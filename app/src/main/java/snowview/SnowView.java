package snowview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.budou.snow.R;

/**
 * package：com.budou.snow
 * author : 布兜小爱
 * e-mail : budou1994@qq.com
 * time   : 2017年08月21日 21:17
 * desc   ：自定义一个雪花飘落的view
 */
public class SnowView extends View {

    private static final int DEFAULT_FLAKES_NUMBER = 150;// 页面中雪花默认的数量
    private static final int DEFAULT_DELAY = 5;// 页面刷新的延迟

    private int mFLAKESNumber = DEFAULT_FLAKES_NUMBER;// 雪花可设置的数量
    private int mDelay = DEFAULT_DELAY;// 页面刷新的延迟(可设置)
    private static final int FLAKES_SCALE = 3;// 雪花默认大小
    private int mImgId, mScale, mRawWidth;
    private Bitmap bitmap;
    private Paint paint;
    private SnowFlake[] flakes;


    public SnowView(Context context) {
        super(context);
    }

    public SnowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    public SnowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        setBackgroundResource(R.drawable.xmn);
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.snow);
            mImgId = a.getResourceId(R.styleable.snow_flakeSrc, R.drawable.heart);
            mFLAKESNumber = a.getInteger(R.styleable.snow_flakeNumber, DEFAULT_FLAKES_NUMBER);
            mDelay = a.getInteger(R.styleable.snow_flakeDelay, DEFAULT_DELAY);
            mScale = a.getInteger(R.styleable.snow_flakeScale, FLAKES_SCALE);
            a.recycle();
        }
        //设置画笔
        // 抗锯齿
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
    }

    /**
     * @param w    当前宽
     * @param h    当前高
     * @param oldw 以前宽
     * @param oldh 以前高
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw || h != oldh) {
            //加载材料
            mRawWidth = initScale(mDelay);
            //对数量进行处理
            initNumber(w, h, mRawWidth);
        }
    }

    /**
     * 对雪花的进行初始化处理
     *
     * @param w
     * @param h
     * @param mRawWidth
     */
    private void initNumber(int w, int h, int mRawWidth) {
        flakes = new SnowFlake[mFLAKESNumber];
        for (int i = 0; i < mFLAKESNumber; i++) {
            flakes[i] = SnowFlake.create(w, h, paint);
        }
    }

    private int initScale(int mDelay) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;// 设置为TRUE，在解码的时候只会获取尺寸，不会将bitmap加入内存
        mRawWidth = options.outWidth;
        options.inSampleSize = mScale;// mScan 如果小于1，就会被当做1处理；大于1，则按比例处理图片。
        bitmap = BitmapFactory.decodeResource(getResources(), mImgId, options);
        return mRawWidth;
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (SnowFlake flake : flakes
                ) {
            //todo  在这个地方 传递过去的bitmap对象会报空指针的
            flake.draw(canvas );
//            flake.draw(canvas,BitmapFactory.decodeResource(getResources(),R.drawable.heart));
        }

        getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        }, mDelay);
    }
}
