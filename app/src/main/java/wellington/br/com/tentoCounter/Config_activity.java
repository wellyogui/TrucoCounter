package wellington.br.com.tentoCounter;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import info.hoang8f.widget.FButton;
import wellington.br.com.trucocounter.R;

public class Config_activity extends AppCompatActivity {

    @Bind(R.id.tvNameTheyConfig)
    EditText etNameTheyConfig;
    @Bind(R.id.tvNameTheyLabel)
    TextView tvNameTheyLabel;
    @Bind(R.id.tvNameWeLabel)
    TextView tvNameWeLabel;
    @Bind(R.id.tvNameWeConfig)
    EditText etNameWeConfig;
//    @Bind(R.id.tvPontMax)
//    EditText etPontMax;
//    @Bind(R.id.tvPontMaxLabel)
//    TextView tvPontMaxLabel;
    @Bind(R.id.fbCancel)
    FButton fbCancel;
    @Bind(R.id.fbSave)
    FButton fbSave;

    public static String nameThey;
    public static String nameWe;

    Bundle bundle = new Bundle();
    boolean isChanged = false;
    boolean main = false;
    Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        ButterKnife.bind(this);

        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/chalk.ttf");

        tvNameTheyLabel.setTypeface(type);
        tvNameWeLabel.setTypeface(type);
//        etPontMax.setTypeface(type);
//        tvPontMaxLabel.setTypeface(type);
        etNameTheyConfig.setTypeface(type);
        etNameWeConfig.setTypeface(type);
        fbCancel.setTypeface(type);
        fbSave.setTypeface(type);

        String flag = getIntent().getStringExtra("I_CAME_FROM");
        if(flag.equals("main")){
            main = true;
        }
        if (flag.equals("inverse")){
            main = false;
        }
    }

    @OnClick(R.id.fbSave)
    public void onSaveConfig(){
        nameThey = etNameTheyConfig.getText().toString();
        nameWe = etNameWeConfig.getText().toString();
        if(!nameWe.equals("") && !nameThey.equals("")){


            isChanged = true;

            Toast.makeText(getApplicationContext(), "Os nomes foram alterados.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Nada foi alterado", Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.fbCancel)
    public void onCancelConfig() {

        etNameWeConfig.setText("");
        etNameTheyConfig.setText("");

        isChanged = false;

        Toast.makeText(getApplicationContext(), "As alterações foram canceladas", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.ibBack)
    public void backTo(){
        if(main){
            intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("I_CAME_FROM", "config");
            intent.putExtras(bundle);
            if (isChanged){

                intent.putExtra("nameWe", nameWe);
                intent.putExtra("nameThey", nameThey);
            }
            startActivity(intent);

        } else {
            intent = new Intent(getApplicationContext(), InverseActivity.class);
            intent.putExtra("I_CAME_FROM", "config");
            intent.putExtras(bundle);
            if (isChanged){

                intent.putExtra("nameWe", nameWe);
                intent.putExtra("nameThey", nameThey);;
            }

            startActivity(intent);
        }

    }
}
