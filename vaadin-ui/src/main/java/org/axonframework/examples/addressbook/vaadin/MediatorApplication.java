package org.axonframework.examples.addressbook.vaadin;

import com.vaadin.Application;

import java.util.Collection;
import java.util.HashSet;

/**
 * User: Kenan Sevindik (ksevindik@gmail.com)
 * Date: Feb 11, 2011
 * Time: 6:12:51 PM
 */
public abstract class MediatorApplication extends Application {
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
