package spoony.jeuxdenim;

import android.widget.ImageView;

public class Game_item {

    private String Game_name;
    private String Game_Icon_name;
    private String Game_Descr;
    //private ImageView Game_icon;

    public Game_item(String name, String Game_icon_name, String Desc){
        this.Game_name = name;
        this.Game_Icon_name = Game_icon_name;
        this.Game_Descr = Desc;

    }

    public String getGame_name() {return Game_name; }
    public String getGame_Icon_name() {return Game_Icon_name; }
    public String getGame_Descr() {return Game_Descr; }


}
