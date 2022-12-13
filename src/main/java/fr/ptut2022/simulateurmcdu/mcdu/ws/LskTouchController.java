package fr.ptut2022.simulateurmcdu.mcdu.ws;

import fr.ptut2022.simulateurmcdu.mcdu.models.LskClickInput;
import fr.ptut2022.simulateurmcdu.mcdu.models.LskClickResult;
import fr.ptut2022.simulateurmcdu.mcdu.models.Mapping;
import fr.ptut2022.simulateurmcdu.mcdu.models.Mcdu;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Arrays;

@Controller
public class LskTouchController {

    @MessageMapping("/lskClick")
    @SendTo("/topic/lskClick")
    public LskClickResult lskClick(LskClickInput input) {
        Mapping[] mappingList = Mcdu.INSTANCE.getWindow().getMapping();
        return new LskClickResult(
                input.lskTouch(),
                input.input(),
                mappingList.length == 0 ? null :
                        Arrays.stream(mappingList)
                                .filter(mapping -> input.lskTouch().equals(mapping.mcduLsk()))
                                .map(Mapping::data)
                                .findFirst().orElse(null));
    }

    @MessageMapping("/lskChangeMapping")
    @SendTo("/topic/lskChangeMapping")
    public Mapping lskChangeMapping(Mapping mapping) {
        return mapping;
    }
}
