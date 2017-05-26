package wellington.br.com.trucocounter;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InverseActivity extends AppCompatActivity {

    @Bind(R.id.llTheyInverse)
    LinearLayout llThey;
    @Bind(R.id.llWeInverse)
    LinearLayout llWe;

    @Bind(R.id.tvNameTheyInverse)
    TextView tvNameThey;
    @Bind(R.id.tvNameWeInverse)
    TextView tvNameWe;
    @Bind(R.id.tvContadorTheyInverse)
    TextView tvContadorThey;
    @Bind(R.id.tvContadorWeInverse)
    TextView tvContadorWe;



    private int we = 0;
    private final int min = 0;
    private int they = 0;
    private int max = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inverse);
        ButterKnife.bind(this);

        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/chalk.ttf");

        tvContadorThey.setTypeface(type);
        tvContadorWe.setTypeface(type);
        tvNameThey.setTypeface(type);
        tvNameWe.setTypeface(type);

        weCount();
        theyCount();
    }

    private void theyCount() {
        tvContadorThey.setText(String.valueOf(they));
        llThey.setOnTouchListener(new OnSwipeTouchListener(InverseActivity.this) {
            @Override
            public void onSwipeTop() {
                super.onSwipeTop();
                if (they > min) {
                    they = they - 1;
                    tvContadorThey.setText(String.valueOf(they));
                }

            }

            @Override
            public void onSwipeBottom() {
                super.onSwipeBottom();

                if (they >= min && they <= max - 1) {
                    they = they + 1;
                    tvContadorThey.setText(String.valueOf(they));

                }

            }

        });
    }

    private void weCount() {
        tvContadorWe.setText(String.valueOf(we));
        llWe.setOnTouchListener(new OnSwipeTouchListener(InverseActivity.this) {
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
