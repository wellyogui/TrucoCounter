package wellington.br.com.trucocounter;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.llThey)
    LinearLayout llThey;
    @Bind(R.id.llWe)
    LinearLayout llWe;

    @Bind(R.id.tvNameThey)
    TextView tvNameThey;
    @Bind(R.id.tvNameWe)
    TextView tvNameWe;
    @Bind(R.id.tvContadorThey)
    TextView tvContadorThey;
    @Bind(R.id.tvContadorWe)
    TextView tvContadorWe;




    private int we = 0;
    private final int min = 0;
    private int they = 0;
    private int max = 12;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/chalk.ttf");

        tvContadorThey.setTypeface(type);
        tvContadorWe.setTypeface(type);
        tvNameThey.setTypeface(type);
        tvNameWe.setTypeface(type);

        weCount();
        theyCount();

    }

    @OnClick(R.id.btn)
    public void inverse(){
        Bundle bundle = new Bundle();
        if (bundle != null){
            Intent intent = new Intent(this, InverseActivity.class);
            startActivity(intent);
        }
    }

    private void theyCount() {
        tvContadorThey.setText(String.valueOf(they));
        llThey.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            @Override
            public void onSwipeTop() {
                super.onSwipeTop();
                if (they >= min && they <= max - 1) {
                    they = they + 1;
                    tvContadorThey.setText(String.valueOf(they));

                }

            }

            @Override
            public void onSwipeBottom() {
                super.onSwipeBottom();
                if (they > min) {
                    they = they - 1;
                    tvContadorThey.setText(String.valueOf(they));
                }
            }

        });
    }

    private void weCount() {
        tvContadorWe.setText(String.valueOf(we));
        llWe.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            @Override
            public void onSwipeTop() {
                super.onSwipeTop();
                if (we >= min && we <= max - 1) {
                    we = we + 1;
                    tvContadorWe.setText(String.valueOf(we));
                }

            }

            @Override
            public void onSwipeBottom() {
                super.onSwipeBottom();
                if (we > min) {
                    we = we - 1;
                    tvContadorWe.setText(String.valueOf(we));
                }
            }

        });
    }


}
