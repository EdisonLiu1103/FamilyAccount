package com.example.edison.familyacoount;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    GridView gvInfo;
    String[] titles = new String[]{"New inaccount", "New outaccount", "My inaccount", "My outaccount", "Data manage",
            "Setting", "In/Out notes", "Quit"};

    int[] images = new int[]{R.drawable.inaccount,
                             R.drawable.outaccount,
                             R.drawable.myin,
                             R.drawable.myout,
                             R.drawable.datamanage,
                             R.drawable.setting,
                             R.drawable.bianqian,
                             R.drawable.quit};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        gvInfo = (GridView)findViewById(R.id.gvinfo);

        //创建pictureAdapter对象
        pictureAdapter adapter = new pictureAdapter(titles, images,this);
        gvInfo.setAdapter(adapter);
        gvInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = null;               //创建Intent对象
                switch (i){
                    case 0:
                        //使用AddOutaccount窗口初始化Intent
                        intent = new Intent(MainActivity.this, AddOutaccount.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, AddInaccount.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, Outaccount.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(MainActivity.this, Inaccount.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(MainActivity.this, Showinfo.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(MainActivity.this, Sysset.class);
                        startActivity(intent);
                        break;
                    case 6:
                        intent = new Intent(MainActivity.this, Accountflag.class);
                        startActivity(intent);
                        break;
                    case 7:
                        finish();
                }
            }
        });
    }
}
