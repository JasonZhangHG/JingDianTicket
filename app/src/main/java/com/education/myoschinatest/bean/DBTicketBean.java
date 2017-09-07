package com.education.myoschinatest.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Json on 2017/5/16.
 */
@Entity // 标识实体类，greenDAO会映射成sqlite的一个表，表名为实体类名的大写形式
public class DBTicketBean {
    @Id(autoincrement = false)
    public long jingDianID;//景点为 作为ID 白帝城景点 1, "大足石刻" 2, "水龙峡地缝" 3, "武隆天生三桥" 4, "仙女山国家深林公园 5
    @Property(nameInDb = "DBTicketBean")
    public String jingDianName;//用户名
    public int jingDianYuPiao;//景点余票
    @Generated(hash = 1285450466)
    public DBTicketBean(long jingDianID, String jingDianName, int jingDianYuPiao) {
        this.jingDianID = jingDianID;
        this.jingDianName = jingDianName;
        this.jingDianYuPiao = jingDianYuPiao;
    }
    @Generated(hash = 485236842)
    public DBTicketBean() {
    }
    public long getJingDianID() {
        return this.jingDianID;
    }
    public void setJingDianID(long jingDianID) {
        this.jingDianID = jingDianID;
    }
    public String getJingDianName() {
        return this.jingDianName;
    }
    public void setJingDianName(String jingDianName) {
        this.jingDianName = jingDianName;
    }
    public int getJingDianYuPiao() {
        return this.jingDianYuPiao;
    }
    public void setJingDianYuPiao(int jingDianYuPiao) {
        this.jingDianYuPiao = jingDianYuPiao;
    }

    @Override
    public String toString() {
        return "DBTicketBean{" +
                "jingDianID=" + jingDianID +
                ", jingDianName='" + jingDianName + '\'' +
                ", jingDianYuPiao=" + jingDianYuPiao +
                '}';
    }
}
