package org.ramirezmario.Services;

import org.ramirezmario.ApplicationConfig;
import org.ramirezmario.Models.State;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/*
    This class holds the business logic.
 */

@Service
public class ParsingService {
    private static final HashMap<String, State> TRANSFORMATIONS_MAP = new ApplicationConfig().transformationsMap();

    // Returns true as String if the entered string is acceptable for the automaton.
    // Returns false if the given String does not leave the automaton in acceptable state.
    public static String parse(String string){
        final StringBuilder currentStateName = new StringBuilder("q0");
        return new AnnotationConfigApplicationContext(ApplicationConfig.class)
                .getBean(Stream.iterate(0, i -> i + 1)
                        .limit(string.length())
                        .map(i -> currentStateName.replace(0, 2, TRANSFORMATIONS_MAP.get(currentStateName.toString() + string.charAt(i))
                                .getName()))
                        .toList()
                        .get(string.length() - 1)
                        .toString(), State.class)
                .isTerminal();
    }
}
