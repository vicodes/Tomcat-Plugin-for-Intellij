package Tomcat;

import com.intellij.notification.*;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


public class TomcatPathSetup extends AnAction {
    // If you register the action from Java code, this constructor is used to set the menu item name
    // (optionally, you can specify the menu description and an icon to display next to the menu item).
    // You can omit this constructor when registering the action in the plugin.xml file.
    public TomcatPathSetup() {
        // Set the menu item name.
        super("Tomcat _path");
        // Set the menu item name, description and icon.
        // super("Text _Boxes","Item description",IconLoader.getIcon("/Mypackage/icon.png"));
    }

    public void actionPerformed(AnActionEvent event) {
        Project project = event.getData(PlatformDataKeys.PROJECT);
        TomcatHelper tomcatHelper = new TomcatHelper();
        String currentTomcatPath = tomcatHelper.getTomcatPath();
        String tomcatPath = Messages.showInputDialog(project, "Current Tomcat Directory:"+currentTomcatPath, "Path", Messages.getQuestionIcon());
        try{
            assert tomcatPath != null;
            if (!tomcatPath.isEmpty() || !tomcatPath.isBlank()) {
            setPathForTomcat(project,tomcatPath);
            tomcatHelper.notify(project,"Path Updated.","Path:"+tomcatPath,NotificationType.INFORMATION);
        }
        }
        catch (Exception e){
           tomcatHelper.notify(project,"Path Updated.","Path update failed:"+tomcatPath+" !!!",NotificationType.INFORMATION);

        }


    }

    void setPathForTomcat(Project project,String tomcatPath){

            PrintWriter writer = null;
            try {
                writer = new PrintWriter("C:\\ProgramData\\path.txt", "UTF-8");
                writer.println(tomcatPath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
                writer.close();

    }
}