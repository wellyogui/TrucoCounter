package wellington.br.com.tentoCounter;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wellington.br.com.trucocounter.R;

public class tutorialActivity extends AppCompatActivity {

    @Bind(R.id.tvSwipeDown)
    TextView tvSwipeDown;
    @Bind(R.id.tvSwipeUp)
    TextView tvSwipeUp;
    @Bind(R.id.tvDeck)
    TextView tvDeck;

    private AdView mAdView;
    boolean main;
    Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        ButterKnife.bind(this);

        mAdView = (AdView) findViewById(R.id.adViewSkin);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/chalk.ttf");

        tvDeck.setTypeface(type);
        tvSwipeDown.setTypeface(type);
        tvSwipeUp.setTypeface(type);

        String flag = getIntent().getStringExtra("I_CAME_FROM");
        if (flag.equals("main")) {
            main = true;
        } else if(flag.equals("inverse")){
            main = false;
        }
    }


    @OnClick(R.id.ibBack)
    public void backTo(){
        if(main){
            intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("I_CAME_FROM", "tutorial");
            startActivity(intent);

        } else {
            intent = new Intent(getApplicationContext(), InverseActivity.class);
            intent.putExtra("I_CAME_FROM", "tutorial");
            startActivity(intent);
        }

    }
}
