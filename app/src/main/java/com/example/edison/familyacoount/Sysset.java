package com.example.edison.familyacoount;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.edison.familyacoount.DAO.PwdDAO;
import com.example.edison.familyacoount.model.Tb_pwd;

public class Sysset extends AppCompatActivity {

    EditText txtpwd;
    Button btnSet, btnsetCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sysset);

        txtpwd = (EditText)findViewById(R.id.txtPwd);
        btnSet = (Button)findViewById(R.id.btnSet);
        btnsetCancel = (Button)findViewById(R.id.btnsetCancel);

        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PwdDAO pwdDAO = new PwdDAO(Sysset.this);
                Tb_pwd tb_pwd = new Tb_pwd(txtpwd.getText().toString());
                if(pwdDAO.getCount() == 0){
                    pwdDAO.add(tb_pwd);
                }else{
                    pwdDAO.update(tb_pwd);
                }
                Toast.makeText(Sysset.this, "[Password] Set successfully!", Toast.LENGTH_SHORT).show();
            }
        });
        btnsetCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtpwd.setText(" ");
                txtpwd.setHint("Please input password!");
            }
        });
    }
}
