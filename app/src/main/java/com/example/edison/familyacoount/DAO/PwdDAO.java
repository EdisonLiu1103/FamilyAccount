package com.example.edison.familyacoount.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.edison.familyacoount.model.Tb_pwd;

import java.util.ArrayList;
import java.util.List;

public class PwdDAO {
    private DBOpenHelper helper;
    private SQLiteDatabase db;

    public PwdDAO(Context context){
        helper = new DBOpenHelper(context);
    }

    /**
     * 添加密码信息
     */
    public void add(Tb_pwd tb_pwd){
        db = helper.getWritableDatabase();                  //初始化SQLiteDatabase对象
        //执行添加密码信息操作
        db.execSQL("insert into tb_pwd(password)values(?)",new
                Object[]{
                tb_pwd.getPassword()
        });
    }

    /**
     * 更新密码信息
     */
    public void update(Tb_pwd tb_pwd){
        db = helper.getWritableDatabase();                  //初始化SQLiteDatebase对象
        //执行修改密码信息操作
        db.execSQL("update tb_pwd set password = ?", new Object[]{
                tb_pwd.getPassword()
        });

    }

    /**
     * 查找密码信息
     */
    public Tb_pwd find(int id){
        db = helper.getWritableDatabase();                      //初始化SQLiteDatebase对象
        Cursor cursor = db.rawQuery("select password from tb_pwd ", new String[]{
                String.valueOf(id)              //根据编号查找密码信息，并储存到Cursor类中
        });

        if(cursor.moveToNext()){                //遍历查找到的密码信息
            //将遍历到的密码信息储存到Tb_pwd类中
            return new Tb_pwd(cursor.getString(cursor.getColumnIndex("password")));
        }

        //如果没有信息，则返回null
        return null;
    }

    /**
     * 删除密码信息
     */
    public void delete(Integer... ids){
        if(ids.length > 0){                                     //判断是否存在要删除的id
            StringBuffer sb = new StringBuffer();               //创建StringBuffer对象
            for (int i = 0; i < ids.length; i++){
                sb.append('?').append(',');                     //将删除条件添加到StringBuffer对象中
            }
            sb.deleteCharAt(sb.length() - 1);                   //去掉最后一个“；”字符
            db = helper.getWritableDatabase();                  //初始化SQLiteDatabase对象

            //执行删除便签信息操作
            db.execSQL("delete from tb_pwd where password in ("+ sb +")",(Object[])ids);
        }
    }

    /**
     * 获取密码信息
     * @param start 起始位置
     * @param count 每页显示数量
     */
    public List<Tb_pwd> getScrollData(int start, int count){
        List<Tb_pwd> tb_pwd = new ArrayList<Tb_pwd>();            //创建集合对象
        db = helper.getWritableDatabase();                                          //初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select * from tb_flag limit ?, ?", new String[]{
                String.valueOf(start),String.valueOf(count)                         //获取所有便签信息
        });

        //遍历所有的便签信息
        while (cursor.moveToNext()){
            tb_pwd.add(new Tb_pwd(cursor.getString(cursor.getColumnIndex("password"))));
        }
        return tb_pwd;
    }

    /**
     * 获取总记录数
     */
    public long getCount(){
        db = helper.getWritableDatabase();
        //获取密码信息的记录数
        Cursor cursor = db.rawQuery("select count(password) from tb_pwd", null);
        if(cursor.moveToNext()){
            return cursor.getLong(0);
        }
        return 0;
    }

}
