package feicui.newsapp02.entity;

import android.support.v4.app.Fragment;
import android.widget.ImageView;

/**
 * Created by 1099057173 on 2016/7/23.
 */
public class HomeButtom {
    private ImageView imageView;
    private int chickbefore;
    private int chickafter;
    private Fragment chickfragment;

    public HomeButtom(ImageView imageView, int chickbefore, int chickafter, Fragment chickfragment) {
        this.imageView = imageView;
        this.chickbefore = chickbefore;
        this.chickafter = chickafter;
        this.chickfragment = chickfragment;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public int getChickbefore() {
        return chickbefore;
    }

    public void setChickbefore(int chickbefore) {
        this.chickbefore = chickbefore;
    }

    public int getChickafter() {
        return chickafter;
    }

    public void setChickafter(int chickafter) {
        this.chickafter = chickafter;
    }

    public Fragment getChickfragment() {
        return chickfragment;
    }

    public void setChickfragment(Fragment chickfragment) {
        this.chickfragment = chickfragment;
    }

    @Override
    public String toString() {
        return "HomeButtom{" +
                "imageView=" + imageView +
                ", chickbefore=" + chickbefore +
                ", chickafter=" + chickafter +
                ", chickfragment=" + chickfragment +
                '}';
    }
}
