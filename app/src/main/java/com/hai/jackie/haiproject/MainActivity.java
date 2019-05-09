package com.hai.jackie.haiproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.hai.jackie.apt_annotations.BindView;
import com.hai.jackie.haiproject.butterknife.ButterKnife;

//import butterknife.BindView;
//import butterknife.ButterKnife;

//import butterknife.BindView;
//import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.textview)
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        textView.setText("success Omooo");
    }
}
