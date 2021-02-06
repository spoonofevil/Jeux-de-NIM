package spoony.jeuxdenim.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import spoony.jeuxdenim.PebbleOnClickListener;
import spoony.jeuxdenim.Pebble;
import spoony.jeuxdenim.R;

public class Pebble1Activity extends AppCompatActivity {


    Pebble peble1_1 = new Pebble("blank", 1, 1);
    Pebble peble1_2 = new Pebble("blank", 1, 2);
    Pebble peble1_3 = new Pebble("blank", 1, 3);
    Pebble peble2_1 = new Pebble("blank", 2, 1);
    Pebble peble2_2 = new Pebble("blank", 2, 2);
    Pebble peble2_3 = new Pebble("blank", 2, 3);
    Pebble peble2_4 = new Pebble("blank", 2, 4);
    Pebble peble2_5 = new Pebble("blank", 2, 5);
    Pebble peble3_1 = new Pebble("blank", 3, 1);
    Pebble peble3_2 = new Pebble("blank", 3, 2);
    Pebble peble3_3 = new Pebble("blank", 3, 3);
    Pebble peble3_4 = new Pebble("blank", 3, 4);
    Pebble peble3_5 = new Pebble("blank", 3, 5);
    Pebble peble3_6 = new Pebble("blank", 3, 6);
    Pebble peble3_7 = new Pebble("blank", 3, 7);

    public Pebble[][] pebbles = new Pebble[][]{
            {
                    peble1_1, peble1_2, peble1_3
            },
            {
                    peble2_1, peble2_2, peble2_3, peble2_4, peble2_5
            },
            {
                    peble3_1, peble3_2, peble3_3, peble3_4, peble3_5, peble3_6, peble3_7
            },
    };

    public ImageView pebbleline1_1;
    public ImageView pebbleline1_2;
    public ImageView pebbleline1_3;
    public ImageView pebbleline2_1;
    public ImageView pebbleline2_2;
    public ImageView pebbleline2_3;
    public ImageView pebbleline2_4;
    public ImageView pebbleline2_5;
    public ImageView pebbleline3_1;
    public ImageView pebbleline3_2;
    public ImageView pebbleline3_3;
    public ImageView pebbleline3_4;
    public ImageView pebbleline3_5;
    public ImageView pebbleline3_6;
    public ImageView pebbleline3_7;
    public Boolean player;
    public Button confirm;
    private Button undo;
    private TextView admin;
    public boolean headmin = false;
    public List<Pebble[][]> saveboard = new ArrayList<>();
    public static int tour;
    public String colorplayer;
    public boolean lignechoisi;
    public Boolean victory = false;
    private Pebble1Activity activity_pebble;

