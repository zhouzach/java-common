package utils;

import java.sql.ResultSet;

public interface CallBack {
    Object deal(Object obj);
}

class CallBackDemo{
    public static void main(String[] args) {
        new CallBack(){
            public Object deal(Object obj) {
                try {

                    if (obj instanceof ResultSet) {
                        ResultSet result = (ResultSet) obj;
                        StringBuilder builder = new StringBuilder();
                        while (result.next()) {
                            builder.append(result.getString(1) + "\n");
                        }

                        return builder.toString();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }

        };
    }
}