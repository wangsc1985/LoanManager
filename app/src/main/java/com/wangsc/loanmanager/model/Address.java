package com.wangsc.loanmanager.model;

import java.util.UUID;

/**
 * Created by Administrator on 2017/5/15.
 */

public class Address {
    private UUID id;
    private UUID provinceId;
    private UUID cityId;
    private UUID countyId;
    private UUID townId;
    private UUID villageId;

    public Address(UUID id) {
        this.id = id;
    }

    public Address(UUID id, UUID provinceId, UUID cityId, UUID countyId, UUID townId, UUID village) {
        this(id);
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.countyId = countyId;
        this.townId = townId;
        this.villageId = village;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(UUID provinceId) {
        this.provinceId = provinceId;
    }

    public UUID getCityId() {
        return cityId;
    }

    public void setCityId(UUID cityId) {
        this.cityId = cityId;
    }

    public UUID getCountyId() {
        return countyId;
    }

    public void setCountyId(UUID countyId) {
        this.countyId = countyId;
    }

    public UUID getTownId() {
        return townId;
    }

    public void setTownId(UUID townId) {
        this.townId = townId;
    }

    public UUID getVillageId() {
        return villageId;
    }

    public void setVillageId(UUID villageId) {
        this.villageId = villageId;
    }

    public String getProvince(DataContext dataContext){
        AddressItem item = dataContext.getAddressItem(provinceId);
        if(item!=null){
            return item.getValue();
        }
        else{
            return "NULL";
        }
    }
    public String getCity(DataContext dataContext){
        AddressItem item = dataContext.getAddressItem(cityId);
        if(item!=null){
            return item.getValue();
        }
        else{
            return "NULL";
        }
    }
    public String getCounty(DataContext dataContext){
        AddressItem item = dataContext.getAddressItem(countyId);
        if(item!=null){
            return item.getValue();
        }
        else{
            return "NULL";
        }
    }
    public String getTown(DataContext dataContext){
        AddressItem item = dataContext.getAddressItem(townId);
        if(item!=null){
            return item.getValue();
        }
        else{
            return "NULL";
        }
    }
    public String getVillage(DataContext dataContext){
        AddressItem item = dataContext.getAddressItem(villageId);
        if(item!=null){
            return item.getValue();
        }
        else{
            return "NULL";
        }
    }
}
