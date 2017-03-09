package org.vaadin.touchkit.itest;

import org.vaadin.touchkit.AbstractTouchKitIntegrationTest;
import org.vaadin.touchkit.ui.Switch;
import org.vaadin.touchkit.ui.VerticalComponentGroup;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;

public class SwitchTest extends AbstractTouchKitIntegrationTest {

    public SwitchTest() {
        setDescription("This is Switch test");

        boolean initialState = true;

        // Label to display the value of the Switch.
        final Label statusLabel = new Label(Boolean.toString(initialState));
        statusLabel.setCaption("Current Switch value:");
        statusLabel.setId("statusLabel");

        // Normal Switch that can be toggled.
        final Switch switchComponent = new Switch("Switch", initialState);
        switchComponent.setId("switchComponent");
		switchComponent
				.addValueChangeListener(event -> statusLabel.setValue(event.getValue().toString()));

        // Disabled Switch that cannot be interacted with.
        final Switch disabledSwitch = new Switch("Disabled Switch");
        disabledSwitch.setEnabled(false);

		Button toggleButton = new Button("Toggle Switch value",
				event -> switchComponent.setValue(!switchComponent.getValue()));
        toggleButton.setId("toggleButton");

        addComponent(switchComponent);
        addComponent(disabledSwitch);
        addComponent(statusLabel);
        addComponent(toggleButton);
        addComponent(createVerticalComponentGroup());
    }

    private VerticalComponentGroup createVerticalComponentGroup() {
        VerticalComponentGroup vcg = new VerticalComponentGroup();
        vcg.addComponent(new Switch("Switch", false));
        Switch disabledSwitch = new Switch("Disabled", true);
        disabledSwitch.setEnabled(false);
        vcg.addComponent(disabledSwitch);
        return vcg;
    }

}
