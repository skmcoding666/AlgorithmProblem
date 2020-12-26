package util;

import java.util.Date;

public class TimeUtil {

    private long time;

    public TimeUtil() {
        this.time = new Date().getTime();
    }

    public Object CalculationTime(String str, Object o) {
        System.out.println(str + " finished in " + (new Date().getTime() - this.time) + "ms");
        return o;
    }

}
