package org.vaadin.touchkit.itest;

import org.vaadin.touchkit.AbstractTouchKitIntegrationTest;
import org.vaadin.touchkit.ui.Toolbar;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;

public class ToolbarTest extends AbstractTouchKitIntegrationTest {
    public ToolbarTest() {
        setDescription("This is Toolbar test");
        Toolbar tbar = new Toolbar();
        final Label label = new Label();
        label.setValue("Clicked: none");

        for (int loop = 0; loop < 10; loop++) {
            Button button = new Button();
            if (loop % 2 == 0) {
				button.setIcon(VaadinIcons.HOME);
            } else {
				button.setIcon(VaadinIcons.FOLDER_O);
            }
            if (loop % 3 == 0) {
                button.setCaption("Diipa");
            }

            final int identifier = loop;
            button.addClickListener(event -> label.setValue("Clicked: " + identifier));
            tbar.addComponent(button);
        }

        addComponent(tbar);
        addComponent(label);
    }
}
