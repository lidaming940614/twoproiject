package feicui.newsapp02.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import feicui.newsapp02.db.News;

/**
 * Created by 1099057173 on 2016/7/23.
 */
public class NetWorkManager {
    Context gC;

    public NetWorkManager(Context gC) {
        this.gC = gC;
    }

    File file=new File("data"+File.separator+"data"+File.separator+"com.example.hrr.newaday"+File.separator+"img");
    public static String  getDataByHttpUrlGET(String path){
        //定义返回值
        String result=null;
        StringBuffer stringBuffer=new StringBuffer();//创建stringbuffer实例，用来装载从网络获取的数据
        try {
            URL url=new URL(path);//建立一个URL
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();//通过通道打开连接
            connection.setConnectTimeout(5000);//设置连接超时
            connection.setReadTimeout(5000);//设置请求超时
            if(connection.getResponseCode()==200){//获取返回码，200表示成功
                InputStream in=connection.getInputStream();//获得从网络请求的输入流
                BufferedInputStream bis=new BufferedInputStream(in);//将输入流转换成缓冲区
                byte[] b=new byte[1024];
                int lenth=0;
                while ((lenth=bis.read(b))!=-1){//循环读取缓冲区的内容
                    stringBuffer.append(new String(b,0,lenth));//将读取的内容拼接到stringbuffer中
                }
                result=stringBuffer.toString();
                connection.disconnect();//关闭http连接
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (UnknownHostException e){
            result="连接超时";//添加连接超时方法时，需手动抛出连接超时异常
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static String httpcookbookpost(String url,String name,String key){
        String result=null;
        StringBuilder sb =new StringBuilder();
        try {
            URL url1=new URL(url);
            HttpURLConnection connection= (HttpURLConnection) url1.openConnection();//打开连接通道
            connection.setRequestMethod("POST");//设置方式
            connection.setConnectTimeout(5000);//设置连接超时
            connection.setReadTimeout(5000);//设置请求超时
            connection.setDoOutput(true);//设置可以输出x
            connection.setUseCaches(false);//不能使用缓冲
            String data = "key="+key
                    + "&type=" + URLEncoder.encode(name, "UTF-8");
            byte[]b=data.getBytes();
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", String.valueOf(b.length));//设置长度
            OutputStream os=connection.getOutputStream();//得到输出流对象
            os.write(b);
            if (connection.getResponseCode()==200){//200代表连接过程中
                InputStream is =connection.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                byte[]b1 = new byte[1024];
                int len=0;
                while ((len=bis.read(b1))!=-1){
                    sb.append(new String(b1,0,len));//将获得到输入流的内容拼接起来
                }
                result=sb.toString();
                connection.disconnect();//关闭http连接
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (UnknownHostException e){
            result="请求超时";
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 解析方法
     * @param s
     * @return
     */
    public List<News> jsonnetwork(String s){
        List<News> news = new ArrayList<News>();
        try {
            JSONObject jo=new JSONObject(s);
            JSONObject joresult=jo.getJSONObject("result");
            JSONArray dataarray=joresult.getJSONArray("data");
            for (int i=0;i<dataarray.length();i++){
                JSONObject joarray=dataarray.getJSONObject(i);
                String jotitle =joarray.getString("title");
                String jodate=joarray.getString("date");
                String jotype=joarray.getString("type");
                String joimg=joarray.getString("thumbnail_pic_s");
                String jourl=joarray.getString("url");
                Bitmap nesimg=getbitmap(joimg);
                News news1=new News(joimg,nesimg,jotitle,jourl,jodate,jotype);
                news.add(news1);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return news;
    }

    /**
     * 图片获取方法
     * @param imgurl
     * @return
     */
    public Bitmap getbitmap(String imgurl){
        Bitmap bitmap = null;
        try {
            URL url=new URL(imgurl);
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream in = httpURLConnection.getInputStream();
            bitmap= BitmapFactory.decodeStream(in);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

}
