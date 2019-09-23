package sys;

import java.util.List;

public class ProcessHandleObj {

    public static void main(String[] args){

        getPidInfo(89508);


    }

    public static ProcessHandle getPidInfo(long pid){
        ProcessHandle processHandle = ProcessHandle.of(pid).get();
        System.out.println(processHandle.info());

        return processHandle;
    }
}
