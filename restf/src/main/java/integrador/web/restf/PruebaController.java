package integrador.web.restf;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class PruebaController {

    @GetMapping("/hola")
    public String hola() {
        return "¡Hola desde REST!";
    }
}
