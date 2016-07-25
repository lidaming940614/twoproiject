package feicui.newsapp02.entity;

import android.widget.Button;

/**
 * Created by 1099057173 on 2016/7/23.
 */
public class AddNews {
    private Button show;
    private Button click;

    public AddNews(Button show, Button click) {
        this.show = show;
        this.click = click;
    }

    public Button getShow() {
        return show;
    }

    public void setShow(Button show) {
        this.show = show;
    }

    public Button getClick() {
        return click;
    }

    public void setClick(Button click) {
        this.click = click;
    }

    @Override
    public String toString() {
        return "AddNews{" +
                "show=" + show +
                ", click=" + click +
                '}';
    }
}
