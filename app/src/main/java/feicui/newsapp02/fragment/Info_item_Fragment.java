package feicui.newsapp02.fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import feicui.newsapp02.R;
import feicui.newsapp02.activity.WebViewActivity;
import feicui.newsapp02.adapter.NewsAdapter;
import feicui.newsapp02.base.BaseFragment;
import feicui.newsapp02.db.NewsDbManager;
import feicui.newsapp02.db.News;
import feicui.newsapp02.tools.VolleyManager;

/**
 * Created by 1099057173 on 2016/7/23.
 */
public class Info_item_Fragment extends BaseFragment {
    View view;
    List<News> newses;
    List<News> innewslist;
    String content;
    VolleyManager vm;
    RecyclerView mrecyclerView;
    NewsAdapter newsAapter;
    LinearLayoutManager mLayoutManager;
    SwipeRefreshLayout sr;
    int page=1;
    boolean isloading,loadingfinish;
    NewsDbManager newsDbManager;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    newsAapter=new NewsAdapter(newses,getActivity());
                    mrecyclerView.setAdapter(newsAapter);
                    newsAapter.setNewsList(newses);
                    newsAapter.notifyDataSetChanged();
                    newsAapter.recyclerViewOnclickListener =new NewsAdapter.RecyclerViewOnclickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent i = new Intent(getActivity(),WebViewActivity.class);
                            Bundle b =new Bundle();
                            newses.get(position).setNewsimg(null);
                            News news=newses.get(position);
                            b.putSerializable("news",news);
                            b.putString("url",newses.get(position).getNewsurl());
                            i.putExtras(b);
                            startActivity(i);
                        }

                        @Override
                        public void onItemLongClick(View view, int position) {

                        }
                    };
                    break;
                case 1:
                    newsAapter.setNewsList(newses);
                    newsAapter.recyclerViewOnclickListener =new NewsAdapter.RecyclerViewOnclickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent i = new Intent(getActivity(),WebViewActivity.class);
                            Bundle b =new Bundle();
                            newses.get(position).setNewsimg(null);
                            News news=newses.get(position);
                            b.putSerializable("news",news);
                            b.putString("url",newses.get(position).getNewsurl());
                            i.putExtras(b);
                            startActivity(i);
                        }
                        @Override
                        public void onItemLongClick(View view, int position) {
                        }
                    };
                    newsAapter.changeState(0);
                    break;
            }
        }
    };
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.news,null);
        getview();
        setview();
        return view;
    }
    @Override
    public void getview() {
        isloading=false;
        loadingfinish=true;
        newsDbManager = NewsDbManager.getDBManger(getActivity());
        innewslist=new ArrayList<News>();
        vm=new VolleyManager(getActivity());
        newses=new ArrayList<News>();
        mrecyclerView= (RecyclerView) view.findViewById(R.id.news_recyclerview);
        mLayoutManager=new LinearLayoutManager(getActivity());
        mrecyclerView.setLayoutManager(mLayoutManager);
        sr= (SwipeRefreshLayout) view.findViewById(R.id.news_srf);

        sr.setColorSchemeResources(R.color.colorPrimaryDark,R.color.colorGreen,R.color.colorOragen,R.color.colorAccent);

        sr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                newses=new ArrayList<News>();
                getdomnnews();
                sr.setRefreshing(false);

            }
        });
    }

    @Override
    public void setview() {
        getphonenews();

        getupfruse();
    }
    public void getupfruse(){
        mrecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int layoutitem=mLayoutManager.findLastCompletelyVisibleItemPosition();
                if (layoutitem==newsAapter.getItemCount()-1){
                    switch (newState){
                        case 1://滑动开始
                            break;
                        case 2://滑到底部或顶部
                            if(!isloading){
                                newsAapter.changeState(2);
                                isloading=true;
                            }
                            break;
                        case 0://滑动结束
                            if(isloading&&loadingfinish){
                                new Thread(new GetNextList()).start();
                            }
                            break;
                    }
                }
            }
        });
    }

    public void getdomnnews(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                vm.setHandImg(new VolleyManager.HandImg() {
                    @Override
                    public void hand(String imgurl, Bitmap bitmap, String name, String url, String date, String type) {
                        News news = new News(imgurl,bitmap,name,url,date,type);
                        newses.add(news);
                        if (newses.size()==10){//当集合的长度等于10时，清楚数据库原有数据并保存新数据
                            savetennews();
                        }
                        handler.sendEmptyMessage(0);
                    }

                });
                vm.getBaiResult("http://api.tianapi.com/"+content+"/?key=5baa842fd7b3a2f188a60ea9dea79161&num=10&page="+page);
            }
        }).start();
    }

    public void getphonenews(){
        List<News> nhiulist= newsDbManager.getnews(content);
        if (nhiulist!=null&&nhiulist.size()>0){
            for (int i=0;i<10;i++){
                if (nhiulist.get(i)!=null){
                    News news=nhiulist.get(i);
                    newses.add(news);
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
                            newses.add(news);
                            if (newses.size()==10){//当集合的长度等于10时，清楚数据库原有数据并保存新数据
                                savetennews();
                            }
                            handler.sendEmptyMessage(0);
                        }
                    });
                    vm.getBaiResult("http://api.tianapi.com/"+content+"/?key=5baa842fd7b3a2f188a60ea9dea79161&num=10&page="+page);
                }
            }).start();

        }


    }
    public void savetennews(){
        newsDbManager.delectnews(content);
        for (int i =0;i<10;i++){
            if (newses.get(i)!=null){
                News news=newses.get(i);
                newsDbManager.savenews(content,news.getImgurl(),news.getNewsname()
                        ,news.getNewsurl(),news.getNewsdate(),news.getNewstype());
                Log.i("newsdate",news.getImgurl()+""+news.getNewsname()
                        +""+news.getNewsurl()+""+news.getNewsdate()+""+news.getNewstype());

            }
        }
    }

    class GetNextList implements Runnable{

        @Override
        public void run() {
            page++;
            vm.setHandImg(new VolleyManager.HandImg() {
                @Override
                public void hand(String imgurl, Bitmap bitmap, String name, String url, String date, String type) {
                    News news = new News(imgurl,bitmap,name,url,date,type);
                    newses.add(news);
                    Log.i("newses",newses.toString());
                    handler.sendEmptyMessage(1);
                }
            });
            vm.getBaiResult("http://api.tianapi.com/"+content+"/?key=5baa842fd7b3a2f188a60ea9dea79161&num=10&page="+page);
        }
    }

}

