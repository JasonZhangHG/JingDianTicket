package com.education.myoschinatest.DBBeanUtils;

import android.content.Context;

import com.aidebar.greendaotest.gen.DBBuyTicketBeanDao;
import com.aidebar.greendaotest.gen.DBUserInfoBeanDao;
import com.aidebar.greendaotest.gen.DaoManager;
import com.education.myoschinatest.bean.DBBuyTicketBean;
import com.education.myoschinatest.bean.DBUserInfoBean;

import java.util.List;

/**
 * Created by Json on 2017/5/16.
 */

public class DBBuyTicketBeanUtils {

    private DBBuyTicketBeanDao dbUserInfoBeanDao ;
    private static DBBuyTicketBeanUtils dbUserInvestmentUtils=null;

    public DBBuyTicketBeanUtils  (Context context){
        dbUserInfoBeanDao= DaoManager.getInstance(context).getNewSession().getDBBuyTicketBeanDao();
    }

    public static DBBuyTicketBeanUtils getInstance(){
        return dbUserInvestmentUtils;
    }
    public static void Init(Context context){
        if(dbUserInvestmentUtils == null){
            dbUserInvestmentUtils=new  DBBuyTicketBeanUtils(context);
        }
    }

    /**
     * 完成对数据库中插入一条数据操作
     * @param
     * @return
     */
    public void insertOneData(DBBuyTicketBean dbUserInvestment){
        dbUserInfoBeanDao.insertOrReplace(dbUserInvestment);
    }

    /**
     * 完成对数据库中插入多条数据操作
     * @param dbUserInvestmentList
     * @return
     */
    public boolean insertManyData(List<DBBuyTicketBean> dbUserInvestmentList){
        boolean flag = false;
        try{
            dbUserInfoBeanDao.insertOrReplaceInTx(dbUserInvestmentList);
            flag = true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库中删除一条数据操作
     * @param dbUserInvestment
     * @return
     */
    public boolean deleteOneData(DBBuyTicketBean dbUserInvestment){
        boolean flag = false;
        try{
            dbUserInfoBeanDao.delete(dbUserInvestment);
            flag = true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库中删除一条数据 ByKey操作
     * @return
     */
    public boolean deleteOneDataByKey(long id){
        boolean flag = false;
        try{
            dbUserInfoBeanDao.deleteByKey(id);
            flag = true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库中批量删除数据操作
     * @return
     */
    public boolean deleteManData(List<DBBuyTicketBean> dbUserInvestmentList){
        boolean flag = false;
        try{
            dbUserInfoBeanDao.deleteInTx(dbUserInvestmentList);
            flag = true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库更新数据操作
     * @return
     */
    public boolean updateData(DBBuyTicketBean dbUserInvestment){
        boolean flag = false;
        try{
            dbUserInfoBeanDao.update(dbUserInvestment);
            flag = true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库批量更新数据操作
     * @return
     */
    public boolean updateManData(List<DBBuyTicketBean> dbUserInvestmentList){
        boolean flag = false;
        try{
            dbUserInfoBeanDao.updateInTx(dbUserInvestmentList);
            flag = true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库查询数据操作
     * @return
     */
    public DBBuyTicketBean queryOneData(long id) {
        return dbUserInfoBeanDao.load(id);
    }

    /**
     * 完成对数据库查询所有数据操作
     * @return
     */
    public List<DBBuyTicketBean> queryData() {
        return dbUserInfoBeanDao.loadAll();
    }

    /**
     * 完成对数据库条件查询数据操作 DependUserName
     * @return
     */
    public List<DBBuyTicketBean> queryDataDependUserName(String userName) {
        return dbUserInfoBeanDao.queryBuilder().where(DBBuyTicketBeanDao.Properties.UserName.eq(userName)).build().list();
    }

    /**
     * 完成对数据库条件查询数据操作 DependBuyerName
     * @return
     */
    public List<DBBuyTicketBean> queryDataDependBuyerName(String buyerName) {
        return dbUserInfoBeanDao.queryBuilder().where(DBBuyTicketBeanDao.Properties.BuyerName.eq(buyerName)).build().list();
    }

    /**
     * 完成对数据库DependJingdainMing条件查询数据操作
     * @return
     */
    public List<DBBuyTicketBean> queryDataDependJingdainMing(String jingdainMing) {
        return dbUserInfoBeanDao.queryBuilder().where(DBBuyTicketBeanDao.Properties.JingdainMing.eq(jingdainMing)).build().list();
    }



}
