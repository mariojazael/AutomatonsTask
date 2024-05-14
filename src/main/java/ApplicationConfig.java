import Models.State;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/*
    Class's purpose: Class in charge of the object management and
    creation.

    Description: It provides singletons to the application. It's
    based on IoC (Inversion of Control) container that is capable
    of assuming the whole responsibility on the class's purpose.
 */
@Configuration
public class ApplicationConfig {
    @Bean
    public State q0(){
        return new State("q0", false);
    }

    @Bean
    public State q1(){
        return new State("q1", false);
    }

    @Bean
    public State q2(){
        return new State("q2", true);
    }

    @Bean
    public State q3(){
        return new State("q3", false);
    }

    @Bean
    public State q4(){
        return new State("q4", false);
    }

    @Bean
    public State q5(){
        return new State("q5", true);
    }

    @Bean
    public HashMap<String, State> transformationsMap(){
        return new HashMap<>() {{
            put("q0a", q1());
            put("q0b", q3());
            put("q0c", q3());
            put("q1a", q4());
            put("q1b", q5());
            put("q1c", q2());
            put("q2a", q2());
            put("q2b", q2());
            put("q2c", q2());
            put("q3a", q4());
            put("q3b", q3());
            put("q3c", q3());
            put("q4a", q4());
            put("q4b", q5());
            put("q4c", q3());
            put("q5a", q4());
            put("q5b", q3());
            put("q5c", q3());
        }};
    }
}
