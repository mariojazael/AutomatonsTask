import Models.State;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/*
    This class holds the business logic.
 */

@Service
public class ParsingService {
    private static final HashMap<String, State> TRANSFORMATIONS_MAP = new ApplicationConfig().transformationsMap();
    private static final AtomicInteger INDEX = new AtomicInteger(0);

    // Returns true as String if the entered string is acceptable for the automaton.
    // Returns false if the given String does not leave the automaton in acceptable state.
    public static String parse(String string){
        while(INDEX.get() < string.length()){
            if(INDEX.get() == 0) string = TRANSFORMATIONS_MAP.get("q0" + string.charAt(INDEX.getAndIncrement()))
                        .getName()
                        .concat(string);
            else if(INDEX.get() + 2 < string.length()) string = TRANSFORMATIONS_MAP.get(string.substring(0, 2) + string.charAt(INDEX.getAndIncrement() + 2))
                        .getName()
                        .concat(string.substring(2));
            else break;
        }
        return new AnnotationConfigApplicationContext(ApplicationConfig.class)
                .getBean(string.substring(0, 2), State.class)
                .isTerminal();
    }

}
