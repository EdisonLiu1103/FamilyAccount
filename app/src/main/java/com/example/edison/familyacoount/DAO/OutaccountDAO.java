package com.example.edison.familyacoount.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.edison.familyacoount.model.Tb_outaccount;

import java.util.ArrayList;
import java.util.List;

public class OutaccountDAO {
    private DBOpenHelper helper;
    private SQLiteDatabase db;

    public OutaccountDAO(Context context){
        helper = new DBOpenHelper(context);
    }

    /**
     * 添加支出信息
     */
    public void add(Tb_outaccount tb_outaccount){
        db = helper.getWritableDatabase();
        //执行添加支出信息操作
        db.execSQL("insert into tb_outaccount(_id,money,time,type,address,mark)values(?,?,?,?,?,?)", new Object[]{
                tb_outaccount.getId(), tb_outaccount.getMoney(), tb_outaccount.getTime(), tb_outaccount.getType(),tb_outaccount.getAddress(),tb_outaccount.getMark()
        });
    }


    /**
     * 更新支出信息
     */
    public void update(Tb_outaccount tb_outaccount){
        db = helper.getWritableDatabase();
        //执行操作
        db.execSQL("update tb_outaccount set money = ?, time = ?, type = ?, address = ?, mark = ? where _id = ?", new Object[]{
                tb_outaccount.getId(),tb_outaccount.getMoney(), tb_outaccount.getTime(), tb_outaccount.getType(), tb_outaccount.getAddress(), tb_outaccount.getMark()
        });
    }


    /**
     * 查找支出信息
     */
    public  Tb_outaccount find(int id){
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select _id, money, time, type, handler,mark from tb_outaccount where _id = ?",new String[]{
               String.valueOf(id)
        }  );
        if(cursor.moveToNext()){
            return new Tb_outaccount(cursor.getInt(cursor.getColumnIndex("_id")), cursor.getDouble(cursor.getColumnIndex("money")),
                    cursor.getString(cursor.getColumnIndex("time")), cursor.getString(cursor.getColumnIndex("type")),
                    cursor.getString(cursor.getColumnIndex("address")), cursor.getString(cursor.getColumnIndex("mark")));
        }
        return null;
    }

    /**
     * 删除支出信息
     */
    public void delete(Integer... ids){
        if(ids.length > 0){
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < ids.length; i++){
                sb.append('?').append(',');                     //将删除条件添加到StringBuffer对象中
            }
            sb.deleteCharAt(sb.length() - 1);                   //去掉最后一个“；”字符
            db = helper.getWritableDatabase();                  //初始化SQLiteDatabase对象

            //执行删除收入信息操作
            db.execSQL("delete from tb_outaccount where _id in ("+ sb +")",(Object[])ids);
        }
        }

    /**
     * 获取支出信息
      */
    public List<Tb_outaccount> getScrollData(int start, int count){
        List<Tb_outaccount> tb_outaccount = new ArrayList<Tb_outaccount>();            //创建集合对象
        db = helper.getWritableDatabase();                                          //初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select * from tb_inaccount limit ?, ?", new String[]{
                String.valueOf(start),String.valueOf(count)                         //获取所有收入信息
        });

        //遍历所有的支出信息
        while (cursor.moveToNext()){
            tb_outaccount.add(new Tb_outaccount(cursor.getInt(cursor.getColumnIndex("_id")), cursor.getDouble(cursor.getColumnIndex("money")),
                    cursor.getString(cursor.getColumnIndex("time")), cursor.getString(cursor.getColumnIndex("type")),
                    cursor.getString(cursor.getColumnIndex("address")), cursor.getString(cursor.getColumnIndex("mark"))));
        }
        return tb_outaccount;
    }

    //获取总记录数
    public long getCount(){
        db = helper.getWritableDatabase();
        //获取收入信息的记录数
        Cursor cursor = db.rawQuery("select count(_id) from tb_outaccount", null);
        if(cursor.moveToNext()){
            return cursor.getLong(0);
        }
        return 0;
    }

    /**
     * 获取支出最大编号
     */
    public int getMaxId(){
        db = helper.getWritableDatabase();
        //获取支出信息表中的最大编号
        Cursor cursor = db.rawQuery("select max(_id) from tb_outaccount", null);
        while (cursor.moveToNext()){
            return cursor.getInt(0);
        }
        return 0;
    }

}
