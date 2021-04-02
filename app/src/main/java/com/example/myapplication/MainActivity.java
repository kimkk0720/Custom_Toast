package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.UFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button button, button2;
    TextView textView;
    TextView approval_date, issuer_nm, card_num, amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        textView = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast_Receipt();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("토스트");
            }
        });

    }
    public void showToast_Receipt() {
        LayoutInflater inflater = getLayoutInflater();
        //custom_toast.xml 파일의 toast_design_root 속성을 로드
        View toastDesign = inflater.inflate(R.layout.custom_toast_receipt, (ViewGroup) findViewById(R.id.toast_design_receipt));
        approval_date = toastDesign.findViewById(R.id.date);
        issuer_nm = toastDesign.findViewById(R.id.issuer);
        card_num = toastDesign.findViewById(R.id.card_num);
        amount = toastDesign.findViewById(R.id.amount);

        SimpleDateFormat format1 = new SimpleDateFormat("yyMMddHHmmss");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

        String approval_Date_String ="210331155407";
        try {
            Date date = format1.parse(approval_Date_String);
            String dateToString = format2.format(date);
            approval_date.setText(dateToString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        issuer_nm.setText("카카오페이");
        card_num.setText("123456789");
        amount.setText(String.format("%,d", Integer.parseInt("124567111")) + "원");

        Toast toast = Toast.makeText(this, "", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(toastDesign);
        toast.show();
    }

    public void showToast(String msg) {
        LayoutInflater inflater = getLayoutInflater();
        View toastDesign = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.toast_design_root)); //custom_toast.xml 파일의 toast_design_root 속성을 로드

        //커스텀 토스트를 띄운다
        TextView text = toastDesign.findViewById(R.id.TextView_toast_design);
        //toast_design.xml 파일에서 직접 텍스트를 지정 가능
        //String.trim() : 문자열 맨 앞,뒤에 있는 공백 제거
        text.setText(msg.trim());

        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(toastDesign);
        toast.show();
    }
}