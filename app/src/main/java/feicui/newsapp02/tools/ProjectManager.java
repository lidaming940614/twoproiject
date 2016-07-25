package feicui.newsapp02.tools;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by 1099057173 on 2016/7/23.
 */
public class ProjectManager {
    static Context mC;
    SharedPreferences.Editor spfe;
    SharedPreferences spf;
    private ProjectManager(){

    }
    private static ProjectManager projectManager;

    public static ProjectManager getspfManager(Context context){
        mC=context;
        if(projectManager ==null){
            projectManager =new ProjectManager();
        }
        return projectManager;
    }

    public void savemassage(String name){
        spfe=mC.getSharedPreferences(name,Context.MODE_PRIVATE).edit();
    }

    public void getmassage(String name){
        spf=mC.getSharedPreferences(name,Context.MODE_PRIVATE);
    }

    public String islogin(){
        getmassage("user");
        String name=spf.getString("name","no");
        return name;
    }

    public void savelogin(String name){
        savemassage("user");
        spfe.putString("name",name);
        spfe.commit();
    }

    public String getlead(){
        getmassage("islead");
        String islead=spf.getString("lead","no");
        return islead;
    }


    public void savefirst(String islead){
        savemassage("islead");
        spfe.putString("lead",islead);
        spfe.commit();
    }


    public String isfirst(){
        getmassage("isfirst");
        String isfirst=spf.getString("one","isfirst");
        return isfirst;
    }
    public void saveone(String s){
        savemassage("isfirst");
        spfe.putString("first",s);
        spfe.commit();
    }

    public void savetabshow(Set<String> tab){
        savemassage("istabshow");
        spfe.putStringSet("tab",tab);
        spfe.commit();
    }
    public Set<String> gettabshow(){
        getmassage("istabshow");
        Set<String> tab=spf.getStringSet("tab",null);
        return tab;
    }

}
