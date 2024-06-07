package org.ramirezmario;

import org.ramirezmario.Models.State;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class ApplicationConfig {

    @Bean
    public State q0() {
        return new State("q0", false, null);
    }

    @Bean
    public State q1() {
        return new State("q1", true, "Parentesis");
    }

    @Bean
    public State q2() {
        return new State("q2", false, "Error");
    }

    @Bean
    public HashMap<String, State> transformationsMap() {
        HashMap<String, State> map = new HashMap<>();
        map.put("q0(", q1());
        map.put("q0)", q1());
        putAllCharacters(map, q1(), letters(), q2());
        putAllCharacters(map, q2(), letters(), q2());
        return map;
    }

    private void putAllCharacters(HashMap<String, State> map, State currentState, Set<Character> characterSet, State targetState) {
        characterSet.forEach(character -> map.put(currentState.getName() + character, targetState));
    }

    @Bean
    public Set<Character> lowerCaseLetters() {
        return Stream.of('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z')
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Bean
    public Set<Character> upperCaseLetters() {
        return Stream.of('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z')
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Bean
    public Set<Character> letters() {
        return new HashSet<>() {{
            addAll(upperCaseLetters());
            addAll(lowerCaseLetters());
        }};
    }
}

