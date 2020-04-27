package cn.jjdcn.etas.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringToListUtils {

    public static List<Integer> splitIdsString(String ids) {
        List<String> strings = Arrays.asList(ids.split("-"));
        List<Integer> idss = new ArrayList<>();
        for (int i = 0; i < strings.size(); i++) {
            if("".equals(strings.get(i))) continue;
            idss.add((Integer.valueOf( strings.get(i))));
        }
        idss.stream().distinct().collect(Collectors.toList());
        return idss;
    }
}
