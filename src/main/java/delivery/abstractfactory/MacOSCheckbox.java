package delivery.abstractfactory;

/** Concrete product: MacOS checkbox. */
public class MacOSCheckbox implements Checkbox {

    @Override
    public void paint() {
        System.out.println("Rendering macOS checkbox");
    }
}
