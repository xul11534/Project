package com.example.superyang.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class HistoryActivity extends AppCompatActivity {


    private AlertDialog dialog;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.history:
                    return true;
                case R.id.main:
                    Intent intent = new Intent();
                    intent.setClass(HistoryActivity.this,MainActivity.class);
                    startActivity(intent);
                    HistoryActivity.this.finish();
                    return true;
                case R.id.upload:
                    dialog.show();
                    return true;
            }
            return false;
        }
    };

    //Dialog按鈕觸發介面
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case 0:
                    break;
                case 1:
                    Toast.makeText(HistoryActivity.this,"camera",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


    //編輯刪除分享選單
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Dialog - Upload
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        String[] options = {"相簿","相機"};
        builder.setItems(options, listener);
        dialog = builder.create();
    }

}
