package Tomcat;

import com.intellij.notification.*;
import com.intellij.openapi.project.Project;
import org.apache.commons.io.FileUtils;

import java.io.*;

public class TomcatHelper {
    private static String resource = "C:\\ProgramData\\path.txt";
    private String tomcatPath;
    private String tomcatBinPath;
    private String tomcatWebappsPath;
    private NotificationGroup balloonNotifications;
    private Notification notify;




    public TomcatHelper() {
        File file = new File(resource);
        BufferedReader br = null;
        String tomcatPath;
        try {
            br = new BufferedReader(new FileReader(file));
            tomcatPath = br.readLine() ;
            this.tomcatPath = tomcatPath;
            this.tomcatBinPath = tomcatPath+"\\bin";
            this.tomcatWebappsPath = tomcatPath+"\\webapps";
            this.balloonNotifications = new NotificationGroup("Notification group", NotificationDisplayType.BALLOON, true);

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            }
        catch (IOException e) {
            e.printStackTrace();
            }
        }

        public void runTomcat(boolean inDebug) throws IOException {
            ProcessBuilder builder;
            if (inDebug) {
                builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \""+tomcatBinPath+"\" && startup.bat jpda start");
                }
            else{
                builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \""+tomcatBinPath+"\" && startup");
                }

            builder.redirectErrorStream(true);
            Process p = builder.start();
//            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
//            String line;
//            while (true) {
//                line = r.readLine();
//                if (line == null) { break; }
//                System.out.println(line);
//            }

    }
    public void shutdownTomcat(Project project) throws IOException {
        ProcessBuilder builder;
        builder = new ProcessBuilder(
                "cmd.exe", "/c", "cd \""+tomcatBinPath+"\" && shutdown");
        builder.redirectErrorStream(true);
        Process p = builder.start();
//        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
//        String line;
//        while (true) {
//            line = r.readLine();
//            if (line == null) { break; }
//            System.out.println(line);
//        }

    }
    public void copyWar(String warPath) throws IOException {
        File sourceFile = new File(warPath);
        String name = sourceFile.getName();
        File targetFile = new File(this.tomcatWebappsPath+name);
        FileUtils.copyFile(sourceFile, targetFile);

    }

    public void notify(Project project, String title, String message,NotificationType notificationType){
        Notification success = balloonNotifications.createNotification(title, message,notificationType, null);
        Notifications.Bus.notify(success, project);

    }

    public String getTomcatPath() {
        return tomcatPath;
    }

    public String getTomcatBinPath() {
        return tomcatBinPath;
    }

    public String getTomcatWebappsPath() {
        return tomcatWebappsPath;
    }
}
