package utils;

import models.Report;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Sorter {

    // firstly sorted by cell's x
    Comparator<Report.Cell> firstComparator = Comparator.comparing(Report.Cell::getX);
    // secondly sorted by cell's y
    Comparator<Report.Cell> secondComparator = firstComparator.thenComparing(Comparator.comparing(Report.Cell::getY));

    public  List<Report.Cell> sort(List<Report.Cell> cells){
        return cells.stream().sorted(secondComparator).collect(Collectors.toList());
    }
}
