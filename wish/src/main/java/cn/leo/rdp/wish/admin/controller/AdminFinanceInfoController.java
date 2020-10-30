package cn.leo.rdp.wish.admin.controller;

import cn.leo.common.utils.RdpRedisson;
import cn.leo.rdp.common.controller.BaseController;
import cn.leo.rdp.web.dto.ResponeModel;
import cn.leo.rdp.web.security.SecurityUtils;
import cn.leo.rdp.wish.admin.service.AdminFinanceInfoService;
import cn.leo.rdp.wish.allbean.dto.WxFinanceDTO;
import cn.leo.rdp.wish.allbean.vo.ExcelData;
import cn.leo.rdp.wish.allbean.vo.QueryFinanceInfoVo;
import cn.leo.rdp.wish.allbean.vo.WxFinanceVo;
import cn.leo.rdp.wish.common.constants.BaseConstants;
import cn.leo.rdp.wish.common.enums.FinanceMoneyTypeEnum;
import cn.leo.rdp.wish.common.utils.DateUtils;
import cn.leo.rdp.wish.common.utils.ExportExcelUtils;
import cn.leo.rdp.wish.common.utils.IdGen;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.redisson.api.RLock;
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
 * @ClassName AdminFinanceInfoController
 * @Description 财务管理
 * @Author liulu_leo
 * @Date 2020/10/27
 * @Version 1.0
 */
@RestController
@RequestMapping("${admin.path}/admin/finance")
public class AdminFinanceInfoController extends BaseController {

    @Autowired
    private AdminFinanceInfoService adminFinanceInfoService;

    @Value("${images.finance.url}")
    private String imagesFinanceUrl;

    @Value("${resp.images.finance.url}")
    private String respImagesFinanceUrl;

    @Autowired
    private RdpRedisson rdpRedisson;

    /**
     * 查询财务列表
     *
     * @param vo
     * @return
     */
    @PreAuthorize("hasAuthority('sys:finance:detail') OR hasAuthority('sys:finance:detail:qry')")
    @GetMapping(value = "/list")
    public ResponeModel queryFinanceList(@Validated QueryFinanceInfoVo vo) {
        logger.info("财务列表查询参数：{}", JSON.toJSONString(vo));
        //不是超级管理员只能查看自己新建的
        if (!SecurityUtils.isSuperAdmin()) {
            vo.setCreateBy(SecurityUtils.getLoginName());
        }
        vo.setNewRecord(BaseConstants.YES);
        PageInfo<WxFinanceDTO> pageInfo = adminFinanceInfoService.queryFinanceList(vo);
        logger.info("财务列表查询返回：{}", JSON.toJSONString(pageInfo));
        return ResponeModel.ok(pageInfo);
    }

    /**
     * 新增财务列表
     *
     * @param vo
     * @return
     */
    @PreAuthorize("hasAuthority('sys:finance:detail:add')")
    @PostMapping
    public ResponeModel saveFinance(@Validated WxFinanceVo vo) {
        logger.info("新增财务记录入参：{}", JSON.toJSONString(vo));

        adminFinanceInfoService.saveFinance(vo);
        logger.info("新增财务纪录成功");
        return ResponeModel.ok("添加成功");
    }

    /**
     * 凭证上传
     *
     * @return {Result}
     */
    @PostMapping(value = "/upload/certificate")
    @ResponseBody
    public Object headImg(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {

        Map<String, Object> resp = new HashMap<>();
        resp.put("code", 0);
        resp.put("msg", "上传成功");

        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd");
        //当前时间
        String date = tempDate.format(new java.util.Date());

        String uuid = IdGen.uuid();
        //保存路径
        String uploadDir = imagesFinanceUrl;
        String filepath = null;
        OutputStream out = null;
        //文件名
        String originalName = null;

        InputStream fileInput = null;
        try {
            if (file != null) {
                originalName = uuid + file.getOriginalFilename();

                //filepath = request.getServletContext().getRealPath("/static/images/finance/") + date + "\\" + originalName;
                filepath = respImagesFinanceUrl + "\\" + date + "\\" + originalName;
                filepath = filepath.replace("\\", "/");
                File files = new File(filepath);
                if (!files.getParentFile().exists()) {
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);
            }
        } catch (Exception e) {
            logger.info("上传文件失败：{}", e);
            resp.put("code", 1);
            return resp;
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (fileInput != null) {
                    fileInput.close();
                }
            } catch (IOException e) {
                logger.info("上传文件失败：{}", e);
                resp.put("code", 1);
                return resp;
            }
        }
        Map<String, String> urls = new HashMap<>();
        String url = uploadDir + "\\" + date + "\\" + originalName;
        url = url.replace("\\", "/");
        urls.put("src", url);
        resp.put("data", urls);

        return resp;
    }

