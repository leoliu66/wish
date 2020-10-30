package cn.leo.rdp.wish.dao;

import cn.leo.common.dao.CrudDao;
import cn.leo.rdp.wish.allbean.vo.QueryFinanceInfoVo;
import cn.leo.rdp.wish.allbean.vo.QueryOrderInfoVo;
import cn.leo.rdp.wish.entity.WxOrder;

import java.util.List;

public interface WxOrderMapper extends CrudDao<WxOrder> {
    int deleteByPrimaryKey(Long id);

    int insert(WxOrder record);

    int insertSelective(WxOrder record);

    WxOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxOrder record);

    int updateByPrimaryKey(WxOrder record);

    List<WxOrder> findListByVo(QueryOrderInfoVo param);
}