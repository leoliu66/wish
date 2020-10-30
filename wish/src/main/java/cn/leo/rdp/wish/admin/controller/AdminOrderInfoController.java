package cn.leo.rdp.wish.admin.controller;

import cn.leo.common.utils.RdpRedisson;
import cn.leo.rdp.common.controller.BaseController;
import cn.leo.rdp.web.dto.ResponeModel;
import cn.leo.rdp.web.security.SecurityUtils;
import cn.leo.rdp.wish.admin.service.AdminFinanceInfoService;
import cn.leo.rdp.wish.admin.service.AdminOrderInfoService;
import cn.leo.rdp.wish.allbean.dto.WxFinanceDTO;
import cn.leo.rdp.wish.allbean.dto.WxOrderDTO;
import cn.leo.rdp.wish.allbean.vo.*;
import cn.leo.rdp.wish.common.constants.BaseConstants;
import cn.leo.rdp.wish.common.enums.FinanceMoneyTypeEnum;
import cn.leo.rdp.wish.common.utils.DateUtils;
import cn.leo.rdp.wish.common.utils.ExportExcelUtils;
import cn.leo.rdp.wish.common.utils.IdGen;
import cn.leo.rdp.wish.dao.WxOrderMapper;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AdminOrderInfoController
 * @Description 订单管理
 * @Author liulu_leo
 * @Date 2020/10/27
 * @Version 1.0
 */
@RestController
@RequestMapping("${admin.path}/admin")
public class AdminOrderInfoController extends BaseController {

    @Autowired
    private AdminOrderInfoService adminOrderInfoService;

    /**
     * 查询订单列表
     *
     * @param vo
     * @return
     */
    @PreAuthorize("hasAuthority('sys:orderlist') OR hasAuthority('sys:orderlist:qry')")
    @GetMapping(value = "/orderlist/list")
    public ResponeModel queryFinanceList(@Validated QueryOrderInfoVo vo) {
        logger.info("查询订单列表参数：{}", JSON.toJSONString(vo));
        //不是超级管理员只能查看自己新建的
        if (!SecurityUtils.isSuperAdmin()) {
            vo.setCreateBy(SecurityUtils.getLoginName());
        }
        vo.setNewRecord(BaseConstants.YES);
        PageInfo<WxOrderDTO> pageInfo = adminOrderInfoService.queryOrderList(vo);
        logger.info("查询订单列表返回：{}", JSON.toJSONString(pageInfo));
        return ResponeModel.ok(pageInfo);
    }

    /**
     * 新增订单
     *
     * @param vo
     * @return
     */
    @PreAuthorize("hasAuthority('sys:orderlist:add')")
    @PostMapping(value = "/orderlist")
    public ResponeModel saveOrder(@Validated WxOrderVo vo) {
        logger.info("新增订单入参：{}", JSON.toJSONString(vo));
        //不是超级管理员设计师就是创建者
        if (!SecurityUtils.isSuperAdmin()) {
            vo.setDeveloper(SecurityUtils.getLoginName());
        }
        adminOrderInfoService.saveOrder(vo);
        logger.info("新增订单成功");
        return ResponeModel.ok("添加成功");
    }

    /**
     * 导出
     *
     * @param
     * @version:
     * @author: leo
     */
    @PreAuthorize("hasAuthority('sys:orderlist:export')")
    @GetMapping(value = "/orderlist/export")
    public void exportorderExcel(HttpServletResponse response, @Validated QueryOrderInfoVo vo) throws Exception {
        logger.info("订单导出列表参数：{}", JSON.toJSONString(vo));
        //不是超级管理员只能查看自己新建的
        if (!SecurityUtils.isSuperAdmin()) {
            vo.setCreateBy(SecurityUtils.getLoginName());
        }
        vo.setNewRecord(BaseConstants.YES);
        vo.setLimit(BaseConstants.EXCELROW);
        PageInfo<WxOrderDTO> pageInfo = adminOrderInfoService.queryOrderList(vo);

        if (pageInfo != null && CollectionUtils.isNotEmpty(pageInfo.getList())) {
            List<WxOrderDTO> list = pageInfo.getList();
            ExcelData data = new ExcelData();
            data.setName("订单导出");
            List<String> titles = new ArrayList();
            titles.add("编号");
            titles.add("联系人");
            titles.add("联系人手机号");
            titles.add("开单员");
            titles.add("业务员");
            titles.add("设计师");
            titles.add("订单状态");
            titles.add("支付状态");
            titles.add("成本（元）");
            titles.add("定金（元）");
            titles.add("尾款（元）");
            titles.add("实收金额（元）");
            titles.add("优惠金额（元）");
            titles.add("总金额（元）");
            titles.add("是否打印");
            titles.add("材质");
            titles.add("备注");
            titles.add("创建时间");
            data.setTitles(titles);
            List<List<Object>> rows = new ArrayList();
            List<Object> row = null;
            for (WxOrderDTO wxOrderDTO : list) {
                row = new ArrayList();
                row.add(wxOrderDTO.getId());
                row.add(wxOrderDTO.getContacts());
                row.add(wxOrderDTO.getContactsPhone());
                row.add(wxOrderDTO.getSalesman());
                row.add(wxOrderDTO.getBusiness());
                row.add(wxOrderDTO.getDeveloper());
                row.add(wxOrderDTO.getOrderStatus());
                row.add(wxOrderDTO.getPayStatus());
                row.add(wxOrderDTO.getCost());
                row.add(wxOrderDTO.getDownPayment());
                row.add(wxOrderDTO.getFinalPayment());
                row.add(wxOrderDTO.getMoney());
                row.add(wxOrderDTO.getDiscountAmount());
                row.add(wxOrderDTO.getAllMoney());
                row.add(wxOrderDTO.getPrint());
                row.add(wxOrderDTO.getTexture());
                row.add(wxOrderDTO.getRemarks());
                row.add(DateUtils.formatDateTime(wxOrderDTO.getCreateDate()));
                rows.add(row);
            }
            data.setRows(rows);
            ExportExcelUtils.exportExcel(response, "订单列表.xlsx", data);
        }
    }

    /**
     * 修改订单
     *
     * @param vo
     * @return
     */
    @PreAuthorize("hasAuthority('sys:orderlist:update')")
    @PutMapping(value = "/orderlist")
    public ResponeModel updateOrder(@Validated WxOrderVo vo) {
        logger.info("修改订单入参：{}", JSON.toJSONString(vo));
        if(vo == null || vo.getId() == null){
            return ResponeModel.error("订单ID不能为空！");
        }
        //不是超级管理员设计师就是创建者
        if (!SecurityUtils.isSuperAdmin()) {
            vo.setDeveloper(SecurityUtils.getLoginName());
        }
        adminOrderInfoService.updateOrder(vo);
        logger.info("修改订单纪录成功");
        return ResponeModel.ok("修改成功");
    }
}
