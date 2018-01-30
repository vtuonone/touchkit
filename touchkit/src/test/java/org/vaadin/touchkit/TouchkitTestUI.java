package org.vaadin.touchkit;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("touchkit")
@Title("TouchKit test app")
public class TouchkitTestUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        
        

        String requestPathInfo = request.getPathInfo();
        if (requestPathInfo.length() > 3) {
            try {

                String className;
                if (requestPathInfo.startsWith("/org.")) {
                    className = requestPathInfo.substring(1);
                } else {
                    className = getClass().getPackage().getName() + ".itest."
                            + requestPathInfo.substring(1);
                }
                if(className.contains("/")) {
                    className = className.substring(0, className.indexOf("/"));
                }
                Class<?> forName = Class.forName(className);
                if (forName != null) {
                    AbstractComponentContainer newInstance = (AbstractComponentContainer) forName
                            .newInstance();
                    newInstance.setDescription(null);
                    setContent(newInstance);
                    System.out.println("Initialized " + className);
                }
                return;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                // e.printStackTrace();
            } catch (IllegalAccessException e) {
                // e.printStackTrace();
            }
        }

        File itestroot = new File("src/test/java/"
                + getClass().getPackage().getName().replace(".", "/")
                + "/itest");

        Collection<Class<? extends AbstractComponent>> tests = new ArrayList<Class<? extends AbstractComponent>>();
        addTests(getClass().getPackage().getName() + ".itest", itestroot,
                tests);

        Grid<Class<? extends AbstractComponent>> table = new Grid<>();
        table.setItems(tests);
        table.addColumn(c -> c.getSimpleName()).setCaption("name");
        table.addColumn(c -> {
            try {
                AbstractComponent t = (AbstractComponent) c.newInstance();
                String description = t.getDescription();
                if (description == null) {
                    description = "-";
                }
                return description;
            } catch (Exception e) {
                return "";
                // e.printStackTrace();
            }
        }).setCaption("description");
        table.addItemClickListener(event -> {
            String canonicalName = event.getItem().getCanonicalName();
            String debug = debugmode.getValue() ? "?debug" : "";
            Page.getCurrent().open(canonicalName + debug, null);
        });
        table.setSizeFull();
        debugmode.setValue(false);
        HorizontalLayout options = new HorizontalLayout();
        options.addComponent(debugmode);
        addComponent(options);
        content.addComponent(table);
        content.setExpandRatio(table, 1);
        setTheme("valo");
    }

    CheckBox debugmode = new CheckBox("Open in debug");

    VerticalLayout content = new VerticalLayout();

    private void addComponent(Component component) {
        content.setSizeFull();
        if (content.getParent() == null) {
            setContent(content);
        }
        content.addComponent(component);
    }

    private void addTests(String base, File itestroot,
            Collection<Class<? extends AbstractComponent>> tests) {
        File[] listFiles = itestroot.listFiles();
        for (File file : listFiles) {
            if (file.isDirectory()) {
                addTests(base + "." + file.getName(), file, tests);
            } else if (file.getName().endsWith(".java")) {
                String name = file.getName().substring(0,
                        file.getName().indexOf("."));
                try {
                    Class<?> forName = Class.forName(base + "." + name);

                    if (AbstractComponent.class.isAssignableFrom(forName)) {
                        tests.add((Class<? extends AbstractComponent>) forName);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
