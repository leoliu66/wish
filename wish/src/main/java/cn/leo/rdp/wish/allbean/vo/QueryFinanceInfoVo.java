package cn.leo.rdp.wish.allbean.vo;

import cn.leo.common.entity.QueryEntity;
import cn.leo.rdp.wish.common.constants.BaseConstants;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import java.util.Date;


/**
 * 查询财务信息入参
 * @version:
 * @Description:  
 * @author: leo
 * @date: 2020年10月27日 下午3:41:44
 */
public class QueryFinanceInfoVo extends QueryEntity {

	/**
	 * 负责人
	 */
	@Length(max = 32)
	private String principal;

	/**
	 * 负责人手机号
	 */
	@Length(max = 32)
    private String principalPhone;

	/**
	 * 财务类型  income(收入),expend （支出）
	 */
	@Length(max = 16)
	private String moneyType;

	/**
	 * 创建时间开始范围
	 */
    private Date sdstrdate;

	/**
	 * 创建时间结束范围
	 */
    private Date edstrdate;

	/**
	 * 创建人
	 */
	@Length(max = 32)
	private String createBy;

	/**
	 * 是否最新记录: Y是 N否'
	 */
	private String newRecord;

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getPrincipalPhone() {
		return principalPhone;
	}

	public void setPrincipalPhone(String principalPhone) {
		this.principalPhone = principalPhone;
	}

	public Date getSdstrdate() {
		return sdstrdate;
	}

	public void setSdstrdate(Date sdstrdate) {
		this.sdstrdate = sdstrdate;
	}

	public Date getEdstrdate() {
		return edstrdate;
	}

	public void setEdstrdate(Date edstrdate) {
		this.edstrdate = edstrdate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}

	public String getNewRecord() {
		return newRecord;
	}

	public void setNewRecord(String newRecord) {
		this.newRecord = newRecord;
	}
}
