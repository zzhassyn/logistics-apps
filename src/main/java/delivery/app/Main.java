package delivery.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

/**
 * Startup: reads and validates the two independent choices, selects the
 * concrete creator and factory, and hands them to DeliveryApplication.
 *
 * Usage: Main [ROAD|SEA WINDOWS|MACOS]   (no arguments = interactive mode)
 */
public final class Main {

    private static final String CARGO = "laboratory equipment";
    private static final String DESTINATION = "Aktau warehouse";
    private static final String MODE_LABEL = "delivery mode";
    private static final String PLATFORM_LABEL = "UI platform";

    private record Selection(DeliveryMode mode, UiPlatform platform) {
    }

    private Main() {
    }

    public static void main(String[] args) {
        Optional<Selection> selection = readSelection(args);
        if (selection.isEmpty()) {
            System.exit(1);
        }
        startApplication(selection.get());
    }

    private static Optional<Selection> readSelection(String[] args) {
        if (args.length == 0) {
            return readInteractively();
        }
        if (args.length > 2) {
            System.err.println("Error: too many arguments. Usage: Main <ROAD|SEA> <WINDOWS|MACOS>");
            return Optional.empty();
        }
        return readFromArguments(args);
    }

    private static Optional<Selection> readFromArguments(String[] args) {
        Optional<DeliveryMode> mode = validate(MODE_LABEL, args[0], DeliveryMode.class);
        String rawPlatform = args.length > 1 ? args[1] : null;
        Optional<UiPlatform> platform = validate(PLATFORM_LABEL, rawPlatform, UiPlatform.class);
        if (mode.isEmpty() || platform.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(new Selection(mode.get(), platform.get()));
    }

    private static Optional<Selection> readInteractively() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            Optional<DeliveryMode> mode = askUntilValid(in, MODE_LABEL, DeliveryMode.class);
            if (mode.isEmpty()) {
                return Optional.empty();
            }
            Optional<UiPlatform> platform = askUntilValid(in, PLATFORM_LABEL, UiPlatform.class);
            return platform.map(chosen -> new Selection(mode.get(), chosen));
        } catch (IOException e) {
            System.err.println("Error: cannot read input: " + e.getMessage());
            return Optional.empty();
        }
    }

    private static <E extends Enum<E>> Optional<E> askUntilValid(
            BufferedReader in, String label, Class<E> type) throws IOException {
        while (true) {
            System.out.print("Enter " + label + " (" + Choices.supportedValues(type) + "): ");
            String line = in.readLine();
            if (line == null) {
                System.err.println("Error: input ended before a valid " + label + " was given.");
                return Optional.empty();
            }
            Optional<E> choice = validate(label, line, type);
            if (choice.isPresent()) {
                return choice;
            }
        }
    }

    private static <E extends Enum<E>> Optional<E> validate(String label, String raw, Class<E> type) {
        if (raw == null || raw.isBlank()) {
            System.err.println("Error: missing " + label + ". Supported values: "
                    + Choices.supportedValues(type));
            return Optional.empty();
        }
        Optional<E> choice = Choices.parse(type, raw);
        if (choice.isEmpty()) {
            System.err.println("Error: unsupported " + label + " '" + raw.trim()
                    + "'. Supported values: " + Choices.supportedValues(type));
        }
        return choice;
    }

    private static void startApplication(Selection selection) {
        System.out.println("Delivery mode: " + selection.mode());
        System.out.println("UI platform: " + selection.platform());
        DeliveryApplication application = new DeliveryApplication(
                selection.platform().createGuiFactory(),
                selection.mode().createLogistics());
        application.run(CARGO, DESTINATION);
    }
}
