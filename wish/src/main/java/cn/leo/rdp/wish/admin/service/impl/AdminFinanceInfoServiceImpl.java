package cn.leo.rdp.wish.admin.service.impl;

import cn.leo.common.service.CrudService;
import cn.leo.rdp.common.constant.RdpConstants;
import cn.leo.rdp.common.exception.ResponeException;
import cn.leo.rdp.web.security.SecurityUtils;
import cn.leo.rdp.wish.admin.service.AdminFinanceInfoService;
import cn.leo.rdp.wish.allbean.dto.WxFinanceDTO;
import cn.leo.rdp.wish.allbean.vo.QueryFinanceInfoVo;
import cn.leo.rdp.wish.allbean.vo.WxFinanceVo;
import cn.leo.rdp.wish.common.constants.BaseConstants;
import cn.leo.rdp.wish.dao.WxFinanceMapper;
import cn.leo.rdp.wish.entity.WxFinance;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.leo.rdp.wish.common.utils.IdGen;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName AdminFinanceInfoServiceImpl
 * @Description 财务管理服务实现
 * @Author liulu_leo
 * @Date 2020/10/27
 * @Version 1.0
 */
@Service
public class AdminFinanceInfoServiceImpl extends CrudService<WxFinanceMapper, WxFinance>
        implements AdminFinanceInfoService {

    @Autowired
    private WxFinanceMapper wxFinanceMapper;

    @Override
    public PageInfo<WxFinanceDTO> queryFinanceList(QueryFinanceInfoVo vo) {

        PageInfo<WxFinanceDTO> resp = new PageInfo<>();
        PageInfo<WxFinance> wxFinancePageInfo= this.findPageByVo(vo);
        if(wxFinancePageInfo!=null && CollectionUtils.isNotEmpty(wxFinancePageInfo.getList())){
            BeanUtils.copyProperties(wxFinancePageInfo, resp);

            List<WxFinanceDTO> wxFinanceDTOS = new ArrayList<>();
            for (WxFinance wxFinance : wxFinancePageInfo.getList()){
                WxFinanceDTO wxFinanceDTO = new WxFinanceDTO();
                BeanUtils.copyProperties(wxFinance, wxFinanceDTO);
                wxFinanceDTOS.add(wxFinanceDTO);
            }

            resp.setList(wxFinanceDTOS);
        }

        return resp;
    }

    private PageInfo<WxFinance> findPageByVo(QueryFinanceInfoVo vo){

        PageHelper.startPage(vo.getPage().intValue(), vo.getLimit().intValue(), true);
        vo.setSortField("create_data");
        vo.setDirection(RdpConstants.DESC);
        List<WxFinance> list = wxFinanceMapper.findListByVo(vo);
        PageInfo<WxFinance> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public void saveFinance(WxFinanceVo vo) {

        WxFinance wxFinance = new WxFinance();
        BeanUtils.copyProperties(vo, wxFinance);
        wxFinance.setNewRecord(BaseConstants.YES);
        wxFinance.setOrderId(IdGen.uuid());
        wxFinance.setCreateBy(SecurityUtils.getLoginName());
        wxFinance.setUpdateBy(SecurityUtils.getLoginName());
        wxFinance.setCreateDate(new Date());
        this.save(wxFinance);
    }

    @Override
    @Transactional
    public void updateFinance(WxFinanceVo vo) {

        WxFinance oldWxFinance = wxFinanceMapper.selectByPrimaryKey(vo.getId());
        if(oldWxFinance == null){
            logger.error("财务记录ID：{}有误！", vo.getId());
            throw new ResponeException("501", "参数有误");
        } else{
            oldWxFinance.setUpdateBy(SecurityUtils.getLoginName());
            oldWxFinance.setNewRecord(BaseConstants.NO);
        }

        WxFinance newWxFinance = new WxFinance();
        BeanUtils.copyProperties(oldWxFinance, newWxFinance, "");
        newWxFinance.setId(null);
        newWxFinance.setNewRecord(BaseConstants.YES);
        newWxFinance.setUpdateBy(SecurityUtils.getLoginName());
        if(vo.getMoney()!= null){
            newWxFinance.setMoney(vo.getMoney());
        }
        if(StringUtils.isNotBlank(vo.getMoneyType())){
            newWxFinance.setMoneyType(vo.getMoneyType());
        }
        if(StringUtils.isNotBlank(vo.getImages())){
            newWxFinance.setImages(vo.getImages());
        }
        if(StringUtils.isNotBlank(vo.getPrincipal())){
            newWxFinance.setPrincipal(vo.getPrincipal());
        }
        if(StringUtils.isNotBlank(vo.getPrincipalPhone())){
            newWxFinance.setPrincipalPhone(vo.getPrincipalPhone());
        }
        if(StringUtils.isNotBlank(vo.getResource())){
            newWxFinance.setResource(vo.getResource());
        }
        this.save(newWxFinance);
        wxFinanceMapper.updateByPrimaryKey(oldWxFinance);
    }
}
