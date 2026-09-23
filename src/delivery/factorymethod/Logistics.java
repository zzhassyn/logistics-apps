package delivery.factorymethod;

public abstract class Logistics {

    protected abstract Transport createTransport();

    public final void planDelivery(String cargo, String destination) {
        Transport transport = createTransport();
        transport.deliver(cargo, destination);
    }
}
