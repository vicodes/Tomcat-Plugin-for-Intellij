package Tomcat;

import com.intellij.notification.*;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import java.io.*;


public class DeployWar extends AnAction {

    public DeployWar() {
        // Set the menu item name.
        super("Deploy _war");
        // Set the menu item name, description and icon.
        // super("Text _Boxes","Item description",IconLoader.getIcon("/Mypackage/icon.png"));
    }

    public void actionPerformed(AnActionEvent event) {
        Project project = event.getData(PlatformDataKeys.PROJECT);
        String WebAppName= Messages.showInputDialog(project, "Enter Webapp Name:", "webApp", Messages.getQuestionIcon());
        String basePath = project.getBasePath().replace("/","\\");
        String war = basePath+"\\"+WebAppName+"\\target\\"+WebAppName+".war";

        TomcatHelper tomcatHelper = new TomcatHelper();

        String warPath = war;
        try {
            if( WebAppName != null || !WebAppName.isBlank()) {
                tomcatHelper.copyWar(warPath);
                tomcatHelper.runTomcat(false);
                tomcatHelper.notify(project,"Information","Service deployed:"+WebAppName,NotificationType.INFORMATION);
            }
            else{
                tomcatHelper.notify(project,"Information", "No war deployed..", NotificationType.ERROR);
            }
        } catch (IOException e) {
            tomcatHelper.notify(project,"Information", "No war deployed..", NotificationType.ERROR);

        }
    }


}