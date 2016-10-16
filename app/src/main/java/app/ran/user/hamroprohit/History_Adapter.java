package app.ran.user.hamroprohit;

/**
 * Created by User on 10/16/2016.
 */

    import android.content.Context;
    import android.content.SharedPreferences;
    import android.support.v4.content.ContextCompat;
    import android.support.v7.widget.CardView;
    import android.support.v7.widget.RecyclerView;
    import android.text.Html;
    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.TextView;

    import java.text.SimpleDateFormat;
    import java.util.ArrayList;
    import java.util.Locale;



    /**
     * Created by User on 5/10/2016.
     */
    public class History_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private final ArrayList<String> list1;
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

        public static class ViewHolder extends RecyclerView.ViewHolder {

            CardView c;
            TextView Name1;
            public View view;

            public ViewHolder(View v) {
                super(v);
                view = v;
              //  c = (CardView) view.findViewById(R.id.card_view2);
                Name1 = (TextView) view.findViewById(R.id.name);
               
                
            }
        }

        public static class EmptyViewHolder extends RecyclerView.ViewHolder {
            TextView tvEmpty;
            public View view;

            public EmptyViewHolder(View v) {
                super(v);
                view = v;
                tvEmpty = (TextView) view.findViewById(R.id.setmsg);
            }
        }

        public History_Adapter(Context context, ArrayList<String> objects) {
            this.context = context;
            this.list1 = objects;
        }

        @Override
        public int getItemViewType(int position) {
            Log.e("getitemcount", String.valueOf(getItemCount()));
            if (list1 == null) {
                return VIEW_TYPE_EMPTY;
            } else {
                return VIEW_TYPE_LIST;
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
            View v;
            if (viewType == VIEW_TYPE_EMPTY) {

                // Log.e("histry_adapter", message + "oncreateview");
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.empty_histry, parent, false);
                EmptyViewHolder vh = new EmptyViewHolder(v);
                return vh;
            } else {
                // Log.e("histry_adapter", status + "oncreateview");
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.tryrow, parent, false);
                ViewHolder vh = new ViewHolder(v);
                return vh;
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder vh, int position) {
           
//        Log.e("onbind", list1.get(position).getREFERED_NAME());
            if (getItemViewType(position) == VIEW_TYPE_EMPTY) {
                EmptyViewHolder holder = (EmptyViewHolder) vh;
                if (message != null) {
                    holder.tvEmpty.setText(message);
                } else {
                    holder.tvEmpty.setText("Something went wrong! Please check your internet connection and try again.");
                }

            } else {
                // Log.e("onbind_inside", list1.get(position).getREFERED_NAME());
                ViewHolder holder = (ViewHolder) vh;
                //  holder.Name = list1.get(position).getREFERED_NAME();


            }
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
return 0;
        }

    }