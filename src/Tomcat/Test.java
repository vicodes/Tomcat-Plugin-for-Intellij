package Tomcat;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessHandler;

import java.nio.charset.Charset;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws ExecutionException {
        ArrayList<String> cmds = new ArrayList<>();
        cmds.add("dir");

        GeneralCommandLine generalCommandLine = new GeneralCommandLine(cmds);
        generalCommandLine.setCharset(Charset.forName("UTF-8"));
        //generalCommandLine.setWorkDirectory(project.getBasePath());

        ProcessHandler processHandler = new OSProcessHandler(generalCommandLine);
        processHandler.startNotify();
    }

}
