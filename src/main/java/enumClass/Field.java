package enumClass;

import java.util.Arrays;

public enum Field {
    SMS("mobile"),
    WECHATPOP("wechat_unionid"),
    CMA("member_id"),
    EMAIL("wechat_unionid");

    private String value;

    Field(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static void main(String[] args){
        Field[] fields = new Field[]{Field.SMS,Field.WECHATPOP};

        Arrays.stream(fields).forEach(f -> System.out.println(f.name()));
    }
}
