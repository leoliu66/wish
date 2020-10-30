package cn.leo.rdp.wish.allbean.vo;

import cn.leo.common.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.math.BigDecimal;

public class WxFinanceVo  extends BaseEntity<Long> {

    /**
     * 订单编号 同一笔单修改多少次订单id一样
     */
    private String orderId;

    /**
     * 是否最新记录: Y是 N否'
     */
    private String newRecord;

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
     * 金额
     */
    private BigDecimal money;

    /**
     * 财务类型  income(收入),expend （支出）
     */
    private String moneyType;

    /**
     * 来源或去向
     */
    private String resource;

    /**
     * 凭证url
     */
    @Length(max = 255)
    private String images;

    public String getNewRecord() {
        return newRecord;
    }

    public void setNewRecord(String newRecord) {
        this.newRecord = newRecord == null ? null : newRecord.trim();
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal == null ? null : principal.trim();
    }

    public String getPrincipalPhone() {
        return principalPhone;
    }

    public void setPrincipalPhone(String principalPhone) {
        this.principalPhone = principalPhone == null ? null : principalPhone.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(String moneyType) {
        this.moneyType = moneyType == null ? null : moneyType.trim();
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource == null ? null : resource.trim();
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

}