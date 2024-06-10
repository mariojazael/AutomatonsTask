package org.ramirezmario.Services;

import org.ramirezmario.Models.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

@Service
public class ParsingService {

    private final HashMap<String, State> transformationsMap;
    private final State initialState;
    private final Set<String> fixedWords;

    @Autowired
    public ParsingService(ApplicationContext applicationContext) {
        this.transformationsMap = applicationContext.getBean("transformationsMap", HashMap.class);
        this.initialState = applicationContext.getBean("q0", State.class);
        this.fixedWords = applicationContext.getBean("fixedWords", Set.class);
    }

    public String parse(String string) {
        final HashMap<String, AtomicInteger> resultsMap = initResultsMap();
        final AtomicReference<State> currentState = new AtomicReference<>(initialState);
        Arrays.stream(prepareData(string)).sequential()
                .forEach(token -> {
                    Stream.of(token.split(""))
                            .forEach(character -> currentState.set(transformationsMap.get(currentState.get().getName() + character)));
                    if (currentState.get().isTerminal()) resultsMap.get(currentState.get().getToken()).getAndIncrement();
                    else if(currentState.get().getToken().equals("Unsolved")) resultsMap.get(solveToken(token)).getAndIncrement();
                    else resultsMap.get("Errores").getAndIncrement();
                    currentState.set(initialState);
                });
        return resultsMap.toString();
    }

    private HashMap<String, AtomicInteger> initResultsMap() {
        return new HashMap<>() {{
            put("Parentesis", new AtomicInteger(0));
            put("Errores", new AtomicInteger(0));
            put("Palabras reservadas", new AtomicInteger(0));
            put("Identificadores", new AtomicInteger(0));
            put("Llaves", new AtomicInteger(0));
            put("Operadores aritmeticos", new AtomicInteger(0));
            put("Comentarios", new AtomicInteger(0));
            put("Comentarios de linea", new AtomicInteger(0));
            put("Cadenas de caracteres", new AtomicInteger(0));
            put("Operadores de decremento", new AtomicInteger(0));
            put("Numeros enteros", new AtomicInteger(0));
            put("Numeros decimales", new AtomicInteger(0));
            put("Operadores de asignacion", new AtomicInteger(0));
            put("Operadores relacionales", new AtomicInteger(0));
            put("Operadores de incremento", new AtomicInteger(0));
            put("Operadores logicos", new AtomicInteger(0));
        }};
    }

    private String[] prepareData(String data){
        List<String> words = new ArrayList<>();
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            if (c == ' ' || c == '\t' || c == '\n') {
                if (!word.isEmpty()) {
                    words.add(word.toString());
                    word.setLength(0);
                }
            } else word.append(c);

        }
        if (!word.isEmpty()) words.add(word.toString());
        return words.toArray(new String[0]);
    }

    private String solveToken(String token){
        return fixedWords.contains(token) ? "Palabras reservadas" : "Identificadores";
    }
}

