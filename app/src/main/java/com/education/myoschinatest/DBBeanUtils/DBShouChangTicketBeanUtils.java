package com.education.myoschinatest.DBBeanUtils;

import android.content.Context;
import com.aidebar.greendaotest.gen.DBShouChangTicketBeanDao;
import com.aidebar.greendaotest.gen.DaoManager;
import com.education.myoschinatest.bean.DBShouChangTicketBean;

import java.util.List;

/**
 * Created by Json on 2017/5/16.
 */

public class DBShouChangTicketBeanUtils {

    private DBShouChangTicketBeanDao dbUserInfoBeanDao ;

    private static DBShouChangTicketBeanUtils dbUserInvestmentUtils=null;

    public DBShouChangTicketBeanUtils  (Context context){
        dbUserInfoBeanDao= DaoManager.getInstance(context).getNewSession().getDBShouChangTicketBeanDao();
    }

    public static DBShouChangTicketBeanUtils getInstance(){
        return dbUserInvestmentUtils;
    }
    public static void Init(Context context){
        if(dbUserInvestmentUtils == null){
            dbUserInvestmentUtils = new DBShouChangTicketBeanUtils(context);
        }
    }

    /**
     * 完成对数据库中插入一条数据操作
     * @param
     * @return
     */
    public void insertOneData(DBShouChangTicketBean dbUserInvestment){
        dbUserInfoBeanDao.insertOrReplace(dbUserInvestment);
    }

    /**
     * 完成对数据库中插入多条数据操作
     * @param dbUserInvestmentList
     * @return
     */
    public boolean insertManyData(List<DBShouChangTicketBean> dbUserInvestmentList){
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
    public boolean deleteOneData(DBShouChangTicketBean dbUserInvestment){
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
    public boolean deleteManData(List<DBShouChangTicketBean> dbUserInvestmentList){
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
    public boolean updateData(DBShouChangTicketBean dbUserInvestment){
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
    public boolean updateManData(List<DBShouChangTicketBean> dbUserInvestmentList){
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
    public DBShouChangTicketBean queryOneData(long id) {
        return dbUserInfoBeanDao.load(id);
    }

    /**
     * 完成对数据库查询所有数据操作
     * @return
     */
    public List<DBShouChangTicketBean> queryData() {
        return dbUserInfoBeanDao.loadAll();
    }

    /**
     * 完成对数据库条件查询数据操作 DependUserName
     * @return
     */
    public List<DBShouChangTicketBean> queryDataDependUserName(String userName) {
        return dbUserInfoBeanDao.queryBuilder().where(DBShouChangTicketBeanDao.Properties.UserName.eq(userName)).build().list();
    }

    /**
     * 完成对数据库条件查询数据操作 DependBuyerName
     * @return
     */
    public List<DBShouChangTicketBean> queryDataDependBuyerName(String buyerName) {
        return dbUserInfoBeanDao.queryBuilder().where(DBShouChangTicketBeanDao.Properties.BuyerName.eq(buyerName)).build().list();
    }

    /**
     * 完成对数据库DependJingdainMing条件查询数据操作
     * @return
     */
    public List<DBShouChangTicketBean> queryDataDependJingdainMing(String jingdainMing) {
        return dbUserInfoBeanDao.queryBuilder().where(DBShouChangTicketBeanDao.Properties.JingdainMing.eq(jingdainMing)).build().list();
    }

}
