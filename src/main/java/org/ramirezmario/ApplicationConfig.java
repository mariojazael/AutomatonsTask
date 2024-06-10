package org.ramirezmario;

import org.ramirezmario.Models.State;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class ApplicationConfig {

    @Bean
    public State q0() {
        return new State("q0", false, "Errores");
    }

    @Bean
    public State q1() {
        return new State("q1", true, "Parentesis");
    }

    @Bean
    public State q2() {
        return new State("q2", false, "Errores");
    }

    @Bean
    public State q3() {
        return new State("q3", false, "Unsolved");
    }

    @Bean
    public State q4() {
        return new State("q4", true, "Llaves");
    }

    @Bean State q5() { return new State ("q5", true, "Operadores aritmeticos"); }

    @Bean State q6() { return new State ("q6", true, "Comentarios de linea"); }

    @Bean State q7() { return new State ("q7", false, "Comentarios/*"); }

    @Bean State q8() { return new State ("q8", false, "Comentarios/**");}

    @Bean State q9() { return new State ("q9", true, "Comentarios");}

    @Bean State q10() { return new State ("q10", false, "Cadenas de caracteres*");}

    @Bean State q11() { return new State ("q11", true, "Cadenas de caracteres");}

    @Bean State q12() { return new State ("q12", true, "Operadores aritmeticos"); }

    @Bean State q13() { return new State ("q13", true, "Numeros enteros"); }

    @Bean State q14() { return new State ("q14", true, "Operadores de decremento"); }

    @Bean State q15() { return new State ("q15", false, "Numeros decimales."); }

    @Bean State q16() { return new State ("q16", true, "Numeros decimales"); }

    @Bean State q17() { return new State ("q17", true, "Operadores de asignacion"); }

    @Bean State q18() { return new State ("q18", true, "Operadores relacionales"); }

    @Bean State q19() { return new State ("q19", true, "Operadores aritmeticos"); }

    @Bean State q20() { return new State ("q20", true, "Operadores de incremento"); }

    @Bean State q21() { return new State ("q21", true, "Operadores aritmeticos"); }

    @Bean State q22() { return new State ("q22", true, "Operadores aritmeticos"); }

    @Bean State q23() { return new State ("q23", false, "Operadores logicos&"); }

    @Bean State q24() { return new State ("q24", true, "Operadores logicos"); }

    @Bean State q25() { return new State ("q25", false, "Operadores logicos|"); }

    @Bean
    public HashMap<String, State> transformationsMap() {
        HashMap<String, State> map = new HashMap<>();
        final char o = '"';

        addEntries(map, q1(), "q0(", "q0)", "q0[", "q0]");
        addEntries(map, q4(), "q0{", "q0}");
        map.put("q0/", q5());
        map.put("q0" + o, q10());
        map.put("q0-", q12());
        map.put("q0=", q17());
        map.put("q0+", q19());
        map.put("q0*", q21());
        map.put("q0%", q22());
        map.put("q0&", q23());
        map.put("q0!", q24());
        map.put("q0<", q18());
        map.put("q0>", q18());
        map.put("q0|", q25());
        putAllCharacters(map, q0(), lettersSet(), q3());
        putAllCharacters(map, q0(), numbersSet(), q13());

        putAllCharacters(map, q1(), allCharactersSet(), q2());

        putAllCharacters(map, q2(), allCharactersSet(), q2());

        putAllCharacters(map, q3(), lettersSet(), q3());
        putAllCharacters(map, q3(), ignoreElements(Set.of('_'), specialCharacters()), q2());
        map.put("q3_", q3());

        putAllCharacters(map, q4(), allCharactersSet(), q2());

        putAllCharacters(map, q5(), ignoreElements(Set.of('*', '/'), allCharactersSet()), q2());
        map.put("q5/", q6());
        map.put("q5*", q7());

        putAllCharacters(map, q6(), ignoreElements(Set.of('/'), allCharactersSet()), q6());
        map.put("q6/", q2());

        putAllCharacters(map, q7(), ignoreElements(Set.of('*'), allCharactersSet()), q7());
        map.put("q7*", q8());

        putAllCharacters(map, q8(), ignoreElements(Set.of('/'), allCharactersSet()), q8());
        map.put("q8/", q9());

        putAllCharacters(map, q9(), allCharactersSet(), q2());

        putAllCharacters(map, q10(), ignoreElements(Set.of(o), allCharactersSet()), q10());
        map.put("q10" + o, q11());

        putAllCharacters(map, q11(), allCharactersSet(), q2());

        putAllCharacters(map, q12(), lettersSet(), q2());
        putAllCharacters(map, q12(), numbersSet(), q13());
        putAllCharacters(map, q12(), ignoreElements(Set.of('-'), specialCharacters()), q2());
        map.put("q12-", q14());

        putAllCharacters(map, q13(), numbersSet(), q13());
        putAllCharacters(map, q13(), ignoreElements(Set.of('.'), specialCharacters()), q2());
        map.put("q13.", q15());
        putAllCharacters(map, q13(), lettersSet(), q2());

        putAllCharacters(map, q14(), allCharactersSet(), q2());

        putAllCharacters(map, q15(), numbersSet(), q16());
        putAllCharacters(map, q15(), lettersSet(), q2());
        putAllCharacters(map, q15(), specialCharacters(), q2());

        putAllCharacters(map, q16(), numbersSet(), q16());
        putAllCharacters(map, q16(), lettersSet(), q2());
        putAllCharacters(map, q16(), specialCharacters(), q2());

        putAllCharacters(map, q17(), ignoreElements(Set.of('='), allCharactersSet()), q2());
        map.put("q17=", q18());

        putAllCharacters(map, q18(), ignoreElements(Set.of('='), allCharactersSet()), q2());
        map.put("q18=", q18());

        putAllCharacters(map, q19(), ignoreElements(Set.of('+'), allCharactersSet()), q2());
        map.put("q19+", q20());

        putAllCharacters(map, q20(), allCharactersSet(), q2());

        putAllCharacters(map, q21(), allCharactersSet(), q2());

        putAllCharacters(map, q22(), allCharactersSet(), q2());

        putAllCharacters(map, q23(), ignoreElements(Set.of('&'), allCharactersSet()), q2());
        map.put("q23&", q24());

        putAllCharacters(map, q24(), ignoreElements(Set.of('='), allCharactersSet()), q2());
        map.put("q24=", q18());

        putAllCharacters(map, q25(), ignoreElements(Set.of('|'), allCharactersSet()), q2());
        map.put("q25|", q24());

        return map;
    }

    private void putAllCharacters(Map<String, State> map, State currentState, Set<Character> characterSet, State targetState) {
        characterSet.forEach(character -> map.put(currentState.getName() + character, targetState));
    }

    private Set<Character> ignoreElements(Set<Character> elements, Set<Character> characterSet){
        return new HashSet<>() {{
            addAll(characterSet);
            elements.forEach(this::remove);
        }};
    }

    private static void addEntries(Map<String, State> map, State state, String... keys) {
        for (String key : keys) {
            map.put(key, state);
        }
    }

    @Bean
    public Set<Character> numbersSet(){
        return Stream.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Bean
    public Set<Character> lowerCaseLettersSet() {
        return Stream.of('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z')
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Bean
    public Set<Character> upperCaseLettersSet() {
        return Stream.of('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z')
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Bean
    public Set<Character> lettersSet() {
        return new HashSet<>() {{
            addAll(upperCaseLettersSet());
            addAll(lowerCaseLettersSet());
        }};
    }

    @Bean
    public Set<Character> specialCharacters(){
        return Stream.of('<', '>', '.', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', ',', ':', ';', '|', '=', '-', '+', '/', '}', '{', '[', ']', '"', '?', '~', '`', '_')
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Bean
    public Set<Character> allCharactersSet(){
        return new HashSet<>() {{
            addAll(specialCharacters());
            addAll(lettersSet());
            addAll(numbersSet());
        }};
    }
    @Bean
    public Set<String> fixedWords(){
        return Stream.of("if", "main", "else", "switch", "case", "default", "for", "do", "while", "break", "int", "String", "double", "char", "print")
                .collect(Collectors.toCollection(HashSet::new));
    }
}

