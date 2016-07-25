package feicui.newsapp02.adapter;

import android.content.Context;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import feicui.newsapp02.R;
import feicui.newsapp02.db.Share;

/**
 * Created by 1099057173 on 2016/7/23.
 */
public class HomerightAdapter extends BaseAdapter {
    List<Share> shareList;
    Context mC;

    public HomerightAdapter(Context mC) {
        this.mC = mC;
    }

    public List<Share> getShareList() {
        return shareList;
    }

    public void setShareList(List<Share> shareList) {
        this.shareList = shareList;
    }

    @Override
    public int getCount() {
        return shareList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view==null){
            view= LayoutInflater.from(mC).inflate(R.layout.share_item,null);
            viewHolder=new ViewHolder();
            viewHolder.img= (ImageView) view.findViewById(R.id.share_img_iv);
            viewHolder.name= (TextView) view.findViewById(R.id.share_name_tv);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.img.setImageResource(shareList.get(i).getImgid());
        viewHolder.name.setText(shareList.get(i).getSharename());
        return view;
    }
    class ViewHolder{
        ImageView img;
        TextView name;
    }
}
