package fr.ptut2022.simulateurmcdu.mcdu.rest;

import fr.ptut2022.simulateurmcdu.mcdu.models.Mcdu;
import fr.ptut2022.simulateurmcdu.mcdu.models.Window;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class WindowRestController {
    private final Mcdu mcdu = Mcdu.INSTANCE;
    @GetMapping("/window")
    public Window getWindow() {
        return mcdu.getWindow();
    }

    @PostMapping(path = "/window", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Window> createWindow(@RequestBody Window newWindow) {
        this.mcdu.setWindow(newWindow);
        return new ResponseEntity<>(newWindow, HttpStatus.CREATED);
    }
}
