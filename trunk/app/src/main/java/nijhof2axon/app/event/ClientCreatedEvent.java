package nijhof2axon.app.event;

import nijhof2axon.app.domain.Address;
import org.axonframework.domain.DomainEvent;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-09-17
 * Time: 3:19:23 PM
 */
public class ClientCreatedEvent extends DomainEvent {

    private String name;
    private Address address;
    private String phoneNumber;

    public ClientCreatedEvent(String name, Address address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getClientIdentifier() {
        return getAggregateIdentifier().asString();
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
