package aaa.cookandshop;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by USER on 8/8/2017.
 */

public class SavingThing {
    public static String toString(ArrayList<String> list) {
        String out = "";

        if (list.size() > 0)
            for (int i = 0; i < list.size() - 1; i++) {
                out += list.get(i) + "\n";
            }

        out += list.get(list.size() - 1);

        return out;
    }

    public static ArrayList<String> toArrayList(String string) {
        String[] arr = string.split("\n");

        return new ArrayList<>(Arrays.asList(arr));
    }
}
