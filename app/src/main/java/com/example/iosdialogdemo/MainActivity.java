package com.example.iosdialogdemo;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CallingTypeDialog callingTypeDialog;
   private Button btn_alert_dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_alert_dialog = findViewById(R.id.btn_alert_dialog);


        btn_alert_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (callingTypeDialog != null && callingTypeDialog.isVisible())
                    callingTypeDialog.dismiss();
                callingTypeDialog = new CallingTypeDialog();
                callingTypeDialog.showAllowingStateLoss(MainActivity.this.getSupportFragmentManager(), "CallingTypeDialog");
                callingTypeDialog.setClickBack(new CallingTypeDialog.ClickBack() {
                    @Override
                    public void clickBack(int position) {
                        callingTypeDialog.dismiss();
                        switch (position) {
                            case 1://微信通话
                                //wxCallingType();
                                break;
                            case 2://普通通话
                                //  votleCallingType(index);
                                break;
                        }
                    }
                });

            }
        });



    }
}