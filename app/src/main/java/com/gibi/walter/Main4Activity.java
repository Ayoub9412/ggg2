package com.gibi.walter;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import org.w3c.dom.Text;

public class Main4Activity extends AppCompatActivity {

    private Toolbar myToolbar;
    private Button nextBtn;
    private TextView myTextView,myStatusText;
    private ProgressBar myProgressBar;
    int myProgress = 0;
    private Handler h= new Handler();
    AdView myAdView;
    InterstitialAd myInterstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        myToolbar=(Toolbar)findViewById(R.id.myToolbar);
        myToolbar.setTitle("");
        setSupportActionBar(myToolbar);

        final Intent i = new Intent(new Intent(Main4Activity.this,Main5Activity.class));

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

        nextBtn=(Button)findViewById(R.id.myNextButton);
        myTextView=(TextView)findViewById(R.id.myTextView);
        myProgressBar=(ProgressBar)findViewById(R.id.myProgressBar);
        myStatusText=(TextView)findViewById(R.id.myStatusText);
        nextBtn.setVisibility(View.GONE);

        Toast.makeText(this, "تثبيت...", Toast.LENGTH_SHORT).show();


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (myProgress<100){

                    myProgress++;
                    android.os.SystemClock.sleep(200);
                    h.post(new Runnable() {
                        @Override
                        public void run() {
                            myProgressBar.setProgress(myProgress);
                            myStatusText.setText((myProgress+"%").toString());
                        }
                    });
                }
                h.post(new Runnable() {
                    @Override
                    public void run() {
                        myTextView.setText((getString(R.string.done_patching)).toString());
                        nextBtn.setVisibility(View.VISIBLE);
                        Toast.makeText(Main4Activity.this, "تم التثبيت!!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }).start();
        
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
