package utils;

import java.io.IOException;

/**
 * Compressor utils.
 *
 * @author fagongzi
 */
public class CompressorUtils {

    public static void main(String[] args) {

        String src = "/Users/zachzhou/java-common/pom.xml";
        String dst = "/Users/zachzhou/java-common/myf tar.zip";
        System.out.println(dst.replaceAll(" ", "\\\\ "));
        try {
            CompressorUtils.zip(src, dst.replaceAll(" ", "\\\\ "), "123");
//            CompressorUtils.zip(src, dst, "123");
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    /**
     * zip
     *
     * @param src      src
     * @param dst      dst
     * @param password password
     * @throws IOException          process io error
     * @throws InterruptedException wait process interrupted
     */
    public static void zip(String src, String dst,
                           String password) throws IOException, InterruptedException {
        Process p = null;
        ProcessBuilder pb = new ProcessBuilder("sh", "-c", "zip  -qjr -P " + password + " " + dst + " " + src);
        try {
            p = pb.start();
            if (0 != p.waitFor()) {
                throw new IOException("invalid cmd: sh -c zip -qrj -P" + password + " " + dst + " " + src);
            }
        } finally {
            if (p != null) {
                p.destroy();
            }
        }
    }

    /**
     * 7z
     *
     * @param src      src
     * @param dst      dst
     * @param password password
     * @throws IOException process io error
     */
    public static void sevenZ(String src, String dst, String password) throws IOException, InterruptedException {
        Process p = null;
        ProcessBuilder pb = new ProcessBuilder(String.format("\"C:\\Program Files\\7-Zip\\7z.exe\" a \"%s\" -tzip -p%s -aoa \"%s\"", dst, password, src));
        try {
            p = pb.start();
            p.waitFor();
        } finally {
            if (p != null) {
                p.destroy();
            }
        }
    }
}
