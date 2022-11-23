package fr.ptut2022.simulateurmcdu.mcdu;

import fr.ptut2022.simulateurmcdu.mcdu.models.Mcdu;
import fr.ptut2022.simulateurmcdu.mcdu.models.Window;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
public class WindowRestController {
    private final Mcdu mcdu = Mcdu.INSTANCE;
    @GetMapping("/windows")
    public Window[] getWindows() {
        return mcdu.getWindows().toArray(Window[]::new);
    }

    @GetMapping("/window/{name}")
    public Window getWindow(@PathVariable("name") String name) {
        return mcdu.getWindows().stream()
                .filter(window -> window.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @PostMapping(path = "/window", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Window> createWindow(@RequestBody Window newWindow) {
        this.mcdu.getWindows().add(newWindow);
        return new ResponseEntity<>(newWindow, HttpStatus.CREATED);
    }
}
