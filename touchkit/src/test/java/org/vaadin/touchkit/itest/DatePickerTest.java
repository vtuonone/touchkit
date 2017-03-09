package org.vaadin.touchkit.itest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.vaadin.touchkit.AbstractTouchKitIntegrationTest;
import org.vaadin.touchkit.gwt.client.vcom.DatePickerState.Resolution;
import org.vaadin.touchkit.ui.DatePicker;

import com.ibm.icu.util.Calendar;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.RadioButtonGroup;

@SuppressWarnings("serial")
public class DatePickerTest extends AbstractTouchKitIntegrationTest {

    private final DatePicker pickerA = new DatePicker("Pick day");
    private final Label pickerALabel;
    private final static String NULL_VALUE = "null";

    private final static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public DatePickerTest() {
        // setDescription("Testing for DatePicker");

        pickerA.setResolution(Resolution.DAY);
        Calendar cal = Calendar.getInstance();
        cal.set(1982, 10, 28, 9, 17);
        pickerA.setValue(cal.getTime());

        createResolutionOptions();

		pickerALabel = new Label(pickerA.getValue().toString());
		pickerALabel.setCaption("Value sent by client");
        addComponent(pickerA);
		pickerA.addValueChangeListener(event -> {
			Date value = event.getValue();
                pickerALabel.setValue(value != null ? value.toString() : "null");
            }
		);

        addComponent(pickerALabel);

        HorizontalLayout optionsLayout = new HorizontalLayout();
        optionsLayout.setCaption("Options");
        addComponent(optionsLayout);

        final CheckBox useNative = new CheckBox("Use native");
        useNative.setId("usenative");

        useNative.setValue(pickerA.isUseNative());
        optionsLayout.addComponent(useNative);
		useNative.addValueChangeListener(event -> pickerA.setUseNative(event.getValue()));

        addComponent(new Button("Toggle enabled state", (ClickListener) event -> pickerA.setEnabled(!pickerA.isEnabled())));

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setWidth("100%");
        buttonLayout.setCaption("Set date value");
        addComponent(buttonLayout);
        buttonLayout.addComponent(new Button(NULL_VALUE, dateButtonListener));
        buttonLayout.addComponent(new Button("1982-10-25", dateButtonListener));
        buttonLayout.addComponent(new Button("2011-02-11", dateButtonListener));
        buttonLayout.addComponent(new Button("2013-01-31", dateButtonListener));
        buttonLayout.addComponent(new Button("2015-06-23", dateButtonListener));
        buttonLayout.addComponent(new Button("2078-12-25", dateButtonListener));

		RadioButtonGroup<String> mingroup = new RadioButtonGroup<>("Min value");
		mingroup.setItems(NULL_VALUE, "1982-01-01", "2011-01-01", "2011-02-01", "2011-02-10", "2011-02-12");
		mingroup.setValue(NULL_VALUE);
        mingroup.addValueChangeListener(event -> {
			String val = event.getValue();
		    if (val.equals(NULL_VALUE)) {
		        System.out.println("Null min value");
		        pickerA.setMin(null);
		    } else {
		        try {
		            System.out.println("Set min value to: " + val);
		            pickerA.setMin(df.parse(val));
		        } catch (ParseException e) {
		            e.printStackTrace();
		        }
		    }
		});

		RadioButtonGroup<String> maxgroup = new RadioButtonGroup<>("Max value");
        maxgroup.setValue(NULL_VALUE);
		maxgroup.setItems(NULL_VALUE, "1982-01-01", "2011-02-01", "2011-02-12", "2011-02-27", "2011-03-01");
        maxgroup.addValueChangeListener(event -> {
			String val = event.getValue();
		    if (val.equals(NULL_VALUE)) {
		        System.out.println("Null max value");
		        pickerA.setMax(null);
		    } else {
		        try {
		            System.out.println("Set max value to: " + val);
		            pickerA.setMax(df.parse(val));
		        } catch (ParseException e) {
		            e.printStackTrace();
		        }
		    }
		});

        HorizontalLayout minmax = new HorizontalLayout();
        minmax.setSpacing(true);
        minmax.addComponents(mingroup, maxgroup);

        addComponent(minmax);

		final RadioButtonGroup<Locale> localeGroup = new RadioButtonGroup<>("Change the locale");
        localeGroup.setId("locale");
        localeGroup.setEnabled(!useNative.getValue());
		Locale defaultLocale = Locale.getDefault();
		Locale finishLocale = new Locale("fi", "FI");
		localeGroup.setItems(defaultLocale, finishLocale, Locale.US, Locale.UK);
		localeGroup.setItemCaptionGenerator(locale -> {
			if (locale.equals(defaultLocale)) {
				return "Default";
			} else if (locale.equals(finishLocale)) {
				return "Finish";
			} else if (locale.equals(Locale.US)) {
				return "US";
			} else if (locale.equals(Locale.UK)) {
				return "UK";
			} else {
				return locale.toString();
			}
		});
        localeGroup.addValueChangeListener(event -> {
			Locale locale = event.getValue();
		    pickerA.setLocale(locale);
		});
        useNative.addValueChangeListener(valueChangeEvent -> localeGroup.setEnabled(!useNative.getValue()));

		localeGroup.setValue(defaultLocale);

        addComponent(localeGroup);
    }

    private final Button.ClickListener dateButtonListener = event -> {
	    try {
	        String caption = event.getButton().getCaption();
	        if (caption.equals(NULL_VALUE)) {
	            pickerA.setValue(null);
	        } else {
	            pickerA.setValue(df.parse(caption));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	};

    private void createResolutionOptions() {
        HorizontalLayout buttonLayout = new HorizontalLayout();

		NativeSelect<Resolution> resolution = new NativeSelect<>("Resolution");
        resolution.setEmptySelectionAllowed(false);
      
        buttonLayout.addComponent(resolution);
        resolution.setItems(Resolution.values());
        resolution.setValue(pickerA.getResolution());

		resolution.addValueChangeListener(event -> {
			Resolution res = event.getValue();
                System.out.println("Resolution: " + res);
                pickerA.setResolution(res);
		});

        addComponent(buttonLayout);
    }
}
