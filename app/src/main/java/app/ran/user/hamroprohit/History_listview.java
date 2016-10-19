package app.ran.user.hamroprohit;
import android.content.CursorLoader;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.support.v4.app.Fragment;
import com.rey.material.widget.ProgressView;

/**
 * Created by User on 10/16/2016.
 */
public class History_listview extends Fragment implements UICallback {
    SwipeRefreshLayout mSwipeRefreshLayout;
    ImageView view;
    private AppBarLayout appBarLayout;
    static int flag = 0;
    static History_Adapter adapter;
    CursorLoader cursorLoader;
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    //ArrayList<Datalist.DataBean.MsgBean> list = new ArrayList<>();
    ProgressView progresView;
    SharedPreferences prefs;
    String name, phone;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        Log.e("fragment", "fragment");
        super.onCreate(savedInstanceState);
    }

   /* private RequestBody addpostdata() {
        body = new FormEncodingBuilder()
                .add("machine_name", name)
                .add("cell_number", phone)
                .add("token", HelperMethods.getKeyFromPref(getActivity()))
                .build();
        return body;
    }
*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.linearlayout, container, false);
        mRecyclerView = (RecyclerView) rootview.findViewById(R.id.my_recycler_view);
        progresView = (ProgressView) rootview.findViewById(R.id.progress_wheel);
      //  mSwipeRefreshLayout = (SwipeRefreshLayout) rootview.findViewById(R.id.swipe_refresh_layout);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        adapter = new History_Adapter(getActivity(), null);
        mRecyclerView.setAdapter(adapter);
        Log.e("histry","histry");
        return rootview;
    }

    private void trySwipeRefreshLayout() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setColorSchemeResources(
                    R.color.materialred,
                    R.color.blue,
                    R.color.materialyellow);
        }

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                run123();
                // adapter.refresh(list);
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
     //   Gson_parser.listener("history", ApiUrl.referl_history, addpostdata(), this);

        //trySwipeRefreshLayout();
    }

    public void getresult(int result) {
        if (result == 1) {
            Log.e("getresult", String.valueOf(result));
            run123();
        }else if(result==2){
            progresView.setVisibility(View.GONE);
        }

    }

   /* @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
    }*/


    public void run123() {
      //  Gson_parser.listener("history", ApiUrl.referl_history, addpostdata(), this);
        flag = 1;
    }




  /*  @Override
    public void update2(String s) {
        //  Log.e("update", "histryupdate");
        mRecyclerView.setVisibility(View.VISIBLE);
        progresView.setVisibility(View.GONE);
        //  Log.e("update", "histryupdate");
        list = Gson_parser.searcharray();
        if (list == null) {
            mRecyclerView.setBackgroundColor(getResources().getColor(R.color.white));
            if (flag == 1) {

                adapter.refresh(list);
                mSwipeRefreshLayout.setRefreshing(false);
                flag = 0;
                AlertUtils.displaySnackBar(getActivity(), "Refresh is Succesful.Your Refer History is Uptodate", R.color.green_complete);
            } else if (flag == 0) {
                Log.e("swipeinside", "swipe");
                adapter.refresh(list);
            }

        } else {

            if (flag == 1) {
                //  Log.e("swipe", "swipe");
                mSwipeRefreshLayout.setRefreshing(false);
                adapter.refresh(list);
                flag = 0;
                AlertUtils.displaySnackBar(getActivity(), "Refresh is Succesful.Your Refer History is Uptodate", R.color.green_complete);
            } else if (flag == 0) {
                // Log.e("swipe", "flag0");
                // adapter = new History_Adapter(AplicationActivity.getInstance(), list);
                // mRecyclerView.setAdapter(adapter);
                adapter.refresh(list);
            }
        }
    }*/

    @Override
    public void onResume() {
        super.onResume();
    }

    public void swipe_offset(int verticalOffset) {
        if (verticalOffset == 0) {
            mSwipeRefreshLayout.setEnabled(true);
        } else {
            mSwipeRefreshLayout.setEnabled(false);
        }
    }
}
