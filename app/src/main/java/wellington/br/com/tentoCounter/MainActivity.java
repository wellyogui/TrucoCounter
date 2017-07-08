package wellington.br.com.tentoCounter;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import butterknife.Bind;
import butterknife.ButterKnife;
import info.hoang8f.widget.FButton;
import wellington.br.com.trucocounter.R;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.llThey)
    LinearLayout llThey;
    @Bind(R.id.llWe)
    LinearLayout llWe;
    @Bind(R.id.fl)
    FrameLayout fl;

    @Bind(R.id.tvNameThey)
    TextView tvNameThey;
    @Bind(R.id.tvNameWe)
    TextView tvNameWe;
    @Bind(R.id.tvContadorThey)
    TextView tvContadorThey;
    @Bind(R.id.tvContadorWe)
    TextView tvContadorWe;
    @Bind(R.id.ivDeckThey)
    ImageView ivDeckThey;
    @Bind(R.id.ivDeckWe)
    ImageView ivDeckWe;
    @Bind(R.id.fbMenu)
    FButton fbMenu;

    private static int we = 0;
    private final int min = 0;
    private static int they = 0;
    private int max = 12;
    private static boolean inverse;
    private static boolean deck;
    Bundle bundle = new Bundle();
    private static String nameWe;
    private static String nameThey;
    private String flag;
    private int counter = 0;


    AlertDialog alertDialog;

    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mAdView = (AdView) findViewById(R.id.adViewSkin);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/chalk.ttf");

        tvContadorThey.setTypeface(type);
        tvContadorWe.setTypeface(type);
        tvContadorWe.setTypeface(type);
        tvNameThey.setTypeface(type);
        tvNameWe.setTypeface(type);
        fbMenu.setTypeface(type);

        fbMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(MainActivity.this, fbMenu);
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


        if (inverse) {
            bundle = getIntent().getExtras();

            flag = getIntent().getStringExtra("I_CAME_FROM");
            if (flag != null) {
                if (flag.equals("inverse")) {
                    we = bundle.getInt("WeCount");
                    they = bundle.getInt("TheyCount");
                    deck = bundle.getBoolean("Deck");
                    nameThey = bundle.getString("nameThey");
                    nameWe = bundle.getString("nameWe");
                    if (nameThey != null && nameWe != null) {
                        tvNameThey.setText(nameThey);
                        tvNameWe.setText(nameWe);
                    }
                    if (counter % 2 == 0) {
                        ivDeckWe.setVisibility(View.VISIBLE);
                        ivDeckThey.setVisibility(GONE);
                    } else {
                        ivDeckThey.setVisibility(View.VISIBLE);
                        ivDeckWe.setVisibility(GONE);
                    }
                } else if (flag.equals("config")) {
                    Bundle b = getIntent().getExtras();
                    nameThey = b.getString("nameThey");
                    nameWe = b.getString("nameWe");
                    if (nameWe != null && nameThey != null) {
                        tvNameWe.setText(nameWe);
                        tvNameThey.setText(nameThey);
                    }
                }
            }
        } else {
            flag = getIntent().getStringExtra("I_CAME_FROM");
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
            }
        }

        weCount();
        theyCount();
        deck();
    }

    private void tutorial() {
        if (bundle != null) {
            Intent intent = new Intent(this, tutorialActivity.class);
            intent.putExtra("I_CAME_FROM", "main");
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
    }

    public void deck(){
        if (counter % 2 == 0) {
            ivDeckWe.setVisibility(View.VISIBLE);
            ivDeckThey.setVisibility(GONE);
        } else {
            ivDeckThey.setVisibility(View.VISIBLE);
            ivDeckWe.setVisibility(GONE);
        }
    }

    public void inverse() {
        if (bundle != null) {
            Intent intent = new Intent(this, InverseActivity.class);
            bundle.putInt("TheyCount", they);
            bundle.putInt("WeCount", we);
            bundle.putBoolean("Deck", deck);
            intent.putExtras(bundle);
            intent.putExtra("I_CAME_FROM", "main");
            inverse = true;
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
            intent.putExtra("I_CAME_FROM", "main");
            startActivity(intent);
        }
    }

    public void callDialogReset(){
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

    public void callDialogThey(){
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

    public void callDialogWe(){
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


    public void resetGame() {
        we = 0;
        they = 0;
        counter = 0;

        tvContadorWe.setText(String.valueOf(we));
        tvContadorThey.setText(String.valueOf(they));
        ivDeckThey.setVisibility(GONE);
        ivDeckWe.setVisibility(View.VISIBLE);
        deck = true;
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
                    deck = false;
                    counter ++;

                    if(they == max){
                        callDialogThey();
                    }
                    deck();
                }

            }

            @Override
            public void onSwipeBottom() {
                super.onSwipeBottom();
                if (they > min) {
                    they = they - 1;
                    tvContadorThey.setText(String.valueOf(they));
                    counter --;

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
                    tvContadorWe.setText(String.valueOf(we));;
                    deck = true;
                    counter ++;
                    deck();

                    if(we >= max){
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
                    counter --;
                }
            }

        });
    }


}
