package feicui.newsapp02.fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import feicui.newsapp02.R;
import feicui.newsapp02.activity.WebViewActivity;
import feicui.newsapp02.adapter.HotAdapter;
import feicui.newsapp02.base.BaseFragment;
import feicui.newsapp02.db.NewsDbManager;
import feicui.newsapp02.db.News;
import feicui.newsapp02.tools.VolleyManager;

/**
 * Created by 1099057173 on 2016/7/23.
 */
public class HotFragment extends BaseFragment {
    HotAdapter hotAdapter;
    List<News> newsList;
    RecyclerView recyclerView;
    View view;
    VolleyManager vm;
    SwipeRefreshLayout srl;
    NewsDbManager newsDbManager;
    int page=1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.hot,null);
        getview();
        setview();
        return view;
    }
    @Override
    public void getview() {
        newsDbManager = NewsDbManager.getDBManger(getActivity());
        recyclerView= (RecyclerView) view.findViewById(R.id.hot_recyclerview);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        newsList=new ArrayList<News>();
        vm=new VolleyManager(getActivity());
        srl= (SwipeRefreshLayout) view.findViewById(R.id.hot_srl);

        srl.setColorSchemeResources(R.color.colorPrimaryDark,R.color.colorGreen,R.color.colorOragen,R.color.colorAccent);
    }
    @Override
    public void setview() {
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                newsList=new ArrayList<News>();
                loaddata();
                srl.setRefreshing(false);
            }
        });
        getphonenews();

    }
    public void loaddata(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                vm.setHandImg(new VolleyManager.HandImg() {
                    @Override
                    public void hand(String imgurl, Bitmap bitmap, String name, String url, String date, String type) {
                        News news = new News(imgurl,bitmap,name,url,date,type);
                        newsList.add(news);
                        Log.i("msg",newsList.toString());
                        handler.sendEmptyMessage(0);
                    }
                });
                vm.getBaiResult("http://api.tianapi.com/wxnew/?key=5baa842fd7b3a2f188a60ea9dea79161&num=10&page="+page);
            }
        }).start();

    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    hotAdapter=new HotAdapter(getActivity(),newsList);
                    recyclerView.setAdapter(hotAdapter);
                    hotAdapter.notifyDataSetChanged();
                    hotAdapter.setRecyclerOnClikListener(new HotAdapter.RecyclerOnClikListener() {
                        @Override
                        public void onItemClik(View view, int position) {
                            Intent i = new Intent(getActivity(), WebViewActivity.class);
                            Bundle b = new Bundle();
                            newsList.get(position).setNewsimg(null);
                            News news=newsList.get(position);
                            b.putSerializable("news",news);
                            b.putString("url",newsList.get(position).getNewsurl());
                            i.putExtras(b);
                            startActivity(i);
                        }

                        @Override
                        public void onItemLongClik(View view, int position) {

                        }
                    });
                    break;
            }
        }
    };
    /**
     * 加载网络数据功能
     */
    public void getphonenews(){
        List<News> nhiulist= newsDbManager.getnews("wxnew");
        if (nhiulist!=null&&nhiulist.size()>0){
            for (int i=0;i<10;i++){
                if (nhiulist.get(i)!=null){
                    News news=nhiulist.get(i);
                    newsList.add(news);
                }
            }
            handler.sendEmptyMessage(0);
        }else if (nhiulist==null||nhiulist.size()==0){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    vm.setHandImg(new VolleyManager.HandImg() {
                        @Override
                        public void hand(String imgurl, Bitmap bitmap, String name, String url, String date, String type) {
                            News news = new News(imgurl,bitmap,name,url,date,type);
                            newsList.add(news);
                            if (newsList.size()==10){//当集合的长度等于10时，清楚数据库原有数据并保存新数据
                                savetennews();
                            }
                            handler.sendEmptyMessage(0);
                        }
                    });
                    vm.getBaiResult("http://api.tianapi.com/wxnew/?key=5baa842fd7b3a2f188a60ea9dea79161&num=10&page="+page);
                }
            }).start();

        }
    }

    public void savetennews(){
        newsDbManager.delectnews("wxnew");
        for (int i =0;i<10;i++){
            if (newsList.get(i)!=null){
                News news=newsList.get(i);
                newsDbManager.savenews("wxnew",news.getImgurl(),news.getNewsname(),news.getNewsurl(),news.getNewsdate(),news.getNewstype());

            }
        }
    }
}
