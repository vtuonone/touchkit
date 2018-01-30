/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vaadin.touchkit;

import javax.servlet.ServletException;
import org.vaadin.touchkit.server.TouchKitServlet;
import org.vaadin.touchkit.settings.TouchKitSettings;

/**
 *
 * @author mstahv
 */
public class TestTouchKitServlet extends TouchKitServlet {

    @Override
    protected void servletInitialized() throws ServletException {
        super.servletInitialized();
        TouchKitSettings touchKitSettings = getTouchKitSettings();
        
        touchKitSettings.getWebAppSettings().setApplicationName("TouchKit test app");
        touchKitSettings.getWebAppSettings().setApplicationShortName("TouchKitTest");
        touchKitSettings.getApplicationIcons().addApplicationIcon(128, 128, "image.png", true);
        
    }
}
