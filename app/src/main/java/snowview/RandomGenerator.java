package snowview;

import java.util.Random;

/**
 * package：com.budou.snow
 * author : 布兜小爱
 * e-mail : budou1994@qq.com
 * time   : 2017年08月21日 21:17
 * desc   ：随机数生成器
 */

public class RandomGenerator {

    private static final Random RANDOM = new Random();


    /**
     * @param lower
     * @param upper
     * @return 返回lower和upper区间的一个随机数
     */
    public static float getRandom(float lower, float upper) {
        float min = Math.min(lower, upper);
        float max = Math.max(lower, upper);
        return getRandom(max - min) + min;
    }

    /**
     * @param upper  随机的float值
     * @return 返回一个0~upper的float值
     */
    public static float getRandom(float upper) {
        return RANDOM.nextFloat() * upper;
    }

    /**
     * @param upper 随机int值
     * @return 返回一个小于upper的int值的随机数
     */
    public static int getRandom(int upper) {
        return RANDOM.nextInt(upper);
    }




    public static void main(String[] strings) {
        System.out.println(getRandom(1f,10f));
    }
}
