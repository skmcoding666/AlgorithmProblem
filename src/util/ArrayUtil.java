package util;

import java.util.List;

public class ArrayUtil {

    public static <T> void showListList(List<List<T>> list) {
        if(list == null || list.size() == 0) {
            System.out.println("The List is empty");
            return;
        }
        StringBuilder temp = new StringBuilder();
        temp.append("The List : [\r\n");

        for(List tempList : list) {
            temp.append(getListBody(tempList));
        }
        temp.delete(temp.length()-2, temp.length()-1);
        temp.append("]\r\n");
        System.out.println(temp.toString());
    }

    public static <T> void showList(List<T> list) {
        System.out.println("The List :" + getListBody(list));
    }

    private static <T> StringBuilder getListBody(List<T> list) {
        StringBuilder temp = new StringBuilder();
        temp.append(" [");
        int count = 0;
        for(Object o : list) {
            temp.append(" " + o.toString() + ",");
            if(++count == 10) {
                count = 0;
                temp.append("\r\n");
            }
        }
        if(temp.lastIndexOf("\r\n") != temp.length() - 2) {
            temp.append("\r\n");
        }
        temp.append(" ]\r\n");
        return temp;
    }

}
