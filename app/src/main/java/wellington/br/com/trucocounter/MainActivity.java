package wellington.br.com.trucocounter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
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

    @Bind(R.id.tvContadorThey)
    TextView tvContadorThey;
    @Bind(R.id.tvContadorWe)
    TextView tvContadorWe;



    int we = 0;
    int min = 0;
    int they = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        weCount();
        theyCount();

    }

    private void theyCount(){
        llThey.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            @Override
            public void onSwipeTop() {
                super.onSwipeTop();
                if(they >= min){
                    they = they + 1;
                    tvContadorThey.setText(String.valueOf(they));
                }

            }
            @Override
            public void onSwipeBottom() {
                super.onSwipeBottom();
                if(they > min){
                    they = they - 1;
                    tvContadorThey.setText(String.valueOf(they));
                }
            }

        });
    }

    private void weCount(){
        llWe.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            @Override
            public void onSwipeTop() {
                super.onSwipeTop();
                if(we >= min){
                    we = we + 1;
                    tvContadorWe.setText(String.valueOf(we));
                }

            }
            @Override
            public void onSwipeBottom() {
                super.onSwipeBottom();
                if(we > min){
                    we = we - 1;
                    tvContadorWe.setText(String.valueOf(we));
                }
            }

        });
    }



}
