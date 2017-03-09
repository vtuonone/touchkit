package org.vaadin.touchkit.itest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.vaadin.touchkit.AbstractTouchKitIntegrationTest;
import org.vaadin.touchkit.ui.NavigationManager;
import org.vaadin.touchkit.ui.NavigationView;
import org.vaadin.touchkit.ui.VerticalComponentGroup;

import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

public class NavigationViewBarTest extends AbstractTouchKitIntegrationTest {

    Random r = new Random(0);

    public NavigationViewBarTest() {
        setDescription("NavigationView and -Bar test");
        addComponent(makeNavigationManager());
    }

    NavigationManager makeNavigationManager() {
        final NavigationManager navman = new NavigationManager();

        final NavigationView one = createView("One", "one", true);
        final NavigationView two = createView("Two", "two", true);
        final NavigationView three = createView("Three", "three", true);
        final NavigationView four = createView("Four", "four", false);

        navman.setCurrentComponent(one);
        navman.setNextComponent(two);

        addNextButton(navman, one, two);
        addNextButton(navman, two, three);
        addNextButton(navman, three, four);

        navman.addNavigationListener(event -> {
		    if (navman.getCurrentComponent() == one) {
		        navman.setNextComponent(two);
		    } else if (navman.getCurrentComponent() == two) {
		        navman.setNextComponent(three);
		    } else if (navman.getCurrentComponent() == three) {
		        navman.setNextComponent(four);
		    }
		});
        return navman;
    }

    private static void addNextButton(final NavigationManager navman,
            final NavigationView view, final NavigationView next) {
        ((Button) view.getRightComponent())
                .addClickListener(event -> navman.navigateTo(next));
    }

    NavigationView createView(String caption, String debugId, boolean hasNext) {
        final CssLayout layout = new CssLayout();

		NativeSelect<String> nativeSelect = new NativeSelect<>();
		List<String> items = new ArrayList<>();
		items.add("FOO");
        for (int i = 0; i < 200; i++) {
			items.add("BAR " + i);
        }
		items.add("CAaaaaaaaaaaaaaaaaa Rsdfs sfsdfsdfsdsd fdsf adsfdsf sdaf j sdlfkjs ld   adsfsd f");
		nativeSelect.setItems(items);

		nativeSelect.addValueChangeListener(event -> Notification.show("Value:" + event.getValue()));

        
        VerticalComponentGroup verticalComponentGroup = new VerticalComponentGroup("Textifedf");
        
        verticalComponentGroup.addComponent(new TextField("Böö"));
        
        layout.addComponent(verticalComponentGroup);
        
        layout.addComponent(nativeSelect);

        layout.setId(debugId);
        int max = (r.nextInt(100));
        for (int i = 0; i < max; i++) {
            Label l = new Label("Foo " + i);
            layout.addComponent(l);
        }

        NavigationView navView = new NavigationView();
        if (hasNext) {
            navView.setRightComponent(new Button("Next"));
        }
        navView.setCaption(caption);
        navView.setContent(layout);
        navView.getLeftComponent().setCaption("Back");
        return navView;
    }
}
