package wellington.br.com.tentoCounter;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import info.hoang8f.widget.FButton;
import wellington.br.com.trucocounter.R;

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
    @Bind(R.id.fbMenuInverse)
    FButton fbMenuInverse;

    private String nameThey;
    private String nameWe;
    private static int we = 0;
    private final int min = 0;
    private static int they = 0;
    private int max = 12;
    private static boolean deckInverse;
    private Bundle bundle;
    private boolean main = false;
    private int counter = 0;

    AlertDialog alertDialog;

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
        fbMenuInverse.setTypeface(type);

        bundle = getIntent().getExtras();

        String flag = getIntent().getStringExtra("I_CAME_FROM");
        if (flag.equals("main")) {
            main = true;
            we = bundle.getInt("WeCount");
            they = bundle.getInt("TheyCount");
            deckInverse = bundle.getBoolean("Deck");
            nameThey = bundle.getString("nameThey");
            nameWe = bundle.getString("nameWe");
            if (nameThey != null && nameWe != null) {
                tvNameWe.setText(nameWe);
                tvNameThey.setText(nameThey);
            }

        }
        if (flag != null) {
            if (flag.equals("config")) {
                Bundle b = getIntent().getExtras();
                nameThey = b.getString("nameThey");
                nameWe = b.getString("nameWe");
                if (nameWe != null && nameThey != null) {
                    tvNameWe.setText(nameWe);
                    tvNameThey.setText(nameThey);
                }

            }

            if(flag.equals("tutorial")){

            }
        }

        if (deckInverse) {
            ivDeckWeInverse.setVisibility(View.VISIBLE);
            ivDeckTheyInverse.setVisibility(GONE);
        } else {
            ivDeckTheyInverse.setVisibility(View.VISIBLE);
            ivDeckWeInverse.setVisibility(GONE);
        }

        fbMenuInverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(InverseActivity.this, fbMenuInverse);
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
                                callDialogReset();
                                break;
                            case R.id.menu_tutorial:
                                tutorial();
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

    private void tutorial() {
        if (bundle != null) {
            Intent intent = new Intent(this, tutorialActivity.class);
            intent.putExtra("I_CAME_FROM", "inverse");
            startActivity(intent);
        }
    }

    public void deck() {
        if (counter % 2 == 0) {
            ivDeckWeInverse.setVisibility(View.VISIBLE);
            ivDeckTheyInverse.setVisibility(GONE);
        } else {
            ivDeckTheyInverse.setVisibility(View.VISIBLE);
            ivDeckWeInverse.setVisibility(GONE);
        }
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
            intent.putExtra("I_CAME_FROM", "inverse");
            if (nameThey != null && nameWe != null) {
                intent.putExtra("nameWe", nameWe);
                intent.putExtra("nameThey", nameThey);
            }
            startActivity(intent);
        }
    }

    public void config() {
        if (bundle != null) {
            Intent intent = new Intent(this, Config_activity.class);
            intent.putExtra("I_CAME_FROM", "inverse");
            startActivity(intent);
        }
    }

    public void resetGame() {
        we = 0;
        they = 0;
        counter = 0;

        tvContadorWe.setText(String.valueOf(we));
        tvContadorThey.setText(String.valueOf(they));
        ivDeckTheyInverse.setVisibility(GONE);
        ivDeckWeInverse.setVisibility(View.VISIBLE);
        deckInverse = true;
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
                    counter++;
                    deck();
                    if (they == max) {
                        callDialogThey();
                    }
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
                    counter++;
                    deck();
                    if (we == max) {
                        callDialogWe();
                    }
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


    public void callDialogReset() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Vai desistir do jogo?");
        builder.setMessage("O jogo será zerado!");
        builder.setIcon(R.drawable.warning);
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                resetGame();
            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(getApplicationContext(), "Vamo ganhar essa porra!", Toast.LENGTH_LONG).show();
            }
        });

        alertDialog = builder.create();

        alertDialog.show();
    }

    public void callDialogThey() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Não acredito que perdemos!");
        builder.setMessage("Vamos ganhar desses patinhos dessa vez?");
        builder.setIcon(R.drawable.trophy);
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                resetGame();
            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(getApplicationContext(), "Que vergonha!", Toast.LENGTH_LONG).show();
            }
        });

        alertDialog = builder.create();

        alertDialog.show();
    }

    public void callDialogWe() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Ganhamos desses pato!");
        builder.setMessage("Os pato vão querer revanche?");
        builder.setIcon(R.drawable.trophy);
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                resetGame();
            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(getApplicationContext(), "Chupa patinhos!", Toast.LENGTH_LONG).show();
            }
        });

        alertDialog = builder.create();

        alertDialog.show();
    }

}
