package com.wangsc.loanmanager.model;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 2017/5/10.
 */

public class Loan {
    private UUID id;
    /**
     * 借款类型
     */
    private int type;
    /**
     * 借款日期
     */
    private Date date;
    /**
     * 借款金额
     */
    private double money;
    /**
     * 借款期限
     */
    private int life;
    /**
     * 借款、还款账号
     */
    private int account;
    /**
     * 外键：借款人Id
     */
    private String borrowerId;


}
