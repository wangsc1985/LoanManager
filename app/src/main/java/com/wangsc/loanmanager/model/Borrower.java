package com.wangsc.loanmanager.model;

import java.util.UUID;

/**
 * 借款人，收到资金的人。
 * Created by Administrator on 2017/5/10.
 */

public class Borrower {
    private UUID id;
    /**
     * 身份证号
     */
    private String identity;
    /**
     * 借款人姓名
     */
    private String name;
    /**
     * 地址：省、市、县、镇、村
     */
    private Adress adress;
    /**
     * 联系电话
     */
    private String phone;
}
