package Tomcat;

import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.apache.commons.io.FileUtils;
import java.io.*;


public class Kill extends AnAction {

    public Kill() {
        // Set the menu item name.
        super("_Stop");
        // Set the menu item name, description and icon.
        // super("Text _Boxes","Item description",IconLoader.getIcon("/Mypackage/icon.png"));
    }

    public void actionPerformed(AnActionEvent event) {
        Project project = event.getData(PlatformDataKeys.PROJECT);

        TomcatHelper tomcatHelper = new TomcatHelper();
        try {
            tomcatHelper.shutdownTomcat(project);
            tomcatHelper.notify(project,"Tomcat","Killed all Instance", NotificationType.INFORMATION);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}