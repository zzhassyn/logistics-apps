package delivery.app;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

/** Validates a user's text against the constants of a choice enum. */
final class Choices {

    private Choices() {
    }

    static <E extends Enum<E>> Optional<E> parse(Class<E> choiceType, String text) {
        if (text == null) {
            return Optional.empty();
        }
        String normalized = text.trim();
        return Arrays.stream(choiceType.getEnumConstants())
                .filter(choice -> choice.name().equalsIgnoreCase(normalized))
                .findFirst();
    }

    static <E extends Enum<E>> String supportedValues(Class<E> choiceType) {
        return Arrays.stream(choiceType.getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.joining(", "));
    }
}
