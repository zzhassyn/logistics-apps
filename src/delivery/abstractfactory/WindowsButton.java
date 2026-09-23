package delivery.abstractfactory;

/** Concrete product: Windows button. */
public class WindowsButton implements Button {

    @Override
    public void paint() {
        System.out.println("Rendering Windows button");
    }
}
