package com.example.feng.reflection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            TestReflect4.main();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
