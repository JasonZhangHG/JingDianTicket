package com.education.myoschinatest.DBBeanUtils;

import android.content.Context;
import com.aidebar.greendaotest.gen.DBTicketBeanDao;
import com.aidebar.greendaotest.gen.DaoManager;
import com.education.myoschinatest.bean.DBTicketBean;


import java.util.List;

/**
 * Created by Json on 2017/5/16.
 */

public class DBTicketBeanUtils {

    private DBTicketBeanDao dbUserInfoBeanDao ;
    private static DBTicketBeanUtils dbUserInvestmentUtils=null;

    public DBTicketBeanUtils  (Context context){
        dbUserInfoBeanDao= DaoManager.getInstance(context).getNewSession().getDBTicketBeanDao();
    }

    public static DBTicketBeanUtils getInstance(){
        return dbUserInvestmentUtils;
    }
    public static void Init(Context context){
        if(dbUserInvestmentUtils == null){
            dbUserInvestmentUtils=new  DBTicketBeanUtils(context);
        }
    }

    /**
     * 完成对数据库中插入一条数据操作
     * @param
     * @return
     */
    public void insertOneData(DBTicketBean dbUserInvestment){
        dbUserInfoBeanDao.insertOrReplace(dbUserInvestment);
    }

    /**
     * 完成对数据库中插入多条数据操作
     * @param dbUserInvestmentList
     * @return
     */
    public boolean insertManyData(List<DBTicketBean> dbUserInvestmentList){
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
    public boolean deleteOneData(DBTicketBean dbUserInvestment){
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
    public boolean deleteManData(List<DBTicketBean> dbUserInvestmentList){
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
    public boolean updateData(DBTicketBean dbUserInvestment){
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
    public boolean updateManData(List<DBTicketBean> dbUserInvestmentList){
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
    public DBTicketBean queryOneData(long id) {
        return dbUserInfoBeanDao.load(id);
    }

    /**
     * 完成对数据库查询所有数据操作
     * @return
     */
    public List<DBTicketBean> queryData() {
        return dbUserInfoBeanDao.loadAll();
    }

    /**
     * 完成对数据库jingDianName条件查询数据操作 jingDianName
     * @return
     */
    public List<DBTicketBean> queryDataDependJingDianName(String jingDianName) {
        return dbUserInfoBeanDao.queryBuilder().where(DBTicketBeanDao.Properties.JingDianName.eq(jingDianName)).build().list();
    }

}
