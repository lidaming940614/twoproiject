package feicui.newsapp02.db;

import java.io.Serializable;

/**
 * Created by 1099057173 on 2016/7/23.
 */
public class Share implements Serializable {
    private int imgid;
    private String sharename;

    public Share(int imgid, String sharename) {
        this.imgid = imgid;
        this.sharename = sharename;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }

    public String getSharename() {
        return sharename;
    }

    public void setSharename(String sharename) {
        this.sharename = sharename;
    }

    @Override
    public String toString() {
        return "Share{" +
                "imgid=" + imgid +
                ", sharename='" + sharename + '\'' +
                '}';
    }
}
