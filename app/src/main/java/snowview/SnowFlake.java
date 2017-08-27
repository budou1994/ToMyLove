package snowview;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;


public class SnowFlake {

    private static final float ANGE_RANGE = 0.1f;// 角度范围
    private static final float HALF_ANGLE_RANGE = ANGE_RANGE / 2f;// 一半角度范围
    private static final float HALF_PI = (float) Math.PI / 2f;
    private static final float ANGLE_SEED = 25f;// 角度随机种子
    private static final float ANGLE_DIVISOR = 10000f;
    //雪花速度区间
    private static final float INCREMENT_LOWER = 2f;
    private static final float INCREMENT_UPPER = 4f;
    //雪花大小区间
    private static final float FLAKE_SIZE_LOWER = 7f;
    private static final float FLAKE_SIZE_UPPER = 20f;

    private final Point position; //雪花位置
    private float angle; // 雪花下落角度
    private final float increment; //雪花下落速度
    private final float flakeSize; // 雪花大小
    private final Paint paint; // 画笔

    public static SnowFlake create(int width, int height, Paint paint) {
        // TODO: 2017/8/21  可能会出现雪花飘落问题 int值x和y 改为float可能会解决
        int x = RandomGenerator.getRandom(width);
        int y = RandomGenerator.getRandom(height);
        Point position = new Point(x, y);//设置雪花初使位置
        float angle = RandomGenerator.getRandom(ANGLE_SEED) / ANGLE_SEED * ANGE_RANGE + HALF_PI - HALF_ANGLE_RANGE;
        float increment = RandomGenerator.getRandom(INCREMENT_LOWER, INCREMENT_UPPER);
        float flakeSize = RandomGenerator.getRandom(FLAKE_SIZE_LOWER, FLAKE_SIZE_UPPER);
        return new SnowFlake( position, angle, increment, flakeSize, paint);
    }

    public SnowFlake(Point position, float angle, float increment, float flakeSize, Paint paint) {
        this.position = position;
        this.angle = angle;
        this.increment = increment;
        this.flakeSize = flakeSize;
        this.paint = paint;
    }


    private void move(int width, int height) {
        //设置雪花偏移量
        double x = position.x + (increment * Math.cos(angle));
        double y = position.y + (increment * Math.sin(angle));

        //使雪花随机晃动
        angle += RandomGenerator.getRandom(-ANGLE_SEED, ANGLE_SEED) / ANGLE_DIVISOR;
        position.set((int) x, (int) y);

        if (!isInside(width, height)) {
            reset(width);//重置雪花
        }
    }

    private boolean isInside(int width, int height) {
        int x = position.x;
        int y = position.y;
        return x >= -flakeSize - 1 && x + flakeSize <= width && y >= -flakeSize - 1 && y - flakeSize < height;
    }

    private void reset(int width) {
        position.x = RandomGenerator.getRandom(width);
        position.y = (int) (-flakeSize - 1);
        angle = RandomGenerator.getRandom(ANGLE_SEED) / ANGLE_SEED * ANGE_RANGE + HALF_PI - HALF_ANGLE_RANGE;
    }

    //绘制雪花
    public void draw(Canvas canvas,Bitmap bitmap) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        move(width, height);
        canvas.drawBitmap(bitmap,position.x,position.y,paint);
//        canvas.drawCircle(position.x, position.y, flakeSize, paint);
    }
    public void draw(Canvas canvas) {
        move(canvas.getWidth(), canvas.getHeight());
        canvas.drawCircle((float) this.position.x, (float) this.position.y, this.flakeSize, this.paint);
    }
}
