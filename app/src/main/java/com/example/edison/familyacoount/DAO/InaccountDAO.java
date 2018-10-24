package com.example.edison.familyacoount.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.edison.familyacoount.model.*;
import java.util.ArrayList;
import java.util.List;

public class InaccountDAO {
    private DBOpenHelper helper;            //创建DBOpenHelper对象
    private SQLiteDatabase db;            //创建SQLiteDatabase

    public InaccountDAO(Context context){       //定义构造函数
        helper = new DBOpenHelper(context);     //初始化DBOpenHelper对象
    }

    /**
     * 添加收入信息
     */

    public void add(Tb_inaccount tb_inaccount){
        db = helper.getWritableDatabase();                  //初始化SQLiteDatabase对象
        //执行添加收入信息操作
        db.execSQL("insert into tb_inaccout(_id, money, time, type, handler, mark)values(?, ?, ?, ?, ?, ?)",new
                Object[]{
                        tb_inaccount.getId(), tb_inaccount.getMoney(), tb_inaccount.getTime(), tb_inaccount.getType(),
                tb_inaccount.getHandler(), tb_inaccount.getMark()
        });
    }

    /**
     * 更新收入信息
     */
    public void update(Tb_inaccount tb_inaccount){
        db = helper.getWritableDatabase();                  //初始化SQLiteDatebase对象
        //执行修改收入信息操作
        db.execSQL("update tb_inaccount set money = ?, time = ?, type = ?, handler = ?, mark = ?, where_id = ?", new Object[]{
                tb_inaccount.getId(), tb_inaccount.getMoney(), tb_inaccount.getTime(), tb_inaccount.getType(),
                tb_inaccount.getHandler(), tb_inaccount.getMark()
        });

    }

    /**
     * 查找收入信息
     */
    public Tb_inaccount find(int id){
        db = helper.getWritableDatabase();                      //初始化SQLiteDatebase对象
        Cursor cursor = db.rawQuery("select _id, money, time, type, handler, mark from tb_inaccount where _id = ?", new String[]{
                String.valueOf(id)              //根据编号查找收入信息，并储存到Cursor类中
        });

        if(cursor.moveToNext()){                //遍历查找到的收入信息
            //将遍历到的收入信息储存到Tb_inaccount类中
            return new Tb_inaccount(cursor.getInt(cursor.getColumnIndex("_id")), cursor.getDouble(cursor.getColumnIndex("money")),
                    cursor.getString(cursor.getColumnIndex("time")), cursor.getString(cursor.getColumnIndex("type")),
                    cursor.getString(cursor.getColumnIndex("handler")), cursor.getString(cursor.getColumnIndex("mark")));
        }

        //如果没有信息，则返回null
        return null;
    }

    /**
     * 删除收入信息
     */
    public void delete(Integer... ids){
        if(ids.length > 0){                                     //判断是否存在要删除的id
            StringBuffer sb = new StringBuffer();               //创建StringBuffer对象
            for (int i = 0; i < ids.length; i++){
                sb.append('?').append(',');                     //将删除条件添加到StringBuffer对象中
            }
            sb.deleteCharAt(sb.length() - 1);                   //去掉最后一个“；”字符
            db = helper.getWritableDatabase();                  //初始化SQLiteDatabase对象

            //执行删除收入信息操作
            db.execSQL("delete from tb_inaccount where _id in ("+ sb +")",(Object[])ids);
        }
    }

    /**
     * 获取收入信息
     * @param start 起始位置
     * @param count 每页显示数量
     */
    public List<Tb_inaccount> getScrollData(int start, int count){
        List<Tb_inaccount> tb_inaccount = new ArrayList<Tb_inaccount>();            //创建集合对象
        db = helper.getWritableDatabase();                                          //初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select * from tb_inaccount limit ?, ?", new String[]{
                String.valueOf(start),String.valueOf(count)                         //获取所有收入信息
        });

        //遍历所有的收入信息
        while (cursor.moveToNext()){
            tb_inaccount.add(new Tb_inaccount(cursor.getInt(cursor.getColumnIndex("_id")), cursor.getDouble(cursor.getColumnIndex("money")),
                    cursor.getString(cursor.getColumnIndex("time")), cursor.getString(cursor.getColumnIndex("type")),
                    cursor.getString(cursor.getColumnIndex("handler")), cursor.getString(cursor.getColumnIndex("mark"))));
        }
        return tb_inaccount;
    }

    /**
     * 获取总记录数
     */
    public long getCount(){
        db = helper.getWritableDatabase();
        //获取收入信息的记录数
        Cursor cursor = db.rawQuery("select count(_id) from tb_inaccount", null);
        if(cursor.moveToNext()){
            return cursor.getLong(0);
        }
        return 0;
    }

    /**
     * 获取收入最大编号
     */
    public int getMaxId(){
        db = helper.getWritableDatabase();
        //获取收入信息表中的最大编号
        Cursor cursor = db.rawQuery("select max(_id) from tb_inaccount", null);
        while (cursor.moveToNext()){
            return cursor.getInt(0);
        }
        return 0;
    }
}
