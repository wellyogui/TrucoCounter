package wellington.br.com.trucocounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);

        Bundle bundle = new Bundle();
        if (bundle != null){

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
