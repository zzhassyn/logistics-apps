package delivery.factorymethod;

/**
 * Creator: declares the factory method and owns the shared delivery workflow.
 * Subclasses decide which Transport is created.
 */
public abstract class Logistics {

    /** Factory method: overridden by concrete creators. */
    protected abstract Transport createTransport();

    /** Shared workflow: identical for every kind of logistics. */
    public final void planDelivery(String cargo, String destination) {
        Transport transport = createTransport();
        transport.deliver(cargo, destination);
    }
}
