package cn.leo.rdp.wish.admin.service;

import cn.leo.rdp.wish.allbean.dto.WxFinanceDTO;
import cn.leo.rdp.wish.allbean.dto.WxOrderDTO;
import cn.leo.rdp.wish.allbean.vo.QueryFinanceInfoVo;
import cn.leo.rdp.wish.allbean.vo.QueryOrderInfoVo;
import cn.leo.rdp.wish.allbean.vo.WxFinanceVo;
import cn.leo.rdp.wish.allbean.vo.WxOrderVo;
import com.github.pagehelper.PageInfo;

/**
 * 订单管理服务层接口
 * leo
 */
public interface AdminOrderInfoService {

    /**
     * 查询
     * @param vo
     * @return
     */
    PageInfo<WxOrderDTO> queryOrderList(QueryOrderInfoVo vo);

    /**
     * 新增
     * @param vo
     * @return
     */
    void saveOrder(WxOrderVo vo);

    /**
     * 修改
     * @param vo
     * @return
     */
    void updateOrder(WxOrderVo vo);
}
