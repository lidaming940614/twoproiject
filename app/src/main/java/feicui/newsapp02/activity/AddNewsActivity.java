package feicui.newsapp02.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import feicui.newsapp02.R;
import feicui.newsapp02.adapter.InterestingAdapter;
import feicui.newsapp02.adapter.AddAdapter;
import feicui.newsapp02.base.BaseActivity;
import feicui.newsapp02.db.NewsDbManager;
import feicui.newsapp02.tools.CloseActivity;

/**
 * Created by 1099057173 on 2016/7/23.
 */
public class AddNewsActivity extends BaseActivity {
    View view,view_rl;
    List<String> tabshow,tabclick;
    AddAdapter tsa;
    InterestingAdapter tca;
    ListView listView;
    GridView gridView;
    NewsDbManager newsDbManager;
    @Override
    public void setlayout() {
        setContentView(R.layout.add_news);
    }
    @Override
    public void settoolbar() {
    }
    @Override
    public void getview() {
        newsDbManager = NewsDbManager.getDBManger(this);
        listView= (ListView) findViewById(R.id.add_listview);
        gridView= (GridView) findViewById(R.id.add_gridview);
        gridView.setNumColumns(6);
        tabclick=new ArrayList<String>();
        view=findViewById(R.id.add_nws_top_rl);
        view_rl=findViewById(R.id.top_rl);
        CloseActivity.settop(view,this);
    }
    @Override
    public void setview() {
        tabshow= newsDbManager.gettab();
        tsa=new AddAdapter(AddNewsActivity.this);
        tsa.setTabshowlist(tabshow);
        gridView.setAdapter(tsa);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tabshow.remove(position);
                newsDbManager.delecttab();
                for (int i=0;i<tabshow.size();i++){
                    newsDbManager.savetab(tabshow.get(i));
                }
                tsa.setTabshowlist(tabshow);
                tsa.notifyDataSetChanged();
                Intent i = new Intent();
                i.setAction("change");
                sendBroadcast(i);
            }
        });
        setlist();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void setlist(){
        tabclick.add("头条");
        tabclick.add("娱乐");
        tabclick.add("国内");
        tabclick.add("健康");
        tabclick.add("科技");
        tabclick.add("体育");
        tca=new InterestingAdapter(this);
        tca.setTabclicklist(tabclick);
        listView.setAdapter(tca);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (tabshow.size()==0){
                    tabshow.add(tabclick.get(position));
                }
                for (int i =0;i<tabshow.size();i++){
                    if (tabshow.get(i).equals(tabclick.get(position))){
                        break;
                    }else if (i==tabshow.size()-1){
                        tabshow.add(tabclick.get(position));
                    }
                }
                tsa.setTabshowlist(tabshow);
                tsa.notifyDataSetChanged();
                newsDbManager.delecttab();
                for (int i=0;i<tabshow.size();i++){
                    newsDbManager.savetab(tabshow.get(i));
                }
                Intent i = new Intent();
                i.setAction("change");
                sendBroadcast(i);
            }
        });
    }
}
