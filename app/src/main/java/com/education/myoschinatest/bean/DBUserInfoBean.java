package com.education.myoschinatest.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Json on 2017/5/16.
 */
@Entity // 标识实体类，greenDAO会映射成sqlite的一个表，表名为实体类名的大写形式
public class DBUserInfoBean {
    @Id(autoincrement = false)
    public long creatTimeAsId;//录入的具体数据的时间作为ID
    @Property(nameInDb = "DBUserInfoBean")
    public String userName;
    public String passWord;
    public String name;
    public String old;
    public String tellPhone;
    public String mail;
    @Generated(hash = 1902891940)
    public DBUserInfoBean(long creatTimeAsId, String userName, String passWord,
            String name, String old, String tellPhone, String mail) {
        this.creatTimeAsId = creatTimeAsId;
        this.userName = userName;
        this.passWord = passWord;
        this.name = name;
        this.old = old;
        this.tellPhone = tellPhone;
        this.mail = mail;
    }
    @Generated(hash = 911723735)
    public DBUserInfoBean() {
    }
    public long getCreatTimeAsId() {
        return this.creatTimeAsId;
    }
    public void setCreatTimeAsId(long creatTimeAsId) {
        this.creatTimeAsId = creatTimeAsId;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassWord() {
        return this.passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getOld() {
        return this.old;
    }
    public void setOld(String old) {
        this.old = old;
    }
    public String getTellPhone() {
        return this.tellPhone;
    }
    public void setTellPhone(String tellPhone) {
        this.tellPhone = tellPhone;
    }
    public String getMail() {
        return this.mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "DBUserInfoBean{" +
                "creatTimeAsId=" + creatTimeAsId +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", name='" + name + '\'' +
                ", old='" + old + '\'' +
                ", tellPhone='" + tellPhone + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
