package org.ramirezmario.Controllers;

import org.ramirezmario.Services.ParsingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/parsing")
public class ParsingServiceController {

    @GetMapping("/parse")
    @ResponseBody
    public String parseData(@RequestParam String data) {
        return ParsingService.parse(data);
    }

    @GetMapping("/")
    public String index() {
        return "Index"; // Thymeleaf buscará un archivo `index.html` en `src/main/resources/templates`
    }
}

