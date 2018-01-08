package org.vaadin.touchkit.gwt.client.vcom;


import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ui.textfield.TextFieldConnector;
import com.vaadin.shared.ui.Connect;
import org.vaadin.touchkit.gwt.client.ui.EmailFieldWidget;

@Connect(org.vaadin.touchkit.ui.EmailField.class)
public class EmailFieldConnector extends TextFieldConnector {

    @Override
    protected Widget createWidget() {
        return GWT.create(EmailFieldWidget.class);
    }

    @Override
    public EmailFieldWidget getWidget() {
        return (EmailFieldWidget) super.getWidget();
    }
}
