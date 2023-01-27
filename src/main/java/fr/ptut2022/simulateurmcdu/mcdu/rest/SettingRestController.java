package fr.ptut2022.simulateurmcdu.mcdu.rest;

import fr.ptut2022.simulateurmcdu.mcdu.Mcdu;
import fr.ptut2022.simulateurmcdu.mcdu.models.Donnee;
import fr.ptut2022.simulateurmcdu.mcdu.models.LSKKey;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/setting/emit/")
public class SettingRestController {
    @GetMapping("mapping")
    public Map<LSKKey, Donnee> getMapping() {
        return Mcdu.INSTANCE.getMapping();
    }

    @GetMapping("mapping/{key}")
    public Donnee getMapping(@PathVariable("key") LSKKey key) {
        return Mcdu.INSTANCE.getActualDonnee(key).orElse(null);
    }

    @PostMapping(value = "mapping", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateMapping(@RequestBody Map<LSKKey, Donnee> mapping) {
        mapping.forEach(Mcdu.INSTANCE::changementMapping);
        return ResponseEntity.ok("Mcdu updated");
    }

    @PostMapping(value = "mapping/{key}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateMapping(@RequestBody Donnee donnee, @PathVariable("key") LSKKey key) {
        Mcdu.INSTANCE.changementMapping(key, donnee);
        return ResponseEntity.ok("key " + key + " updated");
    }

    @GetMapping("mcduConnected")
    public boolean mcduConnected() {
        return Mcdu.INSTANCE.isConnected();
    }
}
