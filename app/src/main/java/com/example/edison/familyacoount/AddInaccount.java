package com.example.edison.familyacoount;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.edison.familyacoount.DAO.InaccountDAO;
import com.example.edison.familyacoount.model.Tb_inaccount;

import java.util.Calendar;

public class AddInaccount extends AppCompatActivity {


    protected static final int DATE_DIALOG_ID = 0;          //日期对话框常量
    EditText txtInMoney, txtInTime, txtInHandler, txtInMark;
    Spinner spInType;
    Button btnInSaveButton;
    Button btnInCancelButton;
    private int mYear;
    private int mMonth;
    private int mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inaccount);

        txtInMoney = findViewById(R.id.txtInMoney);
        txtInTime = findViewById(R.id.txtInTime);
        txtInHandler = findViewById(R.id.txtInHandler);
        txtInHandler = findViewById(R.id.txtInHandler);
        spInType = findViewById(R.id.spInType);
        btnInSaveButton = findViewById(R.id.btnInSave);
        btnInCancelButton = findViewById(R.id.btnInCancel);




        txtInTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        updateDisplay();

        btnInSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strInMoney = txtInMoney.getText().toString();
                if(!strInMoney.isEmpty()){
                    //创建InaccountDAO对象
                    InaccountDAO inaccountDAO = new InaccountDAO(AddInaccount.this);
                    Tb_inaccount tb_inaccount = new Tb_inaccount(
                            inaccountDAO.getMaxId()+1, Double.parseDouble(strInMoney),
                            txtInTime.getText().toString(), spInType.getSelectedItem().toString(), txtInHandler.getText().toString(),
                            txtInMark.getText().toString());
                            inaccountDAO.add(tb_inaccount);
                            //弹出提示信息
                    Toast.makeText(AddInaccount.this, "[The New Inaccount] added successfully!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(AddInaccount.this, "Please input the inaccount amount!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnInCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtInMoney.setText("");
                txtInMoney.setHint("0.00");
                txtInTime.setText("");
                txtInTime.setHint("2018-01-01");
                txtInHandler.setText("");
                txtInMark.setText("");
                spInType.setSelection(0);
            }
        });
    }

    private void updateDisplay(){
        txtInTime.setText(new StringBuilder().append(mYear).append("-").append(mMonth + 1).append("-").append(mDay));
    }

    @Override
    protected Dialog onCreateDialog(int id){
        switch (id){
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateListener, mYear, mMonth, mDay);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            mYear = i;
            mMonth = i1;
            mDay = i2;
            updateDisplay();
        }
    };


}

