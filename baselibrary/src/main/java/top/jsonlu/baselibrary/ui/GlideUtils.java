package top.jsonlu.baselibrary.ui;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

/**
 * Author:JsonLu
 * DateTime:2019/5/22 18:30
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class GlideUtils {

    public void load(Activity app, ImageView view, String url) {
//        Option.memory("com.bumptech.glide.load.model.stream.HttpGlideUrlLoader.Timeout", 5);
        Glide.with(app).load(url).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                call(0x0);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                call(0x1);
                return false;
            }
        }).into(view);
    }

    /**
     * @param type 0x0，失败；0x1，ok
     */
    protected void call(int type) {
        System.out.println("AAA," + type);
    }
}
