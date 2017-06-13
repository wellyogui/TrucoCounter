package wellington.br.com.trucocounter;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Config_activity extends AppCompatActivity {

    @Bind(R.id.tvNameTheyConfig)
    EditText tvNameTheyConfig;
    @Bind(R.id.tvNameTheyLabel)
    TextView tvNameTheyLabel;
    @Bind(R.id.tvNameWeLabel)
    TextView tvNameWeLabel;
    @Bind(R.id.tvNameWeConfig)
    EditText tvNameWeConfig;
    @Bind(R.id.tvPontMax)
    EditText tvPontMax;
    @Bind(R.id.tvPontMaxLabel)
    TextView tvPontMaxLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        ButterKnife.bind(this);

        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/chalk.ttf");

        tvNameTheyLabel.setTypeface(type);
        tvNameWeLabel.setTypeface(type);
        tvPontMax.setTypeface(type);
        tvPontMaxLabel.setTypeface(type);
        tvNameTheyConfig.setTypeface(type);
        tvNameWeConfig.setTypeface(type);
    }
}
