package feicui.newsapp02.tools;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import feicui.newsapp02.R;
import feicui.newsapp02.entity.HomeButtom;
import feicui.newsapp02.fragment.Home_Search_Fragment;
import feicui.newsapp02.fragment.HotFragment;
import feicui.newsapp02.fragment.Home_info_Fragment;

/**
 * Created by 1099057173 on 2016/7/23.
 */
public class HomeManager {
    FragmentActivity a ;
    public HomeManager(FragmentActivity a) {
        this.a = a;
    }

    List<HomeButtom> homeButtomList;
    public void initHomeButtomChange(){
        homeButtomList =new ArrayList<HomeButtom>();
        homeButtomList.add(new HomeButtom((ImageView) a.findViewById(R.id.home_buttom_one_iv), R.mipmap.new_unselected,R.mipmap.new_selected,new Home_info_Fragment()));
        homeButtomList.add(new HomeButtom((ImageView) a.findViewById(R.id.home_buttom_two_iv),R.mipmap.collect_unselected,R.mipmap.collect_selected,new HotFragment()));
        homeButtomList.add(new HomeButtom((ImageView) a.findViewById(R.id.home_buttom_three_iv),R.mipmap.find_defult,R.mipmap.find_selected,new Home_Search_Fragment()));
        for (int i = 0; i< homeButtomList.size(); i++){
            homeButtomList.get(i).getImageView().setOnClickListener((View.OnClickListener) a);
        }

        homeButtomList.get(0).getImageView().setImageResource(homeButtomList.get(0).getChickafter());

    }


    public void setbuttomchick(View v){
        HomeButtom hbc = null;
        for (int i = 0; i< homeButtomList.size(); i++){
            hbc= homeButtomList.get(i);
            if(hbc.getImageView().getId()==v.getId()){
                hbc.getImageView().setImageResource(hbc.getChickafter());
            }else {
                hbc.getImageView().setImageResource(hbc.getChickbefore());
            }
        }
    }


    public void initfragment(){
        FragmentManager fm = a.getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.add(R.id.home_home, homeButtomList.get(0).getChickfragment()).show(homeButtomList.get(0).getChickfragment());
        ft.commit();

    }


    public void changefragment(View v){
        HomeButtom hbc = null;
        for (int i = 0; i< homeButtomList.size(); i++){
            FragmentManager fm = a.getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            hbc= homeButtomList.get(i);
            Fragment fragment=hbc.getChickfragment();
            if(!fragment.isAdded()){
                ft.add(R.id.home_home,fragment);
            }
            if(hbc.getImageView().getId()==v.getId()){
                for (int j = 0; j< homeButtomList.size(); j++){
                    if (i!=j){
                        ft.hide(homeButtomList.get(j).getChickfragment());
                    }
                }
                ft.show(fragment);
                ft.commit();
            }
        }
    }
}
