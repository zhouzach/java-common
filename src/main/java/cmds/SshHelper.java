package cmds;

import models.JobResult;
import net.neoremind.sshxcute.core.ConnBean;
import net.neoremind.sshxcute.core.Result;
import net.neoremind.sshxcute.core.SSHExec;
import net.neoremind.sshxcute.exception.TaskExecFailException;
import net.neoremind.sshxcute.task.CustomTask;
import net.neoremind.sshxcute.task.impl.ExecCommand;

public class SshHelper {

    public static void main(String[] args) {
        String host = "";
        String user = "";
        String password = "";
        StringBuilder cmd = new StringBuilder();

        execTask(host, user, password, cmd.toString());
    }

    public static void execTask(String host, String user, String password, String cmd){
        SSHExec ssh = getInstance(host, user, password);
        if (ssh.connect()) {
            CustomTask task = new ExecCommand(cmd);

            try {
                Result result = ssh.exec(task);

                if (0 != result.rc) {
                    JobResult jobResult = new JobResult();
                    jobResult.setCode(result.rc);
                    jobResult.setErrorMsg(result.error_msg);
                    jobResult.setSysout(result.sysout);
                }
            } catch (TaskExecFailException e) {
                e.printStackTrace();
            }

            ssh.disconnect();
        }

    }

    public static SSHExec getInstance(String host,
                                      String user,
                                      String password) {
        ConnBean cb = new ConnBean(host, user, password);
        return SSHExec.getInstance(cb);
    }
}
