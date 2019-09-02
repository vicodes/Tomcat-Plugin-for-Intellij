package Tomcat;

import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

import java.io.IOException;

public class Debug extends AnAction {
    // If you register the action from Java code, this constructor is used to set the menu item name
    // (optionally, you can specify the menu description and an icon to display next to the menu item).
    // You can omit this constructor when registering the action in the plugin.xml file.
    public Debug() {
        // Set the menu item name.
        super("_Debug");
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
            if(!WebAppName.isEmpty() || !WebAppName.isBlank()) {
                tomcatHelper.copyWar(warPath);
                tomcatHelper.runTomcat(true);
                tomcatHelper.notify(project,"Tomcat","Started in Jpda.", NotificationType.INFORMATION);
            }
            else{
                tomcatHelper.notify(project,"Error!","Please enter the Webapp name to deploy.", NotificationType.ERROR);
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}