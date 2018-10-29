package com.example.edison.familyacoount;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.edison.familyacoount.DAO.FlagDAO;
import com.example.edison.familyacoount.model.Tb_flag;

public class FlagManage extends AppCompatActivity {

    EditText txtManageFlag;
    Button btnManageEdit, btnManageDel;
    String strid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_manage);

        txtManageFlag = (EditText)findViewById(R.id.txtFlagManage);
        btnManageDel = (Button)findViewById(R.id.btnFlagManageDelete);
        btnManageEdit = (Button)findViewById(R.id.btnFlagManageEdit);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        strid = bundle.getString(Showinfo.FLAG);
        final FlagDAO flagDAO = new FlagDAO(FlagManage.this);
        txtManageFlag.setText(flagDAO.find(Integer.parseInt(strid)).getFlag());

        btnManageEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tb_flag tb_flag = new Tb_flag();
                tb_flag.setid(Integer.parseInt(strid));
                tb_flag.setFlag(txtManageFlag.getText().toString());
                flagDAO.update(tb_flag);
                Toast.makeText(FlagManage.this, "[Data of Flag] updated successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        btnManageDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flagDAO.delete(Integer.parseInt(strid));
                Toast.makeText(FlagManage.this, "[Deleted flags]successfully!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
