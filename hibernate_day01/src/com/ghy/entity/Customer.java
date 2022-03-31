package com.ghy.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Customer {
    private Integer cid;    //客户id
    private String custName;    //客户名称
    private String custLevel;   //客户级别
    private String custSource;  //客户来源
    private String custPhone;   //联系电话
    private String custMobile;  //手机
    //在客户实体类里面表示多个联系人，一个客户里面有多个联系人
    //hibernate要求使用集合表示多的数据，使用set集合
    private Set<LinkMan> setlinkMan = new HashSet<LinkMan>();

    public Set<LinkMan> getSetlinkMan() {
        return setlinkMan;
    }

    public void setSetlinkMan(Set<LinkMan> setlinkMan) {
        this.setlinkMan = setlinkMan;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCustMobile() {
        return custMobile;
    }

    public void setCustMobile(String custMobile) {
        this.custMobile = custMobile;
    }
}
