package feicui.newsapp02.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1099057173 on 2016/7/23.
 */
public class FragmentAdapter extends FragmentStatePagerAdapter {
    List<String> tabnamelist;//tab列表
    List<Fragment> fragmentList;//fragment列表

    public List<String> getTabnamelist() {
        return tabnamelist;
    }

    public void setTabnamelist(List<String> tabnamelist) {
        this.tabnamelist = tabnamelist;
    }

    public List<Fragment> getFragmentList() {
        return fragmentList;
    }

    public void setFragmentList(List<Fragment> fragmentList) {
        this.fragmentList = fragmentList;
    }

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
        this.fragmentList = new ArrayList<Fragment>();
        this.tabnamelist = new ArrayList<String>();
    }
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabnamelist.get(position);
    }
}