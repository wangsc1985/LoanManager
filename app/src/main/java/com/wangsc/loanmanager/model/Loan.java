package com.wangsc.loanmanager.model;

import com.wangsc.loanmanager.helper.DateTime;

import java.math.BigDecimal;
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
    private DateTime date;
    /**
     * 借款金额
     */
    private BigDecimal amount;
    /**
     * 借款期限，以月为单位。
     */
    private int life;
    /**
     * 借款、还款账号
     */
    private String account;
    /**
     * 外键：借款人Id
     */
    private UUID borrowerId;

    public Loan(UUID id) {
        this.id = id;
    }

    public Loan(UUID id, int type, DateTime date, BigDecimal amount, int life, String account, UUID borrowerId) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.amount = amount;
        this.life = life;
        this.account = account;
        this.borrowerId = borrowerId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public UUID getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(UUID borrowerId) {
        this.borrowerId = borrowerId;
    }
}
