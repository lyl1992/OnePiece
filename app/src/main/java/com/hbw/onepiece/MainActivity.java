package com.hbw.onepiece;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatTextView module_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        module_1 = findViewById(R.id.module_1);
        module_1.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.module_1:
                Intent it = new Intent(this, NiceDialogActivity.class);
                startActivity(it);
                break;
            default:
                break;
        }
    }
}
