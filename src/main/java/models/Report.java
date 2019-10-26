package models;

import lombok.Data;

public class Report {

    @Data
    public static class Cell {
        public Integer x;
        public Integer y;
        public String value;
    }
}
