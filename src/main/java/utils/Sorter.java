package utils;

import models.Report;

import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;

public class Sorter {


    public  List<Report.Cell> secondarySort(List<Report.Cell> cells){
        // firstly sorted by cell's x
        Comparator<Report.Cell> firstComparator = Comparator.comparing(Report.Cell::getX);
        // secondly sorted by cell's y
        Comparator<Report.Cell> secondComparator = firstComparator.thenComparing(Comparator.comparing(Report.Cell::getY));

        return cells.stream().sorted(secondComparator).collect(Collectors.toList());
    }

    public static void sort4Chinese() {

        // http://zhuhengwei.com/2016/02/27/Java%E8%87%AA%E5%AE%9A%E4%B9%89%E6%AF%94%E8%BE%83%E5%99%A8%E5%AE%9E%E7%8E%B0%E4%B8%AD%E6%96%87%E6%8E%92%E5%BA%8F/
        // https://blog.csdn.net/yangchaofeng1229/article/details/6765031
        List<String> list = new ArrayList<>();
        list.add("b国中"); // 中->20013 unicode编码的4E2D
        list.add("b国美"); // 中->20013 unicode编码的4E2D
        list.add("国英"); // 英-->33521 unicode编码的82F1
        list.add("1国英"); // 英-->33521 unicode编码的82F1
        list.add("1国美"); // 英-->33521 unicode编码的82F1
        list.add("国美"); // 美->32654 unicode编码的7F8E
        // 汉字unicode编码表 http://www.chi2ko.com/tool/CJK.htm
        // 输出字符编码对应的十进制
        //char a = '美';
        //System.out.println((int) a);

        System.out.println("排序前-->" + list);
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String o1 = "";
                String o2 = "";
                if (s1 != null) o1 = s1;
                if (s2 != null) o2 = s2;
                Collator instance = Collator.getInstance(Locale.CHINA);
                return instance.compare(o1, o2);
            }
        });
        System.out.println("排序后-->" + list);
        Collections.reverse(list);
        System.out.println("排序后逆序-->" + list);
    }

    public static void main(String[] args) {
        sort4Chinese();
    }
}
