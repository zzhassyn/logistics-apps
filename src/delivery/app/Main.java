package delivery.app;

import java.util.Scanner;

import delivery.abstractfactory.GUIFactory;
import delivery.abstractfactory.MacOSFactory;
import delivery.abstractfactory.WindowsFactory;
import delivery.factorymethod.Logistics;
import delivery.factorymethod.RoadLogistics;
import delivery.factorymethod.SeaLogistics;

public final class Main {

    private static final String CARGO = "laboratory equipment";
    private static final String DESTINATION = "Aktau warehouse";

    private Main() {
    }

    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println("Error: expected 2 arguments. Usage: Main <ROAD|SEA> <WINDOWS|MACOS>");
            System.exit(1);
        }

        DeliveryApplication application;
        try {
            Logistics logistics = selectLogistics(args[0]);
            GUIFactory guiFactory = selectGuiFactory(args[1]);
            application = new DeliveryApplication(guiFactory, logistics);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
            return;
        }

        System.out.println("Delivery mode: " + normalize(args[0]));
        System.out.println("UI platform: " + normalize(args[1]));
        application.run(CARGO, DESTINATION);
    }

    private static Logistics selectLogistics(String mode) {
        return switch (normalize(mode)) {
            case "ROAD" -> new RoadLogistics();
            case "SEA" -> new SeaLogistics();
            default -> throw new IllegalArgumentException(
                    "unsupported delivery mode '" + mode + "'. Supported values: ROAD, SEA, AIR");
        };
    }

    private static GUIFactory selectGuiFactory(String platform) {
        return switch (normalize(platform)) {
            case "WINDOWS" -> new WindowsFactory();
            case "MACOS" -> new MacOSFactory();
            default -> throw new IllegalArgumentException(
                    "unsupported UI platform '" + platform + "'. Supported values: WINDOWS, MACOS");
        };
    }

    private static String normalize(String text) {
        return text.trim().toUpperCase();
    }
}
