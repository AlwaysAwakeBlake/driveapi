package drive.fitness.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class HomeController {

    @RequestMapping(value = "/test", method= RequestMethod.GET)
    public String index() {
        return "Greetings from Spring Boot!";
    }

}