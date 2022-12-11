package org.example.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/entity")
    public List<Entity> getIndex() {
        return List.of(new Entity("Hello", "World"), new Entity("Foo", "Bar"));
    }

    public record Entity(String attr1, String attr2) {
    }
}
