package delivery.app;

import delivery.abstractfactory.GUIFactory;
import delivery.abstractfactory.MacOSFactory;
import delivery.abstractfactory.WindowsFactory;

import java.util.function.Supplier;

/** Supported UI platforms; each one knows which concrete factory to use. */
public enum UiPlatform {
    WINDOWS(WindowsFactory::new),
    MACOS(MacOSFactory::new);

    private final Supplier<GUIFactory> guiFactorySupplier;

    UiPlatform(Supplier<GUIFactory> guiFactorySupplier) {
        this.guiFactorySupplier = guiFactorySupplier;
    }

    public GUIFactory createGuiFactory() {
        return guiFactorySupplier.get();
    }
}
