package spoony.jeuxdenim.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import spoony.jeuxdenim.GameAdapter;
import spoony.jeuxdenim.Game_item;
import spoony.jeuxdenim.R;

public class GameListActivity extends AppCompatActivity {


    //public Button Home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game__list);
        /*
        this.Home = findViewById(R.id.Home);
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(home);
                finish();
            }
        });
        */

        List<Game_item> listofgame = new ArrayList<>();
        listofgame.add(new Game_item("First pebble", "pebblegame","Take pebbles and try to be the one to take the last pebble"));
        listofgame.add(new Game_item("Unavailable", "nogame" ,"Work_in_progress"));
        listofgame.add(new Game_item("Unavailable","nogame" ,"Work_in_progress"));

        ListView GameListview = findViewById(R.id.Game_List);
        GameListview.setAdapter(new GameAdapter(this, listofgame));
    }
}