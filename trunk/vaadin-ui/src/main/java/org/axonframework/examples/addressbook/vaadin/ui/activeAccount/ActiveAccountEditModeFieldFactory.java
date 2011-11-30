package org.axonframework.examples.addressbook.vaadin.ui.activeAccount;

import com.vaadin.data.Item;
import com.vaadin.data.validator.IntegerValidator;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-29
 * Time: 11:09:34 PM
 */
public class ActiveAccountEditModeFieldFactory extends DefaultFieldFactory {

    @Override
    public Field createField(Item item, Object propertyId, Component uiContext) {
        Field f = super.createField(item, propertyId, uiContext);

        if ("accountName".equals(propertyId)) {
            TextField tf = (TextField) f;
            tf.setNullRepresentation("");
            tf.setRequired(true);
            tf.setRequiredError("Please enter an account name");
        } else if ("accountNumber".equals(propertyId)) {
            TextField tf = (TextField) f;
            tf.setNullRepresentation("");
            tf.setRequired(true);
            tf.setRequiredError("Please enter an account number");
            tf.addValidator(new IntegerValidator(
                    "Account number must be a number"));
        }

        return f;
    }
}
