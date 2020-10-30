package cn.leo.rdp.wish.admin.service;

import cn.leo.rdp.wish.allbean.dto.WxFinanceDTO;
import cn.leo.rdp.wish.allbean.vo.QueryFinanceInfoVo;
import cn.leo.rdp.wish.allbean.vo.WxFinanceVo;
import com.github.pagehelper.PageInfo;

/**
 * 财务管理服务层接口
 * leo
 */
public interface AdminFinanceInfoService {

    /**
     * 查询财务列表详情
     * @param vo
     * @return
     */
    PageInfo<WxFinanceDTO> queryFinanceList(QueryFinanceInfoVo vo);

    /**
     * 新增财务记录
     * @param vo
     * @return
     */
    void saveFinance(WxFinanceVo vo);

    /**
     * 修改财务记录
     * @param vo
     * @return
     */
    void updateFinance(WxFinanceVo vo);
}
