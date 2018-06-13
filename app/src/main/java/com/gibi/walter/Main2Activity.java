package com.gibi.walter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class Main2Activity extends AppCompatActivity {

    RadioGroup myRadioGroupe;
    Button next;
    Toolbar myToolbar;
    AdView myAdView;
    InterstitialAd myInterstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        myToolbar = (Toolbar) findViewById(R.id.myToolbar);
        myToolbar.setTitle("");
        setSupportActionBar(myToolbar);

        final Intent i = new Intent(Main2Activity.this, Main3Activity.class);


        AdRequest adRequest= new AdRequest.Builder().build();

        myAdView=(AdView)findViewById(R.id.adView);
        myAdView.loadAd(adRequest);
        myAdView.setAdListener(new AdListener(){
            @Override
            public void onAdFailedToLoad(int i) {
                myAdView.loadAd(new AdRequest.Builder().build());
            }
        });


        myInterstitial=new InterstitialAd(this);
        myInterstitial.setAdUnitId(getResources().getString(R.string.admob_interstitial));
        myInterstitial.loadAd(adRequest);
        myInterstitial.setAdListener(new AdListener(){
            @Override
            public void onAdFailedToLoad(int i) {
                myInterstitial.loadAd(new AdRequest.Builder().build());

            }

            @Override
            public void onAdClosed() {
                myInterstitial.loadAd(new AdRequest.Builder().build());
            }

        });

        myRadioGroupe = (RadioGroup) findViewById(R.id.myRGroupe);
        next = (Button) findViewById(R.id.myNextButton);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int choice = myRadioGroupe.getCheckedRadioButtonId();
                if (choice!=-1){
                    if (choice == R.id.myEmojiRadio) {
                        Toast.makeText(Main2Activity.this, "Emoji", Toast.LENGTH_SHORT).show();
                        i.putExtra("choice",1);
                    } else if (choice == R.id.myThemesRadio) {
                        Toast.makeText(Main2Activity.this, "Themes", Toast.LENGTH_SHORT).show();
                        i.putExtra("choice", 2);
                    } else if (choice == R.id.myGroupeRadio) {
                        Toast.makeText(Main2Activity.this, "Group", Toast.LENGTH_SHORT).show();
                        i.putExtra("choice", 3);
                    }
                    startActivity(i);
                    myInterstitial.show();
                }else Toast.makeText(Main2Activity.this, "حدد إختيارك", Toast.LENGTH_SHORT).show();



            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.privacy){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.privacy_link))));
            Toast.makeText(this, "سياسة الخصوصية", Toast.LENGTH_SHORT).show();
            return true;
        }else if (id==R.id.rateus){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.rate_us_link))));
            Toast.makeText(this, "خمس نجوم", Toast.LENGTH_SHORT).show();
            return true;
        }else return false;

    }

}
