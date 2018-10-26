package com.example.edison.familyacoount;

import android.content.Intent;
import android.icu.text.IDNA;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.edison.familyacoount.DAO.InaccountDAO;
import com.example.edison.familyacoount.model.Tb_inaccount;

import java.util.List;

public class Inaccountinfo extends AppCompatActivity {

    public static final String FLAG = "id";
    ListView lvinfo;
    String strType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inaccountinfo);

        lvinfo = (ListView)findViewById(R.id.lvinaccountInfo);
        ShowInfo(R.id.btnininfo);

        lvinfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String strInfo = String.valueOf(((TextView)view).getText());        //记录收入信息
                String strid = strInfo.substring(0, strInfo.indexOf("|"));
                Intent intent = new Intent(Inaccountinfo.this, InfoManage.class);
                intent.putExtra(FLAG, new String[]{strid, strType});
                startActivity(intent);
            }
        });
    }

    private void ShowInfo(int intType){
        String[] strInfos = null;
        ArrayAdapter<String> arrayAdapter = null;
        strType = "btnininfo";
        InaccountDAO inaccountinfo = new InaccountDAO(Inaccountinfo.this);   //创建InaccountDAO
        //获取所有收入信息，并存储到List泛型集合中去
        List<Tb_inaccount> listinfos = inaccountinfo.getScrollData(0, (int)inaccountinfo.getCount());
        strInfos = new String[listinfos.size()];        //设置字符数组的长度
        int m = 0;                                      //定义一个开始标识
        for(Tb_inaccount tb_inaccount:listinfos){
            //将收入相关信息组合成一个字符串，储存到字符串数组的相应位置
            strInfos[m] = tb_inaccount.getId() + "|" +tb_inaccount.getType() + " " + String.valueOf(tb_inaccount.getMoney())+
                    " yuan  " + tb_inaccount.getTime();
            m++;
        }

        //使用字符串数组初始化ArrayAdapter对象
        arrayAdapter = new ArrayAdapter<String >(this, android.R.layout.simple_list_item_1, strInfos);
        lvinfo.setAdapter(arrayAdapter);
    }


}
