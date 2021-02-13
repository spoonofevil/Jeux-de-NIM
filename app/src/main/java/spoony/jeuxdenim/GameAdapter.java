package spoony.jeuxdenim;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.List;

import spoony.jeuxdenim.activity.MainActivity;
import spoony.jeuxdenim.activity.Pebble1Activity;
import spoony.jeuxdenim.activity.Pebble1bot;

public class GameAdapter extends BaseAdapter {

    private Context context;
    private List<Game_item> listofGame;
    private LayoutInflater inflater;

    public GameAdapter(Context context, List<Game_item> listofGame ){
        this.context = context;
        this.listofGame = listofGame;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listofGame.size();
    }

    @Override
    public Game_item getItem(int position) {
        return listofGame.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       convertView = inflater.inflate(R.layout.adapter_game, null);

       Game_item currentGame = getItem(position);
       String GameName = currentGame.getGame_name();
       String Game_icon_name = currentGame.getGame_Icon_name();
       String GameDescr = currentGame.getGame_Descr();

        ImageView GameIcon = convertView.findViewById(R.id.Game_icon);
        String iconName = "icon_" + Game_icon_name;
        int iconId = context.getResources().getIdentifier(iconName, "drawable", context.getPackageName());
        GameIcon.setImageResource(iconId);

       TextView GameNameView = convertView.findViewById(R.id.Game_Name);
       GameNameView.setText(GameName);

       TextView GameDescrView = convertView.findViewById(R.id.Description);
       GameDescrView.setText("Descritpion : " + GameDescr);

       convertView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent futureIntent;
               AlertDialog.Builder popupgame = new AlertDialog.Builder(context);
               popupgame.setMessage("Who are you playing with ?");
               popupgame.setTitle("New game !");
               switch(Game_icon_name){
                   case "pebblegame":
                       popupgame.setPositiveButton("Another human", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               Intent new_pebble_game = new Intent(context, Pebble1Activity.class);
                               context.startActivity(new_pebble_game);
                               ((Activity) context).finish();
                           }
                       });
                       popupgame.setNegativeButton("The BOT", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               Intent Menu = new Intent(context, Pebble1bot.class);
                               context.startActivity(Menu);
                               ((Activity) context).finish();
                           }
                       });
                       popupgame.show();
                       break;
                   case "nogame":
                       Toast.makeText(context, "Sorry this game is not Available yet", Toast.LENGTH_SHORT).show();
                       break;
                   default:
                       futureIntent = new Intent(context, MainActivity.class);
                       Toast.makeText(context, "ERROR : this is not supposed to happen... (sorry about that) " + Game_icon_name, Toast.LENGTH_LONG).show();
                       context.startActivity(futureIntent);
               }



           }
       });

       return convertView;
    }
}
