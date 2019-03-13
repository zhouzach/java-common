package models;


import lombok.Data;

@Data
public class JobResult {
    private int code;
    private String errorMsg;
    private String sysout;
}
