package com.education.myoschinatest.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;


@Entity // 标识实体类，greenDAO会映射成sqlite的一个表，表名为实体类名的大写形式
public class DBShouChangTicketBean {
    @Id(autoincrement = false)
    public long creatTimeAsId;//录入的具体数据的时间作为ID
    @Property(nameInDb = "DBShouChangTicketBean")
    public String userName;//用户名
    public String buyerName;//收藏者名字
    public String buyerOld;//收藏者年龄
    public String buyerTel;//收藏者电话
    public String buyerMail;//收藏者邮箱
    public String jingdainMing;//景点名字
    public int position;//景点排序中的位置
    @Generated(hash = 1826216236)
    public DBShouChangTicketBean(long creatTimeAsId, String userName,
            String buyerName, String buyerOld, String buyerTel, String buyerMail,
            String jingdainMing, int position) {
        this.creatTimeAsId = creatTimeAsId;
        this.userName = userName;
        this.buyerName = buyerName;
        this.buyerOld = buyerOld;
        this.buyerTel = buyerTel;
        this.buyerMail = buyerMail;
        this.jingdainMing = jingdainMing;
        this.position = position;
    }
    @Generated(hash = 984507301)
    public DBShouChangTicketBean() {
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
    public String getBuyerName() {
        return this.buyerName;
    }
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }
    public String getBuyerOld() {
        return this.buyerOld;
    }
    public void setBuyerOld(String buyerOld) {
        this.buyerOld = buyerOld;
    }
    public String getBuyerTel() {
        return this.buyerTel;
    }
    public void setBuyerTel(String buyerTel) {
        this.buyerTel = buyerTel;
    }
    public String getBuyerMail() {
        return this.buyerMail;
    }
    public void setBuyerMail(String buyerMail) {
        this.buyerMail = buyerMail;
    }
    public String getJingdainMing() {
        return this.jingdainMing;
    }
    public void setJingdainMing(String jingdainMing) {
        this.jingdainMing = jingdainMing;
    }
    public int getPosition() {
        return this.position;
    }
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "DBShouChangTicketBean{" +
                "creatTimeAsId=" + creatTimeAsId +
                ", userName='" + userName + '\'' +
                ", buyerName='" + buyerName + '\'' +
                ", buyerOld='" + buyerOld + '\'' +
                ", buyerTel='" + buyerTel + '\'' +
                ", buyerMail='" + buyerMail + '\'' +
                ", jingdainMing='" + jingdainMing + '\'' +
                ", position=" + position +
                '}';
    }
}
