package feicui.newsapp02.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import feicui.newsapp02.R;
import feicui.newsapp02.db.News;
import feicui.newsapp02.tools.VolleyManager;

/**
 * Created by 1099057173 on 2016/7/23.
 */
public class HotAdapter extends RecyclerView.Adapter<HotAdapter.MyViewHolder> {
    Context mC;
    List<News> newsList;
    VolleyManager volleyManager;
    public HotAdapter(Context mC, List<News> newsList) {
        this.mC = mC;
        this.newsList = newsList;
        volleyManager=new VolleyManager(mC);
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mC).inflate(R.layout.hot_item,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        News news=newsList.get(position);
        if (news.getNewsimg()==null){
            getbitmap(holder,news);
        }else {
            holder.img.setImageBitmap(news.getNewsimg());
        }
        holder.textView.setText(news.getNewsname());
        if (recyclerOnClikListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int ps=holder.getLayoutPosition();
                    recyclerOnClikListener.onItemClik(v,ps);
                }
            });
        }
    }
    private RecyclerOnClikListener recyclerOnClikListener;

    public RecyclerOnClikListener getRecyclerOnClikListener() {
        return recyclerOnClikListener;
    }

    public void setRecyclerOnClikListener(RecyclerOnClikListener recyclerOnClikListener) {
        this.recyclerOnClikListener = recyclerOnClikListener;
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.hot_item_iv);
            textView = (TextView) itemView.findViewById(R.id.hot_item_tv);
        }
    }
    public interface RecyclerOnClikListener{
        void onItemClik(View view,int position);
        void onItemLongClik(View view,int position);
    }
    public void getbitmap(final RecyclerView.ViewHolder holder ,News n){
        News news=n;
        volleyManager.setHandImg(new VolleyManager.HandImg() {
            @Override
            public void hand(String imgurl, Bitmap bitmap, String name, String url, String date, String type) {
                ((MyViewHolder) holder).img.setImageBitmap(bitmap);
            }
        });
        volleyManager.getbitmap(news.getImgurl(),null,null,null,null);
    }
}
