package app.ran.user.hamroprohit;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Created by User on 6/1/2016.
 */
public class Animationutil {
    private int lastPosition=-1;

    public static void animate(RecyclerView.ViewHolder vh, boolean up_down){
        AnimatorSet animatorSet=new AnimatorSet();
        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(vh.itemView,"translationY",up_down==true?200:-200,0);
        objectAnimator.setDuration(1000);
        animatorSet.playTogether(objectAnimator);
        animatorSet.start();
    }
    private void setAnimation(final View viewToAnimate, int position, Context context)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            final Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
             animation.setStartOffset(position *80);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}