    /**
     * 导出
     *
     * @param
     * @version:
     * @author: leo
     */
    @PreAuthorize("hasAuthority('sys:finance:detail:export')")
    @GetMapping(value = "/export")
    public void exportRecordExcel(HttpServletResponse response, @Validated QueryFinanceInfoVo vo) throws Exception {
        logger.info("导出财务列表参数：{}", JSON.toJSONString(vo));
        //不是超级管理员只能查看自己新建的
        if (!SecurityUtils.isSuperAdmin()) {
            vo.setCreateBy(SecurityUtils.getLoginName());
        }
        vo.setNewRecord(BaseConstants.YES);
        vo.setLimit(BaseConstants.EXCELROW);
        PageInfo<WxFinanceDTO> pageInfo = adminFinanceInfoService.queryFinanceList(vo);

        if (pageInfo != null && CollectionUtils.isNotEmpty(pageInfo.getList())) {
            List<WxFinanceDTO> list = pageInfo.getList();
            ExcelData data = new ExcelData();
            data.setName("财务导出");
            List<String> titles = new ArrayList();
            titles.add("编号");
            titles.add("负责人");
            titles.add("负责人手机号");
            titles.add("金额（元）");
            titles.add("财务类型");
            titles.add("创建人");
            titles.add("创建时间");
            titles.add("来源或去向");
            titles.add("备注");
            titles.add("凭证地址");
            data.setTitles(titles);
            List<List<Object>> rows = new ArrayList();
            List<Object> row = null;
            for (WxFinanceDTO wxFinanceDTO : list) {
                row = new ArrayList();
                String moneyType = null;
                if (StringUtils.equals(wxFinanceDTO.getMoneyType(), FinanceMoneyTypeEnum.income.name())) {
                    moneyType = "收入";
                } else if (StringUtils.equals(wxFinanceDTO.getMoneyType(), FinanceMoneyTypeEnum.expend.name())) {
                    moneyType = "支出";
                }
                row.add(wxFinanceDTO.getId());
                row.add(wxFinanceDTO.getPrincipal());
                row.add(wxFinanceDTO.getPrincipalPhone());
                row.add(wxFinanceDTO.getMoney());
                row.add(moneyType);
                row.add(wxFinanceDTO.getCreateBy());
                row.add(DateUtils.formatDateTime(wxFinanceDTO.getCreateDate()));
                row.add(wxFinanceDTO.getResource());
                row.add(wxFinanceDTO.getRemarks());
                row.add(wxFinanceDTO.getImages());
                rows.add(row);
            }
            data.setRows(rows);
            ExportExcelUtils.exportExcel(response, "财务报表.xlsx", data);
        }
    }

    /**
     * 修改财务列表
     *
     * @param vo
     * @return
     */
    @PreAuthorize("hasAuthority('sys:finance:detail:update')")
    @PutMapping
    public ResponeModel updateFinance(@Validated WxFinanceVo vo) {
        logger.info("修改财务记录入参：{}", JSON.toJSONString(vo));
        if(vo == null || vo.getId() == null){
            return ResponeModel.error("财务记录ID不能为空！");
        }
        adminFinanceInfoService.updateFinance(vo);
        logger.info("修改财务纪录成功");
        return ResponeModel.ok("修改成功");
    }
}
