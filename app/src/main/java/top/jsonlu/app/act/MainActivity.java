package top.jsonlu.app.act;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import top.jsonlu.app.R;
import top.jsonlu.app.frg.MainFrg;

public class MainActivity extends Ba {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        replace(R.id.contents, MainFrg.newInstance());
    }


}
