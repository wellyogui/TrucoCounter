package wellington.br.com.trucocounter;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.view.View.GONE;

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
    @Bind(R.id.ivDeckTheyInverse)
    ImageView ivDeckTheyInverse;
    @Bind(R.id.ivDeckWeInverse)
    ImageView ivDeckWeInverse;
    @Bind(R.id.btnMenuInverse)
    Button btnMenuInverse;


    private int we = 0;
    private final int min = 0;
    private int they = 0;
    private int max = 12;
    private static boolean deckInverse;
    private Bundle bundle;

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

        bundle = getIntent().getExtras();

        we = bundle.getInt("WeCount");
        they = bundle.getInt("TheyCount");
        deckInverse = bundle.getBoolean("Deck");

        if (deckInverse) {
            ivDeckWeInverse.setVisibility(View.VISIBLE);
            ivDeckTheyInverse.setVisibility(GONE);
        } else {
            ivDeckTheyInverse.setVisibility(View.VISIBLE);
            ivDeckWeInverse.setVisibility(GONE);
        }

        btnMenuInverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(InverseActivity.this, btnMenuInverse);
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
                                        InverseActivity.this,
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

    @Override
    public void onBackPressed() {
    }


    public void inverse() {
        if (bundle != null) {
            Intent intent = new Intent(this, MainActivity.class);
            bundle.putInt("TheyCount", they);
            bundle.putInt("WeCount", we);
            bundle.putBoolean("Deck", deckInverse);
            intent.putExtras(bundle);
            intent.putExtra("classFrom", InverseActivity.class.toString());
            startActivity(intent);
        }
    }

    public void config() {
        if (bundle != null) {
            Intent intent = new Intent(this, Config_activity.class);
            startActivity(intent);
        }
    }

    public void resetGame() {
        we = 0;
        they = 0;

        tvContadorWe.setText(String.valueOf(we));
        tvContadorThey.setText(String.valueOf(they));
        deckInverse = true;
        ivDeckTheyInverse.setVisibility(GONE);
        ivDeckWeInverse.setVisibility(View.VISIBLE);
        Toast.makeText(InverseActivity.this, "O Jogo Foi Zerado", Toast.LENGTH_SHORT).show();

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
                    ivDeckTheyInverse.setVisibility(View.VISIBLE);
                    ivDeckWeInverse.setVisibility(GONE);
                    deckInverse = false;
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
                    ivDeckTheyInverse.setVisibility(GONE);
                    ivDeckWeInverse.setVisibility(View.VISIBLE);
                    deckInverse = true;
                }

            }

            @Override
            public void onSwipeBottom() {
                super.onSwipeBottom();
                if (we > min) {
                    we = we - 1;
                    tvContadorWe.setText(String.valueOf(we));
                    deckInverse = true;
                }
            }

        });
    }


}
