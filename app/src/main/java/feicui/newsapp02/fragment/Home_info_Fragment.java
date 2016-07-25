package feicui.newsapp02.fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import feicui.newsapp02.R;
import feicui.newsapp02.activity.AddNewsActivity;
import feicui.newsapp02.adapter.FragmentAdapter;
import feicui.newsapp02.base.BaseFragment;
import feicui.newsapp02.db.NewsDbManager;
import feicui.newsapp02.tools.ProjectManager;

/**
 * Created by 1099057173 on 2016/7/23.
 */
public class Home_info_Fragment extends BaseFragment implements View.OnClickListener {
    TabLayout tabLayout;
    View view;
    FragmentAdapter fragmentAdapter;
    List<String> tabname;
    List<Fragment> fragmentList;
    ViewPager viewPager;
    ImageView addnews;
    ProjectManager projectManager;
    MyReciver mr;
    NewsDbManager newsDbManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.newsfragment,null);
        getview();
        setview();
        return view;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mr==null){
            mr=new MyReciver();
            IntentFilter intentFilter=new IntentFilter();
            intentFilter.addAction("change");
            getActivity().registerReceiver(mr,intentFilter);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(mr);
    }

    @Override
    public void getview() {
        newsDbManager = NewsDbManager.getDBManger(getActivity());
        tabLayout= (TabLayout) view.findViewById(R.id.news_tablayout_tl);
        viewPager= (ViewPager) view.findViewById(R.id.news_viewpager_vp);
        addnews= (ImageView) view.findViewById(R.id.news_img_add);
        projectManager = ProjectManager.getspfManager(getActivity());

    }

    @Override
    public void setview() {
        tabLayout.setTabTextColors(Color.BLACK,Color.BLUE);
        setlist();
        addnews.setOnClickListener(this);
        fragmentAdapter =new FragmentAdapter(getFragmentManager());
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
        fragmentAdapter.setFragmentList(fragmentList);
        fragmentAdapter.setTabnamelist(tabname);
        viewPager.setOffscreenPageLimit(tabname.size());
        fragmentAdapter.notifyDataSetChanged();
    }
    public void setlist(){
        tabname=new ArrayList<String>();
        String one= projectManager.isfirst();
        if (one.equals("isfirst")){
            tabname=new ArrayList<String>();
            tabname.add("头条");
            tabname.add("娱乐");
            tabname.add("国内");
            tabname.add("健康");
            tabname.add("科技");
            tabname.add("体育");
            newsDbManager.delecttab();
            for (int i =0;i<tabname.size();i++){
                newsDbManager.savetab(tabname.get(i));
            }
            projectManager.saveone("no");
        }
        tabname= newsDbManager.gettab();
        Log.i("news",tabname.toString());
        if (tabname==null||tabname.size()==0){
            Log.i("addlist","进来了");
            tabname=new ArrayList<String>();
            tabname.add("头条");
            tabname.add("娱乐");
            tabname.add("国内");
            tabname.add("健康");
            tabname.add("科技");
            tabname.add("体育");
            newsDbManager.delecttab();
            for (int i =0;i<tabname.size();i++){
                newsDbManager.savetab(tabname.get(i));
            }
        }

        fragmentList=new ArrayList<>();
        for (int i=0;i<tabname.size();i++){
            Info_item_Fragment newsfragment=new Info_item_Fragment();
            fragmentList.add(newsfragment);
            Log.i("news",tabname.get(i));
            switch (tabname.get(i)){
                case "头条":
                    newsfragment.setContent("wxnew");
                    break;
                case "娱乐":
                    newsfragment.setContent("huabian");
                    break;
                case "国内":
                    newsfragment.setContent("guonei");
                    break;
                case "健康":
                    newsfragment.setContent("health");
                    break;
                case "科技":
                    newsfragment.setContent("keji");
                    break;
                case "体育":
                    newsfragment.setContent("tiyu");
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.news_img_add:
                Intent i = new Intent(getActivity(), AddNewsActivity.class);
                startActivity(i);
                break;
        }
    }

    public void getnews(){

    }
    /**
     * 设置广播接收
     */
    class MyReciver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String ii=intent.getAction();
            if(ii.equals("change")){
                setlist();
                viewPager.setAdapter(fragmentAdapter);
                tabLayout.setupWithViewPager(viewPager);
                Log.i("news",fragmentList.toString());
                fragmentAdapter.setFragmentList(fragmentList);
                fragmentAdapter.setTabnamelist(tabname);
                fragmentAdapter.notifyDataSetChanged();
                viewPager.setOffscreenPageLimit(tabname.size());

            }
        }
    }

}
