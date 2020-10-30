package cn.leo.rdp.wish.allbean.vo;

import cn.leo.common.entity.QueryEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;


/**
 * 查询订单信息入参
 * @version:
 * @Description:  
 * @author: leo
 * @date: 2020年10月27日 下午3:41:44
 */
public class QueryOrderInfoVo extends QueryEntity {

	/**
	 * 联系人
	 */
	@Length(max = 32)
	private String contacts;

	/**
	 * 联系人手机号
	 */
	@Length(max = 32)
	private String contactsPhone;

	/**
	 * 支付状态:success 已支付 downpayment 定金 nonpayment 未支付 refunded 已退款
	 */
	@Length(max = 16)
	private String payStatus;

	/**
	 * 审核状态:success 已完成 developing 开发中 check 已审核 nocheck 未审核
	 */
	@Length(max = 16)
	private String orderStatus;

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

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getContactsPhone() {
		return contactsPhone;
	}

	public void setContactsPhone(String contactsPhone) {
		this.contactsPhone = contactsPhone;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
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

	public String getNewRecord() {
		return newRecord;
	}

	public void setNewRecord(String newRecord) {
		this.newRecord = newRecord;
	}
}
