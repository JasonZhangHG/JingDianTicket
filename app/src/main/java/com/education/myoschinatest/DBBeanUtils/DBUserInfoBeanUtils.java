package com.education.myoschinatest.DBBeanUtils;

import android.content.Context;
import com.aidebar.greendaotest.gen.DBUserInfoBeanDao;
import com.aidebar.greendaotest.gen.DaoManager;
import com.education.myoschinatest.bean.DBUserInfoBean;
import java.util.List;

/**
 * Created by Json on 2017/5/9.
 */

public class DBUserInfoBeanUtils {

    private DBUserInfoBeanDao dbUserInfoBeanDao ;
    private static DBUserInfoBeanUtils dbUserInvestmentUtils=null;

    public DBUserInfoBeanUtils  (Context context){
        dbUserInfoBeanDao= DaoManager.getInstance(context).getNewSession().getDBUserInfoBeanDao();
    }

    public static DBUserInfoBeanUtils getInstance(){
        return dbUserInvestmentUtils;
    }
    public static void Init(Context context){
        if(dbUserInvestmentUtils == null){
            dbUserInvestmentUtils=new  DBUserInfoBeanUtils(context);
        }
    }

    /**
     * 完成对数据库中插入一条数据操作
     * @param
     * @return
     */
    public void insertOneData(DBUserInfoBean dbUserInvestment){
        dbUserInfoBeanDao.insertOrReplace(dbUserInvestment);
    }

    /**
     * 完成对数据库中插入多条数据操作
     * @param dbUserInvestmentList
     * @return
     */
    public boolean insertManyData(List<DBUserInfoBean> dbUserInvestmentList){
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
    public boolean deleteOneData(DBUserInfoBean dbUserInvestment){
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
    public boolean deleteManData(List<DBUserInfoBean> dbUserInvestmentList){
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
    public boolean updateData(DBUserInfoBean dbUserInvestment){
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
    public boolean updateManData(List<DBUserInfoBean> dbUserInvestmentList){
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
    public DBUserInfoBean queryOneData(long id) {
        return dbUserInfoBeanDao.load(id);
    }

    /**
     * 完成对数据库查询所有数据操作
     * @return
     */
    public List<DBUserInfoBean> queryData() {
        return dbUserInfoBeanDao.loadAll();
    }

    /**
     * 完成对数据库DependName条件查询数据操作
     * @return
     */
    public List<DBUserInfoBean> queryDataDependName(String name) {
        return dbUserInfoBeanDao.queryBuilder().where(DBUserInfoBeanDao.Properties.Name.eq(name)).build().list();
    }

    /**
     * 完成对数据库DependUserName条件查询数据操作
     * @return
     */
    public List<DBUserInfoBean> queryDataDependUserName(String userName) {
        return dbUserInfoBeanDao.queryBuilder().where(DBUserInfoBeanDao.Properties.UserName.eq(userName)).build().list();
    }



}


