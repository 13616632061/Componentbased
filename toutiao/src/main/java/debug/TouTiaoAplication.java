package debug;

import com.library.app.LibAplication;

import org.litepal.LitePal;

/**
 * Created by Administrator on 2019/4/29.
 */

public class TouTiaoAplication extends LibAplication {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化LitePal数据库
        LitePal.initialize(getApplicationContext());
    }
}
