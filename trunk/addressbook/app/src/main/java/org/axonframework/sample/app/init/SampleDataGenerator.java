package org.axonframework.sample.app.init;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.domain.AggregateIdentifier;
import org.axonframework.sample.app.api.fohjin.command.CreateClientCommand;
import org.axonframework.sample.app.api.fohjin.command.OpenNewAccountForClientCommand;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com)
 */
public class SampleDataGenerator implements ApplicationListener {

    private CommandBus commandBus;
    private AtomicBoolean initialized = new AtomicBoolean();

    public SampleDataGenerator(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    public void onApplicationEvent(ApplicationEvent event) {
        if (!initialized.get() && event instanceof ContextRefreshedEvent) {
            initializeData();
        }
    }

    public void initializeData() {
        if (initialized.compareAndSet(false, true)) {
            commandBus.dispatch(new CreateClientCommand("Sample Client 1"));
            commandBus.dispatch(new CreateClientCommand("Sample Client 2"));

            //BKONU: How to create this kind of sample data?
            AggregateIdentifier clientId = null;
            OpenNewAccountForClientCommand accountForClientCommand = new OpenNewAccountForClientCommand
                    (clientId, "account name");
        }
    }
}
