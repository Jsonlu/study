package com.example.logtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.log.SMSAndMailSender;

public class TestActivity extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("-------");
                System.out.println(test("aaa"));;
            }
        });
    }


    @SMSAndMailSender(smsContent = "MODEL_SUBMIT_SMS", mailContent =
            "MODEL_SUPPLIER_EMAIL", subject = "MODEL_SUBJECT_EMAIL")
    public String test(String param) {
        return "success";
    }
}
