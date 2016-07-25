package feicui.newsapp02.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by 1099057173 on 2016/7/23.
 */
public class LeadAdapter extends PagerAdapter {
    List<ImageView> list;
    Context gC;

    public LeadAdapter(Context gC) {
        this.gC = gC;
    }

    public List<ImageView> getList() {
        return list;
    }

    public void setList(List<ImageView> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view=list.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view=list.get(position);
        container.removeView(view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
