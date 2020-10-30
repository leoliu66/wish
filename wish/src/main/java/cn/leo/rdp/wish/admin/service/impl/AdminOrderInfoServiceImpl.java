package cn.leo.rdp.wish.admin.service.impl;

import cn.leo.common.service.CrudService;
import cn.leo.rdp.common.constant.RdpConstants;
import cn.leo.rdp.common.exception.ResponeException;
import cn.leo.rdp.web.security.SecurityUtils;
import cn.leo.rdp.wish.admin.service.AdminOrderInfoService;
import cn.leo.rdp.wish.allbean.dto.WxFinanceDTO;
import cn.leo.rdp.wish.allbean.dto.WxOrderDTO;
import cn.leo.rdp.wish.allbean.vo.QueryFinanceInfoVo;
import cn.leo.rdp.wish.allbean.vo.QueryOrderInfoVo;
import cn.leo.rdp.wish.allbean.vo.WxFinanceVo;
import cn.leo.rdp.wish.allbean.vo.WxOrderVo;
import cn.leo.rdp.wish.common.constants.BaseConstants;
import cn.leo.rdp.wish.common.utils.IdGen;
import cn.leo.rdp.wish.dao.WxFinanceMapper;
import cn.leo.rdp.wish.dao.WxOrderMapper;
import cn.leo.rdp.wish.entity.WxFinance;
import cn.leo.rdp.wish.entity.WxOrder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName AdminOrderInfoServiceImpl
 * @Description 订单管理服务实现
 * @Author liulu_leo
 * @Date 2020/10/27
 * @Version 1.0
 */
@Service
public class AdminOrderInfoServiceImpl extends CrudService<WxOrderMapper, WxOrder>
        implements AdminOrderInfoService {

    @Autowired
    private WxOrderMapper wxOrderMapper;

    @Override
    public PageInfo<WxOrderDTO> queryOrderList(QueryOrderInfoVo vo) {

        PageInfo<WxOrderDTO> resp = new PageInfo<>();
        PageInfo<WxOrder> wxOrderPageInfo = this.findPageByVo(vo);
        if (wxOrderPageInfo != null && CollectionUtils.isNotEmpty(wxOrderPageInfo.getList())) {
            BeanUtils.copyProperties(wxOrderPageInfo, resp);

            List<WxOrderDTO> wxOrderDTOs = new ArrayList<>();
            for (WxOrder WxOrder : wxOrderPageInfo.getList()) {
                WxOrderDTO wxOrderDTO = new WxOrderDTO();
                BeanUtils.copyProperties(WxOrder, wxOrderDTO);
                wxOrderDTOs.add(wxOrderDTO);
            }

            resp.setList(wxOrderDTOs);
        }

        return resp;
    }

    private PageInfo<WxOrder> findPageByVo(QueryOrderInfoVo vo) {

        PageHelper.startPage(vo.getPage().intValue(), vo.getLimit().intValue(), true);
        vo.setSortField("create_data");
        vo.setDirection(RdpConstants.DESC);
        List<WxOrder> list = wxOrderMapper.findListByVo(vo);
        PageInfo<WxOrder> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public void saveOrder(WxOrderVo vo) {

        WxOrder wxOrder = new WxOrder();
        BeanUtils.copyProperties(vo, wxOrder);
        wxOrder.setMoney(wxOrder.getDownPayment().add(wxOrder.getFinalPayment()));
        wxOrder.setAllMoney(wxOrder.getMoney().add(wxOrder.getDiscountAmount()));
        wxOrder.setNewRecord(BaseConstants.YES);
        wxOrder.setOrderId(IdGen.uuid());
        wxOrder.setCreateBy(SecurityUtils.getLoginName());
        wxOrder.setUpdateBy(SecurityUtils.getLoginName());
        wxOrder.setCreateDate(new Date());
        this.save(wxOrder);
    }

    @Override
    @Transactional
    public void updateOrder(WxOrderVo vo) {

        WxOrder oldWxOrder = wxOrderMapper.selectByPrimaryKey(vo.getId());
        if(oldWxOrder == null){
            logger.error("订单ID：{}有误！", vo.getId());
            throw new ResponeException("501", "参数有误");
        } else{
            oldWxOrder.setUpdateBy(SecurityUtils.getLoginName());
            oldWxOrder.setNewRecord(BaseConstants.NO);
        }

        WxOrder newWxOrder = new WxOrder();
        BeanUtils.copyProperties(oldWxOrder, newWxOrder);
        newWxOrder.setId(null);
        newWxOrder.setNewRecord(BaseConstants.YES);
        newWxOrder.setUpdateBy(SecurityUtils.getLoginName());
        if(vo.getContacts()!= null){
            newWxOrder.setContacts(vo.getContacts());
        }
        if(StringUtils.isNotBlank(vo.getContactsPhone())){
            newWxOrder.setContactsPhone(vo.getContactsPhone());
        }
        if(StringUtils.isNotBlank(vo.getBusiness())){
            newWxOrder.setBusiness(vo.getBusiness());
        }
        if(StringUtils.isNotBlank(vo.getSalesman())){
            newWxOrder.setSalesman(vo.getSalesman());
        }
        newWxOrder.setDeveloper(vo.getDeveloper());
        newWxOrder.setMoney(vo.getDownPayment().add(vo.getFinalPayment()));
        newWxOrder.setAllMoney(newWxOrder.getMoney().add(vo.getDiscountAmount()));
        newWxOrder.setCost(vo.getCost());
        newWxOrder.setDownPayment(vo.getDownPayment());
        newWxOrder.setFinalPayment(vo.getFinalPayment());
        newWxOrder.setDiscountAmount(vo.getDiscountAmount());
        newWxOrder.setRemarks(vo.getRemarks());
        newWxOrder.setPrint(vo.getPrint());
        newWxOrder.setTexture(vo.getTexture());
        newWxOrder.setPayStatus(vo.getPayStatus());
        newWxOrder.setOrderStatus(vo.getOrderStatus());
        this.save(newWxOrder);
        wxOrderMapper.updateByPrimaryKey(oldWxOrder);
    }
}
