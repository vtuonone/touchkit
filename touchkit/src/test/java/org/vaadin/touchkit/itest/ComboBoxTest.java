package org.vaadin.touchkit.itest;

import org.vaadin.touchkit.AbstractTouchKitIntegrationTest;

import com.vaadin.ui.ComboBox;

public class ComboBoxTest extends AbstractTouchKitIntegrationTest {
    public ComboBoxTest() {
        setDescription("This is a button test");
        
		ComboBox<String> comboBox = new ComboBox<>();
		comboBox.setItems("foo", "bar");
        
        comboBox.setWidth("200px");
        
        addComponent(comboBox);
    }
}
