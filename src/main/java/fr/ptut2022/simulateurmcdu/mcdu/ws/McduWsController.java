package fr.ptut2022.simulateurmcdu.mcdu.ws;

import fr.ptut2022.simulateurmcdu.mcdu.Mcdu;
import fr.ptut2022.simulateurmcdu.mcdu.models.ChangementDonnee;
import fr.ptut2022.simulateurmcdu.mcdu.models.ControlClickResult;
import fr.ptut2022.simulateurmcdu.mcdu.models.LSKClickResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class McduWsController {
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public McduWsController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
        Mcdu.INSTANCE.addListener(
                changementDonnee -> messagingTemplate.convertAndSend("/mcdu/receive/newView", changementDonnee));
    }

    @MessageMapping("/click/lsk")
    @SendTo("/setting/receive/click/lsk")
    public LSKClickResult lskClick(LSKClickResult clickResult) {
        return clickResult;
    }

    @MessageMapping("/click/control")
    @SendTo("/setting/receive/click/control")
    public ControlClickResult controlClick(ControlClickResult clickResult) {
        return clickResult;
    }

    @MessageMapping("/connexion")
    public void connexion() {
        Mcdu.INSTANCE.setConnected(true);
        Mcdu.INSTANCE.getMapping().entrySet()
                .stream()
                .filter(lskKeyDonneeEntry -> lskKeyDonneeEntry.getValue() != null)
                .map(lskKeyDonneeEntry -> new ChangementDonnee(lskKeyDonneeEntry.getKey(), lskKeyDonneeEntry.getValue()))
                .forEach(changementDonnee -> messagingTemplate.convertAndSend("/mcdu/receive/newView", changementDonnee));
    }
}
