package org.ramirezmario.Controllers;

import org.ramirezmario.Services.ParsingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/parsing")
public class ParsingServiceController {

    private final ParsingService parsingService;

    @Autowired
    public ParsingServiceController(ParsingService parsingService) {
        this.parsingService = parsingService;
    }

    @PostMapping("/parse")
    @ResponseBody
    public ResponseEntity<Map<String, String>> parse(@RequestBody Map<String, String> request) throws InterruptedException {
        Thread.sleep(1000);
        String content = request.get("content");
        Map<String, String> response = new HashMap<>();
        response.put("result", parsingService.parse(content));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/tokenizer")
    public String fileReader() {
        return "tokenizer";
    }
}


