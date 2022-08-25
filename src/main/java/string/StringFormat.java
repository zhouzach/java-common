package string;

public class StringFormat {
    public static final String INSERT_TEMPLATE = "insert into %s (id, title, author, price, qty) values (?,?,?,?,?)";
    public static final String INPUT_TABLE = "books";

    public static void main(String[] args){
        String str = String.format(INSERT_TEMPLATE, INPUT_TABLE);

        String t1 = "upsert into user_flink values(%d,'%s',23,1596524600481)";
        String str1 = String.format(t1, 1,"male");

        System.out.println(str1);
    }
}
