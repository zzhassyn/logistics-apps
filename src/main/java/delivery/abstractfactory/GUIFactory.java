package delivery.abstractfactory;

/** Abstract factory: creates a matching family of UI components. */
public interface GUIFactory {

    Button createButton();

    Checkbox createCheckbox();
}
