package delivery.factorymethod;

/** Product: the contract every kind of transport fulfils. */
public interface Transport {

    void deliver(String cargo, String destination);
}
