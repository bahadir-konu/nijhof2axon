package org.axonframework.examples.addressbook.vaadin;

import com.vaadin.ui.VerticalLayout;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-25
 * Time: 12:09:06 PM
 */
public class MediatorVerticalLayout extends VerticalLayout {

    @Override
    public Nijhof2AxonApplication getApplication() {
        return (Nijhof2AxonApplication) super.getApplication();
    }

    public void fire(MediatorEvent event) {
        getApplication().getMainWindow().fireEvent(event);
    }


}
