package fr.ptut2022.simulateurmcdu.mcdu.events;

import fr.ptut2022.simulateurmcdu.mcdu.models.ChangementDonnee;

@FunctionalInterface
public interface SettingChangeMappingEvent {
    void event(ChangementDonnee changementDonnee);
}
