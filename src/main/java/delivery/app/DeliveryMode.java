package delivery.app;

import delivery.factorymethod.Logistics;
import delivery.factorymethod.RoadLogistics;
import delivery.factorymethod.SeaLogistics;

import java.util.function.Supplier;

/** Supported delivery modes; each one knows which concrete creator to use. */
public enum DeliveryMode {
    ROAD(RoadLogistics::new),
    SEA(SeaLogistics::new);

    private final Supplier<Logistics> logisticsSupplier;

    DeliveryMode(Supplier<Logistics> logisticsSupplier) {
        this.logisticsSupplier = logisticsSupplier;
    }

    public Logistics createLogistics() {
        return logisticsSupplier.get();
    }
}
