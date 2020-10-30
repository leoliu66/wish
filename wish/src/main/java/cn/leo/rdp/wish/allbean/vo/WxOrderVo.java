package cn.leo.rdp.wish.allbean.vo;

import cn.leo.common.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public class WxOrderVo extends BaseEntity<Long> {

    /**
     * 订单编号 同一笔单修改多少次订单id一样
     */
    private String orderId;

    /**
     * 是否最新记录: Y是 N否'
     */
    private String newRecord;

    /**
     * 联系人
     */
    private String contacts;

    /**
     * 联系人手机号
     */
    private String contactsPhone;

    /**
     * 开单员
     */
    private String salesman;

    private String salesmanPhone;

    /**
     * 业务员
     */
    private String business;

    private String businessPhone;

    /**
     * 开发员
     */
    private String developer;

    private String developerPhone;

    /**
     * 订单状态
     */
    private String orderStatus;

    /**
     * 支付状态
     */
    private String payStatus;

    /**
     * 成本
     */
    private BigDecimal cost;

    /**
     * 首付款
     */
    private BigDecimal downPayment;

    /**
     * 尾款
     */
    private BigDecimal finalPayment;

    /**
     * 实收金额
     */
    private BigDecimal money;

    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;

    /**
     * 所有金额
     */
    private BigDecimal allMoney;

    /**
     * 材质
     */
    private String texture;

    /**
     * 是否打印 Y是 N否
     */
    private String print;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getNewRecord() {
        return newRecord;
    }

    public void setNewRecord(String newRecord) {
        this.newRecord = newRecord;
    }

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

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }

    public String getSalesmanPhone() {
        return salesmanPhone;
    }

    public void setSalesmanPhone(String salesmanPhone) {
        this.salesmanPhone = salesmanPhone;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getDeveloperPhone() {
        return developerPhone;
    }

    public void setDeveloperPhone(String developerPhone) {
        this.developerPhone = developerPhone;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(BigDecimal downPayment) {
        this.downPayment = downPayment;
    }

    public BigDecimal getFinalPayment() {
        return finalPayment;
    }

    public void setFinalPayment(BigDecimal finalPayment) {
        this.finalPayment = finalPayment;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getAllMoney() {
        return allMoney;
    }

    public void setAllMoney(BigDecimal allMoney) {
        this.allMoney = allMoney;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public String getPrint() {
        return print;
    }

    public void setPrint(String print) {
        this.print = print;
    }
}