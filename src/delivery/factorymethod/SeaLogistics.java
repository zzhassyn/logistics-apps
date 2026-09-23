package delivery.factorymethod;

/** Concrete creator: creates sea transport. */
public class SeaLogistics extends Logistics {

    @Override
    protected Transport createTransport() {
        return new Ship();
    }
}
