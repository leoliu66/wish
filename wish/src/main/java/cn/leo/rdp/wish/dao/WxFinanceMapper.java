package cn.leo.rdp.wish.dao;

import cn.leo.common.dao.CrudDao;
import cn.leo.rdp.wish.allbean.vo.QueryFinanceInfoVo;
import cn.leo.rdp.wish.entity.WxFinance;
import cn.leo.rdp.wish.entity.WxOrder;

import java.util.List;

public interface WxFinanceMapper extends CrudDao<WxFinance> {

    int deleteByPrimaryKey(Long id);

    int insert(WxOrder record);

    int insertSelective(WxFinance record);

    WxFinance selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxFinance record);

    int updateByPrimaryKey(WxFinance record);

    List<WxFinance> findListByVo(QueryFinanceInfoVo param);
}