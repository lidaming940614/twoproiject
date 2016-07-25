package feicui.newsapp02.adapter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import feicui.newsapp02.R;

/**
 * Created by 1099057173 on 2016/7/23.
 */
public class AddAdapter extends BaseAdapter {
    List<String> tabshowlist;
    Context mC;

    public AddAdapter(Context mC) {
        this.mC = mC;
    }

    public List<String> getTabshowlist() {
        return tabshowlist;
    }

    public void setTabshowlist(List<String> tabshowlist) {
        this.tabshowlist = tabshowlist;
    }

    @Override
    public int getCount() {
        return tabshowlist.size();
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
            convertView= LayoutInflater.from(mC).inflate(R.layout.add_tab_item,null);
            vh.tabname= (TextView) convertView.findViewById(R.id.add_show_tv);
            convertView.setTag(vh);
        }else {
            vh= (ViewHolder) convertView.getTag();
        }
        List<String> tab=new ArrayList<String>();
        Iterator<String> it = tabshowlist.iterator();
        while (it.hasNext())
        {
            tab.add(it.next());
        }
        vh.tabname.setText(tab.get(position));
        return convertView;
    }
    class ViewHolder{
        TextView tabname;
    }
}
