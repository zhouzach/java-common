package models;

import lombok.Data;

public class Report {

    @Data
    public static class Cell {
        private Integer x;
        private Integer y;
        private String value;
    }
}
