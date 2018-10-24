package com.example.edison.familyacoount.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.edison.familyacoount.model.Tb_flag;

import java.util.ArrayList;
import java.util.List;

public class FlagDAO {

    private DBOpenHelper helper;
    private SQLiteDatabase db;

    private FlagDAO(Context context){
        helper = new DBOpenHelper(context);
    }

    /**
     * 添加便签信息
     */
    public void add(Tb_flag tb_flag){
        db = helper.getWritableDatabase();                  //初始化SQLiteDatabase对象
        //执行添加收入信息操作
        db.execSQL("insert into tb_flag(_id, flag)values(?, ?)",new
                Object[]{
                tb_flag.getId(), tb_flag.getFlag()
        });
    }

    /**
     * 更新便签信息
     */
    public void update(Tb_flag tb_flag){
        db = helper.getWritableDatabase();                  //初始化SQLiteDatebase对象
        //执行修改收入信息操作
        db.execSQL("update tb_flag set flag = ?", new Object[]{
                tb_flag.getId(), tb_flag.getFlag()
        });

    }

    /**
     * 查找便签信息
     */
    public Tb_flag find(int id){
        db = helper.getWritableDatabase();                      //初始化SQLiteDatebase对象
        Cursor cursor = db.rawQuery("select _id, money, time, type, handler, mark from tb_inaccount where _id = ?", new String[]{
                String.valueOf(id)              //根据编号查找收入信息，并储存到Cursor类中
        });

        if(cursor.moveToNext()){                //遍历查找到的收入信息
            //将遍历到的便签信息储存到Tb_inaccount类中
            return new Tb_flag(cursor.getInt(cursor.getColumnIndex("_id")), cursor.getString(cursor.getColumnIndex("flag")));
        }

        //如果没有信息，则返回null
        return null;
    }

    /**
     * 删除便签信息
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
            db.execSQL("delete from tb_flag where _id in ("+ sb +")",(Object[])ids);
        }
    }

    /**
     * 获取便签信息
     * @param start 起始位置
     * @param count 每页显示数量
     */
    public List<Tb_flag> getScrollData(int start, int count){
        List<Tb_flag> tb_flag = new ArrayList<Tb_flag>();            //创建集合对象
        db = helper.getWritableDatabase();                                          //初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select * from tb_flag limit ?, ?", new String[]{
                String.valueOf(start),String.valueOf(count)                         //获取所有便签信息
        });

        //遍历所有的便签信息
        while (cursor.moveToNext()){
            tb_flag.add(new Tb_flag(cursor.getInt(cursor.getColumnIndex("_id")),cursor.getString(cursor.getColumnIndex("falg"))));
        }
        return tb_flag;
    }

    /**
     * 获取总记录数
     */
    public long getCount(){
        db = helper.getWritableDatabase();
        //获取便签信息的记录数
        Cursor cursor = db.rawQuery("select count(_id) from tb_flag", null);
        if(cursor.moveToNext()){
            return cursor.getLong(0);
        }
        return 0;
    }

    /**
     * 获取便签最大编号
     */
    public int getMaxId(){
        db = helper.getWritableDatabase();
        //获取便签信息表中的最大编号
        Cursor cursor = db.rawQuery("select max(_id) from tb_flag", null);
        while (cursor.moveToNext()){
            return cursor.getInt(0);
        }
        return 0;
    }
}
