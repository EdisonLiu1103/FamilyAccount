package com.example.edison.familyacoount;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.edison.familyacoount.DAO.FlagDAO;
import com.example.edison.familyacoount.DAO.InaccountDAO;
import com.example.edison.familyacoount.DAO.OutaccountDAO;
import com.example.edison.familyacoount.model.Tb_flag;
import com.example.edison.familyacoount.model.Tb_inaccount;
import com.example.edison.familyacoount.model.Tb_outaccount;

import java.util.List;

import static com.example.edison.familyacoount.Inaccountinfo.FLAG;

public class Showinfo extends AppCompatActivity {

    Button btnflaginfo;
    Button btnoutinfo;
    Button btnininfo;
    ListView lvinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showinfo);

        btnflaginfo = (Button)findViewById(R.id.btnflaginfo);
        btnininfo = (Button)findViewById(R.id.btnininfo);
        btnoutinfo = (Button)findViewById(R.id.btnoutinfo);
        lvinfo = (ListView)findViewById(R.id.lvinfo);

        btnflaginfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowInfo(R.id.btnflaginfo);
            }
        });

        lvinfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String strInfo = String.valueOf(((TextView)view).getText());
                String strid = strInfo.substring(0, strInfo.indexOf("|"));
                String strType = " ";
                Intent intent = null;
                if(strType == "btnoutinfo" | strType == "btninfo"){
                    //使用InfoManage窗口初始化intent对象
                    intent = new Intent(Showinfo.this, InfoManage.class);
                    intent.putExtra(FLAG, new String[]{strid, strType});
                }else if(strType == "btnflaginfo"){
                    //使用FlagManage窗口初始化对象
                    intent = new Intent(Showinfo.this, InfoManage.class);
                    intent.putExtra(FLAG, strid);
                }
                startActivity(intent);
            }
        });

    }
    private void ShowInfo(int intType){
        String[] strInfos = null;
        ArrayAdapter<String> arrayAdapter = null;
        switch (intType){
            case R.id.btnoutinfo:
                String strType;
                strType = "btnoutinfo";
                OutaccountDAO outaccountinfo = new OutaccountDAO(Showinfo.this);
                //获取所有支出信息，并储存到List泛型集合中
                List<Tb_outaccount> listoutinfos = outaccountinfo.getScrollData(0, (int)outaccountinfo.getCount());
                strInfos = new String [listoutinfos.size()];
                int i = 0;
                for(Tb_outaccount tb_outaccount:listoutinfos){
                    strInfos[i] = tb_outaccount.getId()+ " |" + tb_outaccount.getType() + " " +String.valueOf(tb_outaccount.getMoney())+
                            "yuan " + tb_outaccount.getTime();
                    i++;
                }
                break;
            case R.id.btnininfo:
                strType = "btnininfo";
                InaccountDAO inaccountinfo = new InaccountDAO(Showinfo.this);
                List<Tb_inaccount> listinfos = inaccountinfo.getScrollData(0, (int)inaccountinfo.getCount());
                strInfos = new String[listinfos.size()];
                int m = 0;
                for(Tb_inaccount tb_inaccount:listinfos){
                    strInfos[m] = tb_inaccount.getId() + " |" + tb_inaccount.getType() + " " +String.valueOf(tb_inaccount.getMoney())+
                            "yuan " + tb_inaccount.getTime();
                    m++;
                }
                break;
            case R.id.btnflaginfo:
                strType = "btnflaginfo";
                FlagDAO flaginfo = new FlagDAO(Showinfo.this);
                List<Tb_flag> listFlags = flaginfo.getScrollData(0, (int)flaginfo.getCount());
                strInfos = new String[listFlags.size()];
                int n = 0;
                for(Tb_flag tb_flag:listFlags){
                    strInfos[n] = tb_flag.getId() + "|"+tb_flag.getFlag();
                    if(strInfos[n].length() > 15)
                        strInfos[n] = strInfos[n].substring(0, 15)+ "******";
                    n++;
                }
                break;
        }

        arrayAdapter = new ArrayAdapter<String >(this, R.layout.simple_list_item_1,strInfos);
        lvinfo.setAdapter(arrayAdapter);
    }


}
