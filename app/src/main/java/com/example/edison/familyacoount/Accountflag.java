 package com.example.edison.familyacoount;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.edison.familyacoount.DAO.FlagDAO;
import com.example.edison.familyacoount.model.Tb_flag;

 public class Accountflag extends AppCompatActivity {

    EditText txtFlag ;
    Button btnflagSaveButton;
    Button btnflagCancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountflag);

        txtFlag = (EditText)findViewById(R.id.txtFlag);
        btnflagSaveButton = (Button)findViewById(R.id.btnflagSave);
        btnflagCancelButton = (Button)findViewById(R.id.btnflagCancel);

        btnflagSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strFlag = txtFlag.getText().toString();
                if(strFlag.isEmpty()){
                    FlagDAO flagDAO = new FlagDAO(Accountflag.this);
                    Tb_flag tb_flag = new Tb_flag(flagDAO.getMaxId()+1, strFlag);       //创建Tb_flag对象
                    Toast.makeText(Accountflag.this, "[New flag] added successfully!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Accountflag.this, "Please input the flags!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnflagCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtFlag.setText(" ");
            }
        });

    }
}
