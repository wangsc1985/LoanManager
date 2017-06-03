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
     * 联系电话
     */
    private String phone;
    /**
     * 地址：省、市、县、镇、村
     */
    private String address;

    public Borrower(UUID id) {
        this.id = id;
    }

    public Borrower(UUID id, String identity, String name, String address, String phone) {
        this.id = id;
        this.identity = identity;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
