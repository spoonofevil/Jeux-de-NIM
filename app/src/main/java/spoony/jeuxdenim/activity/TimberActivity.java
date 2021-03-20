package spoony.jeuxdenim.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import spoony.jeuxdenim.R;

public class TimberActivity extends AppCompatActivity {
        Button confirmnbdom,undo;
        EditText nbofdom;
        LinearLayout Layout;
        List<String> jeu;
        int tour;
        String colorplayer;
        TextView colorplayertxt;
        List<List<String>> saves;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_timber);
            undo=findViewById(R.id.undoBtnTimber);
            colorplayer="Bleu";
            colorplayertxt=findViewById(R.id.coloplayertext);
            undo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (saves!=null && !saves.isEmpty() && tour>0){
                        loadsave(saves.get(tour-1));
                        saves.remove(tour);
                        tour--;
                    }else{
                        Toast.makeText(TimberActivity.this, "First round : no undo available", Toast.LENGTH_SHORT).show();
                    }


                }
            });
            Layout=findViewById(R.id.Tableau);
            nbofdom=findViewById(R.id.numberof_domino);
            confirmnbdom=findViewById(R.id.confirm_number);
            confirmnbdom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tour=0;

                    Layout.removeAllViews();
                    saves=new ArrayList<List<String >>();
                    jeu =  new ArrayList<>();
                    Random rand = new Random();
                    if(!TextUtils.isEmpty(nbofdom.getText())) {
                        int nbdom= Integer.parseInt(nbofdom.getText().toString());
                        for(int i=0;i<nbdom;i++){
                            ImageView domino = new ImageView(Layout.getContext());
                            int test = rand.nextInt(2);
                            LinearLayout.LayoutParams dominparam =new LinearLayout.LayoutParams( 50,100) ;
                            //System.out.println(test);
                            int finalI = i;
                            if (test==1) {
                                //System.out.println("droite");
                                jeu.add("D");
                                domino.setImageResource(R.drawable.dominoright);
                                domino.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        droite(finalI);
                                    }
                                });
                            }
                            else{
                                //System.out.println("gauche");
                                domino.setImageResource(R.drawable.dominoleft);
                                jeu.add("G");
                                domino.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        gauche(finalI);
                                    }
                                });
                            }
                            domino.setLayoutParams(dominparam);

                            Layout.addView(domino);
                        }
                    }else{
                        Toast.makeText(TimberActivity.this, "Put a number PLZ", Toast.LENGTH_SHORT).show();
                    }
                    saves.add(jeu);
                }
            });

        }

        public void loadsave(List<String> liste) {
            Layout.removeAllViews();
            System.out.println("la liste passer en param : " + liste);
            List<String> newjeu = new ArrayList<>();
            if (colorplayer.equals("Bleu")) colorplayer = "Rouge";
            else colorplayer = "Bleu";
            colorplayertxt.setText(colorplayer);
            int nbdom = liste.size();
            for (int i = 0; i < nbdom; i++) {
                ImageView domino = new ImageView(Layout.getContext());
                LinearLayout.LayoutParams dominparam = new LinearLayout.LayoutParams(50, 100);
                int finalI = i;
                if (liste.get(i).equals("D")) {
                    //System.out.println("droite");
                    newjeu.add("D");
                    domino.setImageResource(R.drawable.dominoright);
                    domino.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            droite(finalI);
                        }
                    });
                } else {
                    //System.out.println("gauche");
                    newjeu.add("G");

                    domino.setImageResource(R.drawable.dominoleft);
                    domino.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            gauche(finalI);
                        }
                    });
                }
                domino.setLayoutParams(dominparam);
                Layout.addView(domino);
            }
            System.out.println("la save : " + newjeu);
            jeu = new ArrayList<>(newjeu);


        }


        public void droite(int i){
            tour++;
            System.out.println("tour numero : "+tour+" le domino qui penchait a droit ayant le numero : "+i+" de la liste : "+jeu);
            System.out.println(" fait tomber les dominos : "+jeu.subList(i,jeu.size()));
            saves.add( jeu.subList(0,i));
            System.out.println("les dominos restant sont donc : "+jeu.subList(0,i));
            if (i==0 ||jeu.subList(0,i).isEmpty()){
                Toast.makeText(TimberActivity.this, "LA CLASSE A DALLAS", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder popupvictory = new AlertDialog.Builder(TimberActivity.this);
                popupvictory.setMessage("Victoire du joueur " + colorplayer + "\n" + " En " + tour + " tours !");
                popupvictory.setTitle("Fin de partie");
                popupvictory.setPositiveButton("Play again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent new_game = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(new_game);
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
            loadsave(jeu.subList(0,i));
            System.out.println(" info "+jeu.size()+" la liste : "+jeu);
        }
        public void gauche(int i){
            tour++;
            System.out.println("tour numero : "+tour+" le domino qui penchait a gauche ayant le numero : "+i+" de la liste : "+jeu);
            System.out.println(" fait tomber les dominos : "+jeu.subList(0,i+1));
            saves.add(jeu.subList(i+1,jeu.size()));
            System.out.println("les dominos a restant sont donc : "+jeu.subList(i+1,jeu.size()));
            if (i==jeu.size() || jeu.subList(i+1,jeu.size()).isEmpty()){
                Toast.makeText(TimberActivity.this, "LA CLASSE A DALLAS", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder popupvictory = new AlertDialog.Builder(TimberActivity.this);
                popupvictory.setMessage("Victoire du joueur " + colorplayer + "\n" + " En " + tour + " tours !");
                popupvictory.setTitle("Fin de partie");
                popupvictory.setPositiveButton("Play again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent new_game = new Intent(getApplicationContext(), TimberActivity.class);
                        startActivity(new_game);
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
            loadsave(jeu.subList(i+1,jeu.size()));
            System.out.println(" info "+jeu.size());
        }
}