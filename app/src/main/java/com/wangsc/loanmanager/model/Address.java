package com.wangsc.loanmanager.model;

import java.util.UUID;

/**
 * Created by Administrator on 2017/5/15.
 */

public class Address {
    private UUID id;
    private UUID province;
    private UUID city;
    private UUID county;
    private UUID town;
    private UUID village;

    public Address(UUID id) {
        this.id = id;
    }

    public Address(UUID id, UUID province, UUID city, UUID county, UUID town, UUID village) {
        this.id = id;
        this.province = province;
        this.city = city;
        this.county = county;
        this.town = town;
        this.village = village;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProvince() {
        return province;
    }

    public void setProvince(UUID province) {
        this.province = province;
    }

    public UUID getCity() {
        return city;
    }

    public void setCity(UUID city) {
        this.city = city;
    }

    public UUID getCounty() {
        return county;
    }

    public void setCounty(UUID county) {
        this.county = county;
    }

    public UUID getTown() {
        return town;
    }

    public void setTown(UUID town) {
        this.town = town;
    }

    public UUID getVillage() {
        return village;
    }

    public void setVillage(UUID village) {
        this.village = village;
    }
}
