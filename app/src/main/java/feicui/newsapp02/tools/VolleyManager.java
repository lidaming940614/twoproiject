package feicui.newsapp02.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import feicui.newsapp02.R;
import feicui.newsapp02.db.News;
import feicui.newsapp02.db.VolleySingleton;

/**
 * Created by 1099057173 on 2016/7/23.
 */
public class VolleyManager {
        Context mC;
String result;
        Bitmap img=null;
        List<News> news = new ArrayList<News>();
private HandResult handResult;
private HandImg handImg;

public HandResult getHandResult() {
        return handResult;
        }

public void setHandResult(HandResult handResult) {
        this.handResult = handResult;
        }

public HandImg getHandImg() {
        return handImg;
        }

public void setHandImg(HandImg handImg) {
        this.handImg = handImg;
        }

public VolleyManager(Context mC) {
        this.mC = mC;
        }

/**
 * 聚合数据获取
 * @param url
 */
public void getResult(String url){
        StringRequest stringRequest=new StringRequest(Request.Method.GET,url,
        new Response.Listener<String>() {
@Override
public void onResponse(String s) {
        Log.i("juhes",s);
        jsonnetwork(s);
        }
        }, new Response.ErrorListener() {
@Override
public void onErrorResponse(VolleyError volleyError) {
        }
        });
        VolleySingleton.getVolleySingleton(mC).addToRequestQueue(stringRequest);
        }
/**
 * 聚合数据解析方法
 * @param s
 * @return
 */
public void jsonnetwork(String s){
        try {
        JSONObject jo=new JSONObject(s);
        JSONObject joresult=jo.getJSONObject("result");
        JSONArray dataarray=joresult.getJSONArray("data");
        for (int i=0;i<dataarray.length();i++){
        JSONObject joarray=dataarray.getJSONObject(i);
final String jotitle =joarray.getString("title");
        String jodate=joarray.getString("date");
        String jotype=joarray.getString("author_name");
        String joimg=joarray.getString("thumbnail_pic_s");
final String jourl=joarray.getString("url");
        getbitmap(joimg,jotitle,jourl,jodate,jotype);
        }
        } catch (JSONException e) {
        e.printStackTrace();
        }
}

/**
 * 获取图片方法
 * @param url
 * @param name
 * @param newsurl
 * @param date
 * @param type
 */
public void getbitmap(final String url, final String name, final String newsurl, final String date, final String type){
        ImageRequest imageRequest=new ImageRequest(url, new Response.Listener<Bitmap>() {
@Override
public void onResponse(Bitmap bitmap) {
        if (handImg!=null){
        handImg.hand(url,bitmap,name,newsurl,date,type);
        }
        }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
@Override
public void onErrorResponse(VolleyError volleyError) {
        }
        });
        VolleySingleton.getVolleySingleton(mC).addToRequestQueue(imageRequest);
        }
/**
 * 百度数据获取
 * @param url
 */
public void getBaiResult(String url){
        StringRequest stringRequest=new StringRequest(Request.Method.POST,url,
        new Response.Listener<String>() {
@Override
public void onResponse(String s) {
        jsonBainetwork(s);
        }
        }, new Response.ErrorListener() {
@Override
public void onErrorResponse(VolleyError volleyError) {
        Log.i("home_search","请求失败");
        }
        });
        VolleySingleton.getVolleySingleton(mC).addToRequestQueue(stringRequest);
        }
/**
 * 百度数据解析方法
 * @param s
 * @return
 */
public void jsonBainetwork(String s){
        try {
        Log.i("news",s);
        JSONObject jo=new JSONObject(s);
        JSONArray dataarray=jo.getJSONArray("newslist");
        for (int i=0;i<dataarray.length();i++){
        JSONObject joarray=dataarray.getJSONObject(i);
        String jodate=joarray.getString("ctime");
        String jotype=joarray.getString("description");
final String jotitle =joarray.getString("title");
        String joimg=joarray.getString("picUrl");
final String jourl=joarray.getString("url");
        getbitmap(joimg,jotitle,jourl,jodate,jotype);
        }
        } catch (JSONException e) {
        e.printStackTrace();
        }
        }
public interface HandResult{
    void hand(String s);
}
public interface HandImg{
    void hand(String imgurl,Bitmap bitmap,String name,String url,String date,String type);
}

    /**
     * 使用imagerloader加载图片
     * @param imgurl
     * @param iv
     */
    public void setImagerLoader(String imgurl,ImageView iv){
        RequestQueue requestQueue=VolleySingleton.getVolleySingleton(mC).getRequestQueue();
        ImageLoader mImageLoader=new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String s) {

                return null;
            }

            @Override
            public void putBitmap(String s, Bitmap bitmap) {

            }
        });
        ImageLoader.ImageListener listener=ImageLoader.getImageListener(iv, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        mImageLoader.get(imgurl,listener);
    }
}
