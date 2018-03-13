package com.example.superyang.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import static android.Manifest.permission.*;


public class MainActivity extends AppCompatActivity {

    private AlertDialog dialog;

    //Dialog按鈕觸發介面
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case 0:
                    break;
                case 1:
                    Toast.makeText(MainActivity.this,"camera",Toast.LENGTH_SHORT).show();
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

    //底部選單按鈕觸發
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.history:
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this,HistoryActivity.class);
                    startActivity(intent);
                    MainActivity.this.finish();
                    return true;
                case R.id.main:
                    return true;
                case R.id.upload:
                    dialog.show();
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //隱藏ActionBar
        getSupportActionBar().hide();

        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA);

        //底部選單物件宣告
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Dialog - Upload
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        String[] options = {"相簿","相機"};
        builder.setItems(options, listener);
        dialog = builder.create();
    }


}
