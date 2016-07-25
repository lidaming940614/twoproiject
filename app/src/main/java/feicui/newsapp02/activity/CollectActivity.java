package feicui.newsapp02.activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import feicui.newsapp02.R;
import feicui.newsapp02.adapter.HotAdapter;
import feicui.newsapp02.base.BaseActivity;
import feicui.newsapp02.db.NewsDbManager;
import feicui.newsapp02.db.News;
import feicui.newsapp02.tools.VolleyManager;

/**
 * Created by 1099057173 on 2016/7/23.
 */
public class CollectActivity extends BaseActivity {
    HotAdapter hotAdapter;
    List<News> newsList;
    RecyclerView recyclerView;
    VolleyManager vm;
    SwipeRefreshLayout srl;
    NewsDbManager newsDbManager;
    @Override
    public void setlayout() {
        setContentView(R.layout.hot);
    }

    @Override
    public void settoolbar() {

    }

    @Override
    public void getview() {
        newsDbManager = NewsDbManager.getDBManger(this);
        recyclerView= (RecyclerView)findViewById(R.id.hot_recyclerview);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        newsList=new ArrayList<News>();
        vm=new VolleyManager(this);
        srl= (SwipeRefreshLayout)findViewById(R.id.hot_srl);
        srl.setColorSchemeResources(R.color.colorPrimaryDark,R.color.colorGreen,R.color.colorOragen,R.color.colorAccent);

    }

    @Override
    public void setview() {
        loaddata();
    }



    @Override
    public void onClick(View v) {

    }
    public void loaddata(){
        newsList= newsDbManager.getnews("collect");
        hotAdapter=new HotAdapter(CollectActivity.this,newsList);
        recyclerView.setAdapter(hotAdapter);
        hotAdapter.setRecyclerOnClikListener(new HotAdapter.RecyclerOnClikListener() {
            @Override
            public void onItemClik(View view, int position) {
                Intent i = new Intent(CollectActivity.this, WebViewActivity.class);
                Bundle b = new Bundle();
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
    }
}
