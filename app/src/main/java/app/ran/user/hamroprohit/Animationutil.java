package app.ran.user.hamroprohit;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;

/**
 * Created by User on 6/1/2016.
 */
public class Animationutil {
    public static void animate(RecyclerView.ViewHolder vh, boolean up_down){
        AnimatorSet animatorSet=new AnimatorSet();
        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(vh.itemView,"translationY",up_down==true?200:-200,0);
        objectAnimator.setDuration(1000);
        animatorSet.playTogether(objectAnimator);
        animatorSet.start();
    }
}
