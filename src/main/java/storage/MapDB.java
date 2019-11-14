package storage;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import java.util.concurrent.ConcurrentMap;

//https://jankotek.gitbooks.io/mapdb/content/quick-start/
public class MapDB {

    //opens in-memory HashMap, it uses off-heap store and it is not limited by Garbage Collection
    public static void memoryDb(){
        DB db = DBMaker.memoryDB().make();
        ConcurrentMap map = db.hashMap("map").createOrOpen();
        map.put("something", "memoryDb");

        Object object = map.get("something");
        System.out.println(object);
    }

    /**
     * HashMap (and other collections) can be also stored in file. In this case the content can be preserved between JVM restarts.
     * It is necessary to call DB.close() to protect file from data corruption. Other option is to enable transactions with write ahead log.
     *
     * By default, MapDB uses generic serialization, which can serialize any data type. It is faster and more memory efficient to use specialized serializers.
     * Also we can enable faster memory-mapped files on 64bit operating systems
     */
    public static void fileDb(){
        DB db = DBMaker
                .fileDB("file.db")
                .fileMmapEnable() //64bit operating systems
                .make();
        ConcurrentMap map = db.hashMap("map",Serializer.STRING, Serializer.LONG).createOrOpen();
        map.put("something", 111L);
        Object object = map.get("something");
        System.out.println(object);
        db.close();
    }

    public static void main(String[] args) {

//        memoryDb();

        fileDb();
    }
}
