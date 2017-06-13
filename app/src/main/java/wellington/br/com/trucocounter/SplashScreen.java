package wellington.br.com.trucocounter;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;

public class SplashScreen extends AppCompatActivity {

    private int SPLASH_SCREEN_TIME = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                startMainActivity();
            }
        }, SPLASH_SCREEN_TIME);


    }


    private void startMainActivity() {
        Bundle bundle = new Bundle();
        if (bundle != null){

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
