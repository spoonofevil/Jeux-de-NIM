package spoony.jeuxdenim

import android.widget.ImageView

data class Pebble (
        var color: String = "blank",
        var ligne: Int,
        var num: Int

){
    fun clone():Pebble
    {
        //print("${ligne} $num - $color")
        return Pebble(color, ligne, num)
    }
}