package spoony.jeuxdenim

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import spoony.jeuxdenim.activity.Pebble1Activity
import java.util.*

class PebbleOnClickListener(
        val peble: Pebble,
        val context: Pebble1Activity,
        val pebblesimg: Array<Array<ImageView>>
) : View.OnClickListener {


    fun ligneclickable(Ligne: Int, pebblesimg: Array<Array<ImageView>>) {
        var L1 = 1
        var L2 = 1
        when (Ligne) {
            1 -> {
                L1 = 2
                L2 = 3
            }
            2 -> {
                L1 = 1
                L2 = 3
            }
            3 -> {
                L1 = 2
                L2 = 1
            }
            else -> Toast.makeText(context, "C PASENS2 ARRIVER TT CA", Toast.LENGTH_SHORT).show()
        }
        for (pebleIMG in pebblesimg[L1 - 1]) {
            pebleIMG.isClickable = false
        }
        for (pebleIMG in pebblesimg[L2 - 1]) {
            pebleIMG.isClickable = false
        }
    }

    fun lignenonclickable(Ligne: Int, pebblesimg: Array<Array<ImageView>>, pebblesnow: Array<Array<Pebble>>) {
        var L1 = 1
        var L2 = 1
        when (Ligne) {
            1 -> {
                L1 = 1
                L2 = 2
            }
            2 -> {
                L1 = 0
                L2 = 2
            }
            3 -> {
                L1 = 1
                L2 = 0
            }
            else -> Toast.makeText(context, "C PASENS2 ARRIVER TT CA", Toast.LENGTH_SHORT).show()
        }


        for (Pebblenow in pebblesnow[L1]) {
            if (Pebblenow.color == "blank")
                pebblesimg[Pebblenow.ligne - 1][Pebblenow.num - 1].isClickable = true
        }
        for (Pebblenow in pebblesnow[L2]) {
            if (Pebblenow.color == "blank") {
                pebblesimg[Pebblenow.ligne - 1][Pebblenow.num - 1].isClickable = true
            }

        }
    }

    override fun onClick(v: View) {
        val tour = Pebble1Activity.tour
        val savem = context.saveboard.get(tour)
        var avant = true;
        when (peble.color) {
            "blank" -> if (context.player) {
                (v as ImageView).setImageResource(R.drawable.blue_pebble)
                peble.color = "b"
                ligneclickable(peble.ligne, pebblesimg)
                context.confirm.isClickable = true
            } else {
                (v as ImageView).setImageResource(R.drawable.red_pebble)
                peble.color = "r"
                ligneclickable(peble.ligne, pebblesimg)
                context.confirm.isClickable = true
            }

            "b" -> if (context.player) {
                (v as ImageView).setImageResource(R.drawable.empty_pebble)
                peble.color = "blank"
                for (pebbltemp in context.pebbles) {
                   for (pebblenow in pebbltemp) {
                        if (pebblenow.color != savem[pebblenow.ligne - 1][pebblenow.num - 1].color) {
                            avant = false;
                        }
                    }
                }
                if (avant) {
                    context.confirm.isClickable = false;
                    lignenonclickable(peble.ligne, pebblesimg, context.pebbles)
                } else {
                    context.confirm.isClickable = true
                }

            } else {
                Toast.makeText(context, "Stop trying to cheat", Toast.LENGTH_SHORT).show()
            }
            "r" -> if (!context.player) {
                (v as ImageView).setImageResource(R.drawable.empty_pebble)
                peble.color = "blank"
                for (pebbltemp in context.pebbles) {
                    for (pebblenow in pebbltemp) {
                        if (pebblenow.color != savem[pebblenow.ligne - 1][pebblenow.num - 1].color) {
                            avant = false;
                        }
                    }
                }
                if (avant) {
                    context.confirm.isClickable = false;
                    lignenonclickable(peble.ligne, pebblesimg, context.pebbles)
                } else {
                    context.confirm.isClickable = true
                }
            } else {
                Toast.makeText(context, "Stop trying to cheat", Toast.LENGTH_SHORT).show()
            }
            else -> Toast.makeText(context, "OULALAAAAA C PAS SENSE SE PASSER COMME CA : " + peble.color, Toast.LENGTH_LONG).show()
        }

    }
}