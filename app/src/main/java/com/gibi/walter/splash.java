package com.gibi.walter;

import android.accounts.NetworkErrorException;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.net.ConnectException;

public class splash extends AppCompatActivity {

    private ProgressBar pb;
    String p1 = "com.gibi";
    String p2 = ".walter";
    String s = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        pb = (ProgressBar) findViewById(R.id.myProgressBar);

        if (getPackageName().compareTo(p1 + p2) != 0) {
            s.getBytes();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(splash.this, Main1Activity.class);
                startActivity(i);

            }
        }, 4000);


    }
}