    public void ligneclickable(int Ligne, ImageView[][] pebblesimg){
        int L1 = 1;
        int L2 = 1;
        switch (Ligne){
            case 1:
                L1=2;
                L2=3;
                break;
            case 2:
                L1=1;
                L2=3;
                break;
            case 3:
                L1=2;
                L2=1;
                break;
            default:
                Toast.makeText(activity_pebble, "C PASENS2 ARRIVER TT CA", Toast.LENGTH_SHORT).show();
        }
        for (ImageView pebleIMG : pebblesimg[L1-1]) {
            pebleIMG.setClickable(false);
        }
        for (ImageView pebleIMG : pebblesimg[L2-1]) {
            pebleIMG.setClickable(false);
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pebble1_activity);
        saveboard.add(getPebblesCopy());
        tour = 0;

        boolean firstmove = true;

        this.activity_pebble = this;

        this.undo = findViewById(R.id.undo);
        this.admin = findViewById(R.id.whosplayin);

        admin.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                headmin = true;
                undo.setVisibility(v.VISIBLE);
                return false;
            }
        });


        this.pebbleline1_1 = findViewById(R.id.pebble_line1_1);
        this.pebbleline1_2 = findViewById(R.id.pebble_line1_2);
        this.pebbleline1_3 = findViewById(R.id.pebble_line1_3);
        this.pebbleline2_1 = findViewById(R.id.pebble_line2_1);
        this.pebbleline2_2 = findViewById(R.id.pebble_line2_2);
        this.pebbleline2_3 = findViewById(R.id.pebble_line2_3);
        this.pebbleline2_4 = findViewById(R.id.pebble_line2_4);
        this.pebbleline2_5 = findViewById(R.id.pebble_line2_5);
        this.pebbleline3_1 = findViewById(R.id.pebble_line3_1);
        this.pebbleline3_2 = findViewById(R.id.pebble_line3_2);
        this.pebbleline3_3 = findViewById(R.id.pebble_line3_3);
        this.pebbleline3_4 = findViewById(R.id.pebble_line3_4);
        this.pebbleline3_5 = findViewById(R.id.pebble_line3_5);
        this.pebbleline3_6 = findViewById(R.id.pebble_line3_6);
        this.pebbleline3_7 = findViewById(R.id.pebble_line3_7);

        ImageView[][] pebblesimg = new ImageView[][]{
                {
                        pebbleline1_1, pebbleline1_2, pebbleline1_3
                },
                {
                        pebbleline2_1, pebbleline2_2, pebbleline2_3, pebbleline2_4, pebbleline2_5
                },
                {
                        pebbleline3_1, pebbleline3_2, pebbleline3_3, pebbleline3_4, pebbleline3_5, pebbleline3_6, pebbleline3_7
                },
        };

        //true = bleu /false = rouge
        player = true;


        this.confirm = findViewById(R.id.confirm);
        this.confirm.setClickable(false);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                undo.setClickable(true);
                victory=true;
                for (Pebble[] pebbltemp : pebbles) {
                    for (Pebble peble : pebbltemp) {
                        if (peble.getColor().equals("blank")) {
                            pebblesimg[peble.getLigne() - 1][peble.getNum() - 1].setClickable(true);
                            victory = false;
                            //Toast.makeText(Pebble1Activity.this, "oh le nul", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                saveboard.add(getPebblesCopy());
                for (Pebble[] pebbltemp : saveboard.get(tour)) {
                    for (Pebble peble : pebbltemp) {
                        ImageView pebbleimg = pebblesimg[peble.getLigne() - 1][peble.getNum() - 1];
                        if (!peble.getColor().equals("blank")) {
                            pebbleimg.setClickable(false);
                        }
                    }
                }
                player = !player;
                if (player){
                    colorplayer = "bleu";
                }else{
                    colorplayer = "rouge";
                }
                admin.setText("Au tour du joueur " + colorplayer);
                tour++;
                confirm.setClickable(false);
                if(victory){
                    Toast.makeText(activity_pebble, "LA CLASSE A DALLAS", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder popupvictory = new AlertDialog.Builder(activity_pebble);
                    popupvictory.setMessage("Victoire du joueur " + colorplayer + "\n" + " En " + tour + " tours !");
                    popupvictory.setTitle("Fin de partie");
                    popupvictory.setPositiveButton("Play again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent new_pebble_game = new Intent(getApplicationContext(), Pebble1Activity.class);
                            startActivity(new_pebble_game);
                            finish();
                        }
                    });
                    popupvictory.setNegativeButton("MENU", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent Menu = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(Menu);
                            finish();
                        }
                    });
                    popupvictory.show();
                }
            }
        });
        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tour != 0){
                for (Pebble[] pebbltemp : saveboard.get(tour - 1)) {
                    for (Pebble peble : pebbltemp) {
                        ImageView pebbleimg = pebblesimg[peble.getLigne() - 1][peble.getNum() - 1];
                        Pebble pebblenow = pebbles[peble.getLigne() - 1][peble.getNum() - 1];
                        switch (peble.getColor()) {
                            case "blank":
                                pebbleimg.setImageResource(R.drawable.empty_pebble);
                                pebbleimg.setClickable(true);
                                pebblenow.setColor("blank");
                                break;
                            case "b":
                                pebbleimg.setImageResource(R.drawable.blue_pebble);
                                pebblenow.setColor("b");
                                break;
                            case "r":
                                pebbleimg.setImageResource(R.drawable.red_pebble);
                                pebblenow.setColor("r");
                                break;
                            default:
                                Toast.makeText(Pebble1Activity.this, "OULAAAAAAAAAAA", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                    saveboard.remove(tour);
                    player=!player;
                    if (player){
                        colorplayer = "bleu";
                    }else{
                        colorplayer = "rouge";
                    }
                    admin.setText("Au tour du joueur" + colorplayer);
                    tour--;
                } else{
                    Toast.makeText(Pebble1Activity.this, "premier tour : pas de undo possible", Toast.LENGTH_SHORT).show();
                }
            }
        });
        pebbleline1_1.setOnClickListener(new PebbleOnClickListener(peble1_1, this,pebblesimg));

        /* pebbleline1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (peble1_1.getColor()){
                    case "blank":
                        if(player){
                            pebbleline1_1.setImageResource(R.drawable.blue_pebble);
                            peble1_1.setColor("b");
                        }else {
                            pebbleline1_1.setImageResource(R.drawable.red_pebble);
                            peble1_1.setColor("r");
                        }
                        break;
                    case "b":
                        if(player){
                            pebbleline1_1.setImageResource(R.drawable.empty_pebble);
                            peble1_1.setColor("blank");
                        }else {
                            Toast.makeText(Pebble1Activity.this, "Stop trying to cheat", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case "r":

                        if(!player){
                            pebbleline1_1.setImageResource(R.drawable.empty_pebble);
                            peble1_1.setColor("blank");
                        }else {
                            Toast.makeText(Pebble1Activity.this, "Stop trying to cheat", Toast.LENGTH_SHORT).show();
                        }
                            break;
                    default:
                        Toast.makeText(Pebble1Activity.this, "MAIS T UNE MEEEEERDE", Toast.LENGTH_LONG).show();
                }
            }
        });
    */
        pebbleline1_2.setOnClickListener(new PebbleOnClickListener(peble1_2, this,pebblesimg));
        pebbleline1_3.setOnClickListener(new PebbleOnClickListener(peble1_3, this,pebblesimg));
        pebbleline2_1.setOnClickListener(new PebbleOnClickListener(peble2_1, this,pebblesimg));
        pebbleline2_2.setOnClickListener(new PebbleOnClickListener(peble2_2, this,pebblesimg));
        pebbleline2_3.setOnClickListener(new PebbleOnClickListener(peble2_3, this,pebblesimg));
        pebbleline2_4.setOnClickListener(new PebbleOnClickListener(peble2_4, this,pebblesimg));
        pebbleline2_5.setOnClickListener(new PebbleOnClickListener(peble2_5, this,pebblesimg));
        pebbleline3_1.setOnClickListener(new PebbleOnClickListener(peble3_1, this,pebblesimg));
        pebbleline3_2.setOnClickListener(new PebbleOnClickListener(peble3_2, this,pebblesimg));
        pebbleline3_3.setOnClickListener(new PebbleOnClickListener(peble3_3, this,pebblesimg));
        pebbleline3_4.setOnClickListener(new PebbleOnClickListener(peble3_4, this,pebblesimg));
        pebbleline3_5.setOnClickListener(new PebbleOnClickListener(peble3_5, this,pebblesimg));
        pebbleline3_6.setOnClickListener(new PebbleOnClickListener(peble3_6, this,pebblesimg));
        pebbleline3_7.setOnClickListener(new PebbleOnClickListener(peble3_7, this,pebblesimg));
    }

    Pebble[][] getPebblesCopy() {
        return new Pebble[][]{
                {
                        peble1_1.clone(), peble1_2.clone(), peble1_3.clone()
                },
                {
                        peble2_1.clone(), peble2_2.clone(), peble2_3.clone(), peble2_4.clone(), peble2_5.clone()
                },
                {
                        peble3_1.clone(), peble3_2.clone(), peble3_3.clone(), peble3_4.clone(), peble3_5.clone(), peble3_6.clone(), peble3_7.clone()
                },
        };
    }
}