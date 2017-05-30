package wellington.br.com.trucocounter;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

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

    @Bind(R.id.btn)
    Button btn;


    private int we = 0;
    private final int min = 0;
    private int they = 0;
    private int max = 12;
    Bundle bundle = new Bundle();



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

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(MainActivity.this, btn);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_inverter:
                                inverse();
                                break;
                            case R.id.menu_config:
                                config();
                                break;
                            case R.id.menu_zerar:
                                resetGame();
                                break;
                            default:
                                Toast.makeText(
                                        MainActivity.this,
                                        "Erro",
                                        Toast.LENGTH_SHORT
                                ).show();
                                break;
                        }
                        return true;
                    }
                });

                popup.show(); //showing popup menu
            }
        });


        weCount();
        theyCount();
    }

    public void inverse() {
        if (bundle != null) {
            Intent intent = new Intent(this, InverseActivity.class);
            startActivity(intent);
        }
    }

    public void config(){
        if(bundle != null){
            Intent intent = new Intent(this, Config_activity.class);
            startActivity(intent);
        }
    }

    public void resetGame() {
        we = 0;
        they = 0;

        tvContadorWe.setText(String.valueOf(we));
        tvContadorThey.setText(String.valueOf(they));

        Toast.makeText(MainActivity.this, "O jogo foi zerado", Toast.LENGTH_SHORT).show();

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
