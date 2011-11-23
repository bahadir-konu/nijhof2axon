package org.axonframework.examples.addressbook.vaadin;

import com.vaadin.ui.Window;

import java.util.Collection;
import java.util.HashSet;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-23
 * Time: 5:54:22 PM
 */
public class MainWindow extends Window {

    private Collection<MediatorListener> collaborators = new HashSet<MediatorListener>();


    public void addCollaborator(MediatorListener collaborator) {
        collaborators.add(collaborator);
    }

    public void removeCollaborator(MediatorListener collaborator) {
        collaborators.remove(collaborator);
    }

    public void fireEvent(MediatorEvent event) {
        for (MediatorListener ml : collaborators) {
            ml.handleEvent(event);
        }
    }

}
