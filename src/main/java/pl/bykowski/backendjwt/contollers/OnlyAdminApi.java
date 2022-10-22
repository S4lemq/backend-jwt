package pl.bykowski.backendjwt.contollers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OnlyAdminApi {

    @GetMapping("/onlyAdmin")
    public String get(){
        return "Hello Admin";
    }
}
