package delivery.app;

import delivery.abstractfactory.Button;
import delivery.abstractfactory.Checkbox;
import delivery.abstractfactory.GUIFactory;
import delivery.factorymethod.Logistics;

/**
 * Client of both patterns. It only knows the abstractions
 * (GUIFactory, Button, Checkbox, Logistics), never concrete classes.
 */
public class DeliveryApplication {

    private final Button button;
    private final Checkbox checkbox;
    private final Logistics logistics;

    public DeliveryApplication(GUIFactory guiFactory, Logistics logistics) {
        this.button = guiFactory.createButton();
        this.checkbox = guiFactory.createCheckbox();
        this.logistics = logistics;
    }

    public void run(String cargo, String destination) {
        renderInterface();
        deliver(cargo, destination);
    }

    private void renderInterface() {
        button.paint();
        checkbox.paint();
    }

    private void deliver(String cargo, String destination) {
        logistics.planDelivery(cargo, destination);
    }
}
