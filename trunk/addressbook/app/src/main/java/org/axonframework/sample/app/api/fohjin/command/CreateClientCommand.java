package org.axonframework.sample.app.api.fohjin.command;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com)
 */
public class CreateClientCommand {

    public CreateClientCommand(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }
}
