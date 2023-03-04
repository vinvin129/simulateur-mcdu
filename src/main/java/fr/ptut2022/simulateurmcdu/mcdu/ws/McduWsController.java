package fr.ptut2022.simulateurmcdu.mcdu.ws;

import fr.ptut2022.simulateurmcdu.mcdu.Mcdu;
import fr.ptut2022.simulateurmcdu.mcdu.models.ClickResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class McduWsController {

    @Autowired
    public McduWsController(SimpMessagingTemplate messagingTemplate) {
        Mcdu.INSTANCE.addListener(
                changementDonnee -> messagingTemplate.convertAndSend("/mcdu/receive/newView", changementDonnee));
    }

    @MessageMapping("/click")
    @SendTo("/setting/receive/click")
    public ClickResult click(ClickResult clickResult) {
        return clickResult;
    }

    @MessageMapping("/connexion")
    public void connexion(String name) {
        Mcdu.INSTANCE.setConnected(true);
    }
}
