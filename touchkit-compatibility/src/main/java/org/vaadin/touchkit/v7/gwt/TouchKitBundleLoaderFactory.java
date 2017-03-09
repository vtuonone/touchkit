package org.vaadin.touchkit.v7.gwt;

import com.vaadin.v7.client.ui.calendar.CalendarConnector;
import com.vaadin.v7.client.ui.combobox.ComboBoxConnector;
import com.vaadin.v7.client.ui.richtextarea.RichTextAreaConnector;
import com.vaadin.v7.client.ui.table.TableConnector;
import com.vaadin.v7.client.ui.treetable.TreeTableConnector;
import com.vaadin.v7.client.ui.twincolselect.TwinColSelectConnector;

/**
 * TouchKitWidgetMapGenerator enables lazy loading of some heavy widget classes
 * that are rarely used in mobile devices. This way saves bandwidth and improves
 * the loading time.
 * 
 * Components for which the widgets are lazily loaded:
 * <ul>
 * <li>{@link com.vaadin.ui.VerticalLayout}</li>
 * <li>{@link com.vaadin.ui.HorizontalLayout}</li>
 * <li>{@link com.vaadin.ui.GridLayout}</li>
 * <li>{@link com.vaadin.ui.AbsoluteLayout}</li>
 * <li>{@link com.vaadin.ui.HorizontalSplitPanel}</li>
 * <li>{@link com.vaadin.ui.VerticalSplitPanel}</li>
 * <li>{@link com.vaadin.ui.Accordion}</li>
 * <li>{@link com.vaadin.ui.ComboBox}</li>
 * <li>{@link com.vaadin.ui.TabSheet}</li>
 * <li>{@link com.vaadin.ui.MenuBar}</li>
 * <li>{@link com.vaadin.ui.Panel}</li>
 * <li>{@link com.vaadin.ui.Window}</li>
 * <li>{@link com.vaadin.ui.RichTextArea}</li>
 * <li>{@link com.vaadin.ui.TwinColSelect}</li>
 * <li>{@link com.vaadin.ui.CustomLayout}</li>
 * <li>{@link com.vaadin.ui.PopupView}</li>
 * <li>{@link com.vaadin.ui.Calendar}</li>
 * <li>{@link com.vaadin.ui.Table}</li>
 * <li>{@link com.vaadin.ui.TreeTable}</li>
 * </ul>
 */
public class TouchKitBundleLoaderFactory extends org.vaadin.touchkit.gwt.TouchKitBundleLoaderFactory {

    public TouchKitBundleLoaderFactory() {
        add(ComboBoxConnector.class);
        add(RichTextAreaConnector.class);
        add(TwinColSelectConnector.class);
        add(CalendarConnector.class);
        add(TableConnector.class);
        add(TreeTableConnector.class);
    }
}
