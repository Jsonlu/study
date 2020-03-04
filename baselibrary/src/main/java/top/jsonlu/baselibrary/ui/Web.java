package top.jsonlu.baselibrary.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.webkit.WebView;

/**
 * Author:JsonLu
 * DateTime:2020/1/9 11:18
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class Web extends WebView {

    private CallBack callBack;
    private GestureDetector gestureDetector;
    private Context context;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public Web(Context context) {
        super(context);
        this.context = context;
    }

    public Web(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public Web(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gestureDetector == null) {
            gestureDetector = new GestureDetector(context, new YScrollDetector());
        }
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public interface CallBack {
        void onBottom(boolean s);

        void onLeft(boolean s);
    }

    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float x = e2.getX() - e1.getX();
            float y = e2.getY() - e1.getY();
            if (callBack != null) {
                if (x > 170) {
                    callBack.onLeft(true);
                } else if (x < -170) {
                    callBack.onLeft(false);
                }
                if (y > 170) {
                    callBack.onBottom(false);
                } else if (y < -170) {
                    callBack.onBottom(true);
                }
            }

            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }
}
