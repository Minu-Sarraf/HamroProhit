package app.ran.user.hamroprohit;

/**
 * Created by User on 10/16/2016.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import app.ran.user.hamroprohit.Model.Poojalist;


/**
 * Created by User on 5/10/2016.
 */
public class History_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    // private final ArrayList<String> list1;
    String[] ticketdate;
    Context context;
    //  ArrayList<Datalist.DataBean.MsgBean> list1;
    public View view;
    String message;
    int previousposition = 0;
    int status;
    public static final int VIEW_TYPE_EMPTY = 0;
    SharedPreferences prefs;
    public static final int VIEW_TYPE_LIST = 1;

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.next){
            Intent i=new Intent(context,Profile.class);
            context.startActivity(i);
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView c;
        TextView Name1;
        ImageView ivnext;
        public View view;

        public ViewHolder(View v) {
            super(v);
            view = v;
            Log.e("view", "holder");
            //  c = (CardView) view.findViewById(R.id.card_view2);
            Name1 = (TextView) view.findViewById(R.id.name);
            ivnext = (ImageView) view.findViewById(R.id.next);

        }
    }


    public History_Adapter(Context context, ArrayList<String> objects) {
        this.context = context;
        Log.e("view", "context");

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View v;

        Log.e("histry_adapter", "oncreateview");
        v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tryrow, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vh, int position) {

        //  fillableLoader = (FillableLoader) findViewById(R.id.fillableLoader);

//        Log.e("onbind", list1.get(position).getREFERED_NAME());

        Log.e("onbind_inside", "row");
        ViewHolder holder = (ViewHolder) vh;
        Typeface fontHindi = Typeface.createFromAsset(context.getAssets(),
                "fonts/Ananda Lipi Bold Cn Bt.ttf");
        holder.Name1.setTypeface(fontHindi);
        holder.Name1.setText(Poojalist.pooja[position]);
        holder.ivnext.setOnClickListener(this);
        if (position > previousposition) {
            Animationutil.animate(holder, true);
        } else {
            Animationutil.animate(holder, false);
        }
        previousposition = position;
    }
       /* public void refresh(ArrayList<Datalist.DataBean.MsgBean> list1) {
            //  list1.clear();
            this.list1 = list1;
            notifyDataSetChanged();
        }*/

    @Override
    public int getItemCount() {
          /* if (list1 == null) {
                //  progressBar.setVisibility(View.VISIBLE);
                return 1;
            } else {
                //  progressBar.setVisibility(View.INVISIBLE);
                return list1.size();
            }*/
        return Poojalist.pooja.length;
    }

}