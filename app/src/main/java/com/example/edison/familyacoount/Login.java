package com.example.edison.familyacoount;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.edison.familyacoount.DAO.PwdDAO;

public class Login extends AppCompatActivity {

    private EditText txtlogin;
    private Button btnClose;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        txtlogin = (EditText)findViewById(R.id.txt_login);
        btnClose = (Button)findViewById(R.id.btn_close);
        btnLogin = (Button)findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                PwdDAO pwdDAO = new PwdDAO(this);
                if((pwdDAO.getCount() == 0 || pwdDAO.find().getPassword().isEmpty()) && txtlogin.getText().toString().isEmpty()){
                    startActivity(intent);
                }
                else {
                    //判断输入的密码是否与数据库中的密码一致
                    if(pwdDAO.find().getPassword().equals(txtlogin.getText().toString())){
                        startActivity(intent);                          //启动主Activity
                    }
                    else {
                        //弹出信息提示
                        Toast.makeText(Login.this , "Please input the correct password!", Toast.LENGTH_SHORT).show();
                    }
                }
                txtlogin.setText("");                                   //清空密码文本框
            }
        });
    }
}


