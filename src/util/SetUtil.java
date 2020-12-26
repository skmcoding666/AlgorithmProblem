package util;

import java.util.Iterator;
import java.util.Set;

public class SetUtil {

    public static <T> void showSet (Set<T> set) {
        if(set == null || set.size() == 0) {
            System.out.println("The Set is empty");
            return;
        }
        StringBuilder temp = new StringBuilder();
        temp.append("The Set : [\r\n");
        Iterator i = set.iterator();
        int count = 0;
        while(i.hasNext()) {
            temp.append(" " + i.next().toString() + ",");
            if(++count == 10) {
                count = 0;
                temp.append("\r\n");
            }
        }
        temp.delete(temp.lastIndexOf(","), temp.lastIndexOf(",") + 1);
        if(temp.lastIndexOf("\r\n") != temp.length() - 2) {
            temp.append("\r\n");
        }
        temp.append("]");
        System.out.println(temp.toString());
    }
}
