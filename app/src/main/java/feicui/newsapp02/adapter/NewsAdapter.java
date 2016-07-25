package feicui.newsapp02.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
public class NewsAdapter extends RecyclerView.Adapter{
    VolleyManager volleyManager;
    List<News> newsList;
    Context mC;
    final int HEAD_TYPE=1;
    final int BODY_TYPE=2;
    final int FOTT_TYPE=3;
    boolean isloding=false;
    int type;
    public int  load_more_state=0;//加载状态的标签
    public NewsAdapter(List<News> newsList, Context mC) {
        this.newsList = newsList;
        this.mC = mC;
        volleyManager=new VolleyManager(mC);
        Log.i("list",newsList.toString());
    }

    public boolean isloding() {
        return isloding;
    }

    public void setIsloding(boolean isloding) {
        this.isloding = isloding;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==HEAD_TYPE){
            View view= LayoutInflater.from(mC).inflate(R.layout.head,parent,false);
            MyViewHolder viewHolder =new MyViewHolder(view);
            return viewHolder;
        } else if (viewType==BODY_TYPE){
            View view= LayoutInflater.from(mC).inflate(R.layout.news_item_main,parent,false);
            MyViewHolder viewHolder =new MyViewHolder(view);
            return viewHolder;
        } else{
            View view=LayoutInflater.from(mC).inflate(R.layout.foot,parent,false);
            MyViewHolderFott myviewholderfott=new MyViewHolderFott(view);
            return myviewholderfott;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        Log.i("adapter",newsList.size()+"");
        News news = null;
        if (position<newsList.size()){
            news =newsList.get(position);
        }
        if (holder instanceof MyViewHolder){
            if (type==HEAD_TYPE){
                if (news.getNewsimg()!=null){
                    ((MyViewHolder) holder).img.setImageBitmap(news.getNewsimg());
                }else {
                    getbitmap(holder,news);
                }
                ((MyViewHolder) holder).name.setText(news.getNewsname());
            }
            if (type==BODY_TYPE){
                if (news.getNewsimg()!=null){
                    ((MyViewHolder) holder).img.setImageBitmap(news.getNewsimg());
                }else {
                    getbitmap(holder,news);
                }
                ((MyViewHolder) holder).name.setText(news.getNewsname());
                ((MyViewHolder) holder).newstype.setText(news.getNewstype());
                ((MyViewHolder) holder).date.setText(news.getNewsdate());
                Log.i("newsdate",news.getNewsdate());
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewOnclickListener.onItemClick(v,position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    recyclerViewOnclickListener.onItemLongClick(v,position);
                    return true;
                }
            });
        }
        if (holder instanceof MyViewHolderFott){
            switch (load_more_state){
                case 0:
                    ((MyViewHolderFott) holder).tv.setText("加载更多");
                    break;
                case 1:
                    break;
                case 2:
                    ((MyViewHolderFott) holder).tv.setText("正在加载");
                    break;
            }
        }


    }


    @Override
    public int getItemCount() {
        return newsList.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            type=HEAD_TYPE;//将第0项的type类型设为1
            return HEAD_TYPE;
        }if (position==newsList.size()){
            type=FOTT_TYPE;
            return FOTT_TYPE;
        }else {
            type=BODY_TYPE;//其他项的type类型设为2，若不设置，type值全部为0；
            return BODY_TYPE;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView name,date,newstype;
        public MyViewHolder(View itemView) {
            super(itemView);
            Log.i("type",type+"");
            if (type==HEAD_TYPE){
                img= (ImageView) itemView.findViewById(R.id.newsitem_img_iv);
                name= (TextView) itemView.findViewById(R.id.newsitem_news_tv);
            }
            if (type==BODY_TYPE){
                img= (ImageView) itemView.findViewById(R.id.newsnext_img_iv);
                name= (TextView) itemView.findViewById(R.id.newsnext_news_tv);
                date= (TextView) itemView.findViewById(R.id.newsnext_newstime_tv);
                newstype= (TextView) itemView.findViewById(R.id.newsnext_newsfrom_tv);
            }

        }
    }
    class MyViewHolderFott extends RecyclerView.ViewHolder{
        TextView tv;
        public MyViewHolderFott(View itemView) {
            super(itemView);
            tv= (TextView) itemView.findViewById(R.id.fotter);
        }
    }
    public RecyclerViewOnclickListener recyclerViewOnclickListener;

    public RecyclerViewOnclickListener getRecyclerViewOnclickListener() {
        return recyclerViewOnclickListener;
    }

    public void setRecyclerViewOnclickListener(RecyclerViewOnclickListener recyclerViewOnclickListener) {
        this.recyclerViewOnclickListener = recyclerViewOnclickListener;
    }

    public interface RecyclerViewOnclickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }
    public void changeState(int state){
        load_more_state=state;
        notifyItemChanged(newsList.size());
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

