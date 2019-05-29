package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class FileUtil {

    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static void readBigFile(String filepath, int bufferSize) {


        try {

            // creage Buffered InputStream from InputStream
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(filepath)));


            //create InputStream Reader from InputStream
            InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream, "utf-8");


            // create buffered Reader from reader
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader, bufferSize);

            FileWriter output = new FileWriter("/home/haoqiong/part" + ".txt");
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                output.append(line + "\r");
            }

            output.flush();
            output.close();

            bufferedReader.close();
            inputStreamReader.close();
            bufferedInputStream.close();

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public static void main(String[] args) {
        long timer = System.currentTimeMillis();
        // 用5M的缓冲读取文本文件
        readBigFile("", 5 * 1024 * 1024);

        timer = System.currentTimeMillis() - timer;
        System.out.println("处理时间：" + timer);
    }
}
