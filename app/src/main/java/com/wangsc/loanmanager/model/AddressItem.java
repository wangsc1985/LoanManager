package com.wangsc.loanmanager.model;

import java.util.UUID;

/**
 * Created by Administrator on 2017/5/16.
 */

public class AddressItem {
    private UUID id;
    /**
     * 地址段名称，比如贺兰、银川等。
     */
    private String value;
    /**
     * 地址级别：省=1；市=2；县=3；镇=4；村=5；
     */
    private int level;
    /**
     * 上一级ID，比如山东省德州市武城县，武城县的上一级是德州市，德州市的上一级是山东省。
     */
    private UUID parentId;

    public AddressItem(UUID id) {
        this.id = id;
    }

    public AddressItem(UUID id, String value, int level, UUID parentId) {
        this.id = id;
        this.value = value;
        this.level = level;
        this.parentId = parentId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public UUID getParentId() {
        return parentId;
    }

    public void setParentId(UUID parentId) {
        this.parentId = parentId;
    }
}
