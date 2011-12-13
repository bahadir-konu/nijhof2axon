package nijhof2axon.ui;

import org.axonframework.examples.addressbook.vaadin.UIEvent;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com) 
 */
public interface MediatorListener {
    void handleEvent(UIEvent event);
}
