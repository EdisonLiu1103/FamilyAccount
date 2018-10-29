package com.example.edison.familyacoount;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edison.familyacoount.DAO.InaccountDAO;
import com.example.edison.familyacoount.DAO.OutaccountDAO;
import com.example.edison.familyacoount.model.Tb_inaccount;
import com.example.edison.familyacoount.model.Tb_outaccount;

public class InfoManage extends AppCompatActivity {

    protected static final int DATE_DIALOG_ID = 0;         //创建日期对话框常量
    TextView tvtitle, textView;
    EditText txtMoney, txtTime, txtHA, txtMark;
    Spinner spType;
    Button btnEdit, btnDel;
    String[] strInfos;
    String strid, strType;
    private int mYear;
    private int mMonth;
    private int mDay;
    OutaccountDAO outaccountDAO = new OutaccountDAO(InfoManage.this);
    InaccountDAO inaccountDAO = new InaccountDAO(InfoManage.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_manage);

        tvtitle = (TextView) findViewById(R.id.inouttitle);
        textView = (TextView) findViewById(R.id.tvInOut);
        txtMoney = (EditText) findViewById(R.id.txtInOutMoney);
        txtTime = (EditText) findViewById(R.id.txtInOutTime);
        txtHA = (EditText) findViewById(R.id.txtInOut);
        txtMark = (EditText) findViewById(R.id.txtInOutMark);
        spType = (Spinner) findViewById(R.id.spInOutType);
        btnEdit = (Button) findViewById(R.id.btnInOutEdit);
        btnDel = (Button) findViewById(R.id.btnInOutDelete);

        Intent intent = getIntent();                            //创建Intent对象
        Bundle bundle = intent.getExtras();                     //获取传入的数据，并使用Bundle记录
        strInfos = bundle.getStringArray(ShowInfo.FLAG);        //获取Bundle中记录的信息
        strid = strInfos[0];                                    //记录id
        strType = strInfos[1];                                  //记录类型
        if (strType.equals("btnoutinfo")) ;                       //如果类型是btnoutinfo
        {
            tvtitle.setText("Outcome Manage");
            textView.setText("Adress :");                       //设置“地点/付款方”，标签文本为“地点”
            //根据编号查找支出信息，并储存到Tb_outaccount对象中
            Tb_outaccount tb_outaccount = outaccountDAO.find(Integer.parseInt(strid));
            txtMoney.setText(String.valueOf(tb_outaccount.getMoney()));         //显示金额
            txtTime.setText(tb_outaccount.getTime());                           //显示时间
            spType.setPrompt(tb_outaccount.getType());                         //显示类别
            txtHA.setText(tb_outaccount.getAddress());                          //显示地点
            txtMark.setText(tb_outaccount.getMark());                           //显示备注
        } if(strType.equals("btnininfo")) {
            tvtitle.setText("Income Manage");
            textView.setText("Handler:");
            //根据编号查找收入信息，并储存到Tb_outaccount对象中
            Tb_inaccount tb_inaccount = inaccountDAO.find(Integer.parseInt(strid));
            txtMoney.setText(String.valueOf(tb_inaccount.getMoney()));          //显示金额
            txtTime.setText(tb_inaccount.getTime());                            //显示时间
            spType.setPrompt(tb_inaccount.getType());                           //显示类别
            txtHA.setText(tb_inaccount.getHandler());                           //显示付款方
            txtMark.setText(tb_inaccount.getMark());                            //显示备注
        }


        /**
         *
         * btnEdit的编写
         */

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(strType.equals("btnoutinfo")){
                    Tb_outaccount tb_outaccount = new Tb_outaccount();
                    tb_outaccount.setid(Integer.parseInt(strid));
                    //设置金额
                    tb_outaccount.setMoney(Double.parseDouble(txtMoney.getText().toString()));
                    tb_outaccount.setTime(txtTime.getText().toString());
                    tb_outaccount.setType(spType.getSelectedItem().toString());
                    tb_outaccount.setAddress(txtHA.getText().toString());
                    tb_outaccount.setMark(txtMark.getText().toString());
                    outaccountDAO.update(tb_outaccount);
                }
                else  if(strType.equals("btnininfo")){
                    Tb_inaccount tb_inaccount = new Tb_inaccount();
                    tb_inaccount.setId((Integer.parseInt(strid)));
                    tb_inaccount.setTime(txtTime.getText().toString());
                    tb_inaccount.setType(spType.getSelectedItem().toString());
                    tb_inaccount.setHandler(txtHA.getText().toString());
                    tb_inaccount.setMark(txtMark.getText().toString());
                    inaccountDAO.update(tb_inaccount);
                }

                //弹出信息提示
                Toast.makeText(InfoManage.this, "[数据]修改成功", Toast.LENGTH_SHORT).show();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(strType.equals("btninoutinfo")){
                    outaccountDAO.delete(Integer.parseInt(strid));
                }
                else  if(strType.equals("btnininfo")){
                    inaccountDAO.delete(Integer.parseInt(strid));
                }
                Toast.makeText(InfoManage.this, "[删除]数据成功", Toast.LENGTH_SHORT).show();
            }
        });
    }





}
