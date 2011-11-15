package org.axonframework.sample.app.query;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-04
 * Time: 11:28:37 AM
 */
@Entity
public class LedgerEntry {

    public LedgerEntry(BigDecimal amount, String action) {
        this.amount = amount;
        this.action = action;
    }

    @Id
    @GeneratedValue
    private Long db_identifier;

    @Basic
    private BigDecimal amount;

    @Basic
    public String action;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
