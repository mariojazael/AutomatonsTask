package org.ramirezmario.Models;

public class State {
    private String name;
    private boolean isTerminal;
    private String token;

    public State(String name, boolean isTerminal, String token){
        this.name = name;
        this.isTerminal = isTerminal;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTerminal() {
        return isTerminal;
    }

    public void setTerminal(boolean terminal) {
        isTerminal = terminal;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
