package feicui.newsapp02.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import feicui.newsapp02.R;

/**
 * Created by 1099057173 on 2016/7/23.
 */
public class InterestingAdapter extends BaseAdapter{
    List<String> tabclicklist;
    Context mC;

    public InterestingAdapter(Context mC) {
        this.mC = mC;
    }

    public List<String> getTabclicklist() {
        return tabclicklist;
    }

    public void setTabclicklist(List<String> tabclicklist) {
        this.tabclicklist = tabclicklist;
    }

    @Override
    public int getCount() {
        return tabclicklist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView==null){
            vh=new ViewHolder();
            convertView= LayoutInflater.from(mC).inflate(R.layout.interest_item,null);
            vh.tabname= (TextView) convertView.findViewById(R.id.add_click_tv);
            convertView.setTag(vh);
        }else {
            vh= (ViewHolder) convertView.getTag();
        }
        vh.tabname.setText(tabclicklist.get(position));
        return convertView;
    }
    class ViewHolder{
        TextView tabname;
    }
}
