package org.vaadin.touchkit.ui;

import com.vaadin.ui.TextField;

/**
 *
 * @author mstahv
 */
public class EmailField extends TextField {

    public EmailField() {
    }

    public EmailField(String caption) {
        super(caption);
    }

    public EmailField(String caption, String value) {
        super(caption, value);
    }

    public EmailField(ValueChangeListener<String> valueChangeListener) {
        super(valueChangeListener);
    }

    public EmailField(String caption, ValueChangeListener<String> valueChangeListener) {
        super(caption, valueChangeListener);
    }

    public EmailField(String caption, String value, ValueChangeListener<String> valueChangeListener) {
        super(caption, value, valueChangeListener);
    }
    
    
    
}
