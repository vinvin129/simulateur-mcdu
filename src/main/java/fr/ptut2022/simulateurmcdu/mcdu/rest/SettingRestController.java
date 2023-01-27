package fr.ptut2022.simulateurmcdu.mcdu.rest;

import fr.ptut2022.simulateurmcdu.mcdu.Mcdu;
import fr.ptut2022.simulateurmcdu.mcdu.models.Donnee;
import fr.ptut2022.simulateurmcdu.mcdu.models.LSKKey;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping("/setting/emit/")
public class SettingRestController {
    @GetMapping("mapping")
    public Map<LSKKey, Donnee> getMapping() {
        return Mcdu.INSTANCE.getMapping();
    }
}
