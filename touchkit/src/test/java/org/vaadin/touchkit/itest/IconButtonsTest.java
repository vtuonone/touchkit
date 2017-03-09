package org.vaadin.touchkit.itest;

import org.vaadin.touchkit.AbstractTouchKitIntegrationTest;
import org.vaadin.touchkit.ui.NavigationButton;
import org.vaadin.touchkit.ui.VerticalComponentGroup;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

public class IconButtonsTest extends AbstractTouchKitIntegrationTest implements
        ClickListener {
    private int i;

    public IconButtonsTest() {

        setDescription("This is a button test");

        VerticalComponentGroup componentGroup = new VerticalComponentGroup();

        TextField textField = getTextFieldWithIcon();
        componentGroup.addComponent(textField);

		for (VaadinIcons i : VaadinIcons.values()) {
            NavigationButton button = new NavigationButton(i.name());
            button.setIcon(i);
            componentGroup.addComponent(button);
        }

        addComponent(getTextFieldWithIcon());

        addComponent(componentGroup);

    }

    private TextField getTextFieldWithIcon() {
        TextField textField = new TextField();
		textField.setIcon(VaadinIcons.USER);
        textField.setCaption("User");
        return textField;
    }

    @Override
    public void buttonClick(ClickEvent event) {
        Notification.show("Clicked " + ++i);
    }

}
