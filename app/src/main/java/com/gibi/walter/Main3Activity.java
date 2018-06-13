package com.gibi.walter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class Main3Activity extends AppCompatActivity {

    Toolbar myToolbar;
    TextView myTextView;
    FrameLayout myFrame;
    Button nextBtn;
    AdView myAdView;
    InterstitialAd myInterstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        myToolbar=(Toolbar)findViewById(R.id.myToolbar);
        myToolbar.setTitle("");
        setSupportActionBar(myToolbar);

        final Intent i = new Intent(new Intent(Main3Activity.this,Main4Activity.class));

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

        myFrame=(FrameLayout)findViewById(R.id.frameresult);
        nextBtn=(Button)findViewById(R.id.myNextButton);
        LayoutInflater inflater = getLayoutInflater();


        myTextView=(TextView)findViewById(R.id.mytxt);
        int choiceInput=getIntent().getIntExtra("choice",0);

        if (choiceInput==1){
            myTextView.setText(R.string.emojichoice);
            View emojisView=(View) inflater.inflate(R.layout.emojis_layout,(ViewGroup)findViewById(R.id.emojislayout));
            myFrame.addView(emojisView);
        }else if (choiceInput==2){
            myTextView.setText(R.string.themechoice);
            View themesView=(View)inflater.inflate(R.layout.themes_layout,(ViewGroup)findViewById(R.id.themesLayout));
            myFrame.addView(themesView);
        } else if (choiceInput == 3) {
            myTextView.setText(R.string.groupchoice);
            View groupsView=(View)inflater.inflate(R.layout.groups_layout,(ViewGroup)findViewById(R.id.groups_layout));
            myFrame.addView(groupsView);
        }

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
                myInterstitial.show();
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
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
