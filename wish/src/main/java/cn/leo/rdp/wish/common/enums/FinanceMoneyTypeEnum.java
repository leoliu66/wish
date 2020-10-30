package cn.leo.rdp.wish.common.enums;

/**
 * 财务类型
 * income(收入),expend （支出）
 */
public enum FinanceMoneyTypeEnum {

    income("income"),
    expend("expend"),
    ;
    private final String code;

    FinanceMoneyTypeEnum(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }

}
