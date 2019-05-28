package string;

public class StringParsing {

    public static int compareVersion(String v1, String v2) {

        if (v1 == null && v2 != null) {
            return - v2.length();
        } else if (v1 != null && v2 == null) {
            return v1.length();
        } else if (v1 == null && v2 == null) {
            return 0;
        }

        String[] a1 = v1.split("\\.");
        String[] a2 = v2.split("\\.");
        int idx = 0;
        int minLength = Math.min(a1.length, a2.length);
        int diff = 0;
        while (idx < minLength
                && (diff = a1[idx].length() - a2[idx].length()) == 0
                && (diff = a1[idx].compareTo(a2[idx])) == 0) {

            ++idx;
        }

        diff = (diff != 0) ? diff : a1.length - a2.length;
        return diff;
    }

    public static void main(String[] args) {
        int n1 = compareVersion(null,"1.0.0");
        int n2 = compareVersion("0.1", null);
        int n3 = compareVersion(null, null);

        int n4 = compareVersion("2.1", "10.0");
        int n5 = compareVersion("1.0b", "1.1a");

        System.out.println("n1: " + n1);
        System.out.println("n2: " + n2);
        System.out.println("n3: " + n3);
        System.out.println("n4: " + n4);
        System.out.println("n5: " + n5);
    }
}
