package com.education.myoschinatest.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *
 *
 * 数据库被锁 Locked
 *
 * 当多个线程对数据库操作的时候
 * 会出现线程安全的问题
 * 系统报错   ....Locked
 *
 * 1. 数据库操作  加上同步锁
 * 2. 与数据库连接的只能有1个
 *    //与数据库建立连接的
 *    getWriterableDatabase
 *    getReaderableDatabase
 *    //jdbc
 *
 *
 *
 *
 */

public class DouyuSqlHelper extends SQLiteOpenHelper {
    public DouyuSqlHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, 3);
        //
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 第一次创建数据库会调用
        db.execSQL("create table xxx (integer id primary key autoincrement , ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //一般不要这么作  删除之前的表，新建表
        //除非数据库结构发生很大的改变
        //数据迁移   将旧表中的数据 插入到新表中，再删除旧表

        //数据库升级
        if(oldVersion==1 && newVersion==2){
            //
        }else if(oldVersion==1 && newVersion==3) {
            //
        }else  if (oldVersion==2 && newVersion==3){
            //
        }

    }
}
