package nijhof2axon.app.query;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com) 
 */
@Entity
public class ClientEntry {

    @Id
    @GeneratedValue
    private Long db_identifier;

    @Basic
    private String identifier;

    @NotNull
    @Size(min = 3)
    @Basic
    private String name;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

