package delivery.abstractfactory;

/** Concrete product: MacOS button. */
public class MacOSButton implements Button {

    @Override
    public void paint() {
        System.out.println("Rendering macOS button");
    }
}
