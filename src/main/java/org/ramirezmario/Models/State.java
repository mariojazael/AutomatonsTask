package org.ramirezmario.Models;

public class State {
    private String name;
    private boolean isTerminal;

    public State(String name, boolean isTerminal){
        this.name = name;
        this.isTerminal = isTerminal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String isTerminal() {
        return String.valueOf(isTerminal);
    }

    public void setTerminal(boolean terminal) {
        isTerminal = terminal;
    }
}
