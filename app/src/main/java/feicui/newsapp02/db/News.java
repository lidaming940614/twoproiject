package feicui.newsapp02.db;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by 1099057173 on 2016/7/23.
 */
public class News implements Serializable {
    private String imgurl;
    private Bitmap newsimg;
    private String newsname;
    private String newsurl;
    private String newsdate;
    private String newstype;

    public News(String imgurl, Bitmap newsimg, String newsname, String newsurl, String newsdate, String newstype) {
        this.imgurl = imgurl;
        this.newsimg = newsimg;
        this.newsname = newsname;
        this.newsurl = newsurl;
        this.newsdate = newsdate;
        this.newstype = newstype;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public Bitmap getNewsimg() {
        return newsimg;
    }

    public void setNewsimg(Bitmap newsimg) {
        this.newsimg = newsimg;
    }

    public String getNewsname() {
        return newsname;
    }

    public void setNewsname(String newsname) {
        this.newsname = newsname;
    }

    public String getNewsurl() {
        return newsurl;
    }

    public void setNewsurl(String newsurl) {
        this.newsurl = newsurl;
    }

    public String getNewsdate() {
        return newsdate;
    }

    public void setNewsdate(String newsdate) {
        this.newsdate = newsdate;
    }

    public String getNewstype() {
        return newstype;
    }

    public void setNewstype(String newstype) {
        this.newstype = newstype;
    }

    @Override
    public String toString() {
        return "News{" +
                "imgurl='" + imgurl + '\'' +
                ", newsimg=" + newsimg +
                ", newsname='" + newsname + '\'' +
                ", newsurl='" + newsurl + '\'' +
                ", newsdate='" + newsdate + '\'' +
                ", newstype='" + newstype + '\'' +
                '}';
    }
}
