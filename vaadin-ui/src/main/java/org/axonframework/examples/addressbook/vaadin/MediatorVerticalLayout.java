package org.axonframework.examples.addressbook.vaadin;

import com.vaadin.ui.VerticalLayout;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com) 
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
