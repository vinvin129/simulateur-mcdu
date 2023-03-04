package fr.ptut2022.simulateurmcdu.mcdu;

import fr.ptut2022.simulateurmcdu.mcdu.events.SettingChangeMappingEvent;
import fr.ptut2022.simulateurmcdu.mcdu.models.ChangementDonnee;
import fr.ptut2022.simulateurmcdu.mcdu.models.Donnee;
import fr.ptut2022.simulateurmcdu.mcdu.models.LSKKey;
import org.springframework.lang.Nullable;

import java.util.*;

public class Mcdu {
    public static final Mcdu INSTANCE = new Mcdu();

    private final Map<LSKKey, Donnee> mapping = new EnumMap<>(LSKKey.class);
    private boolean connected = false;

    private List<SettingChangeMappingEvent> listeners = new ArrayList<>();

    private Mcdu() {
        mapping.put(LSKKey.LSK1L, null);
        mapping.put(LSKKey.LSK2L, null);
        mapping.put(LSKKey.LSK3L, null);
        mapping.put(LSKKey.LSK4L, null);
        mapping.put(LSKKey.LSK5L, null);
        mapping.put(LSKKey.LSK6L, null);

        mapping.put(LSKKey.LSK1R, null);
        mapping.put(LSKKey.LSK2R, null);
        mapping.put(LSKKey.LSK3R, null);
        mapping.put(LSKKey.LSK4R, null);
        mapping.put(LSKKey.LSK5R, null);
        mapping.put(LSKKey.LSK6R, null);
    }

    public Optional<Donnee> getActualDonnee(LSKKey key) {
        return Optional.ofNullable(mapping.get(key));
    }

    public Map<LSKKey, Donnee> getMapping() {
        return new HashMap<>(this.mapping);
    }

    public void changementMapping(LSKKey key, @Nullable Donnee donnee) {
        mapping.replace(key, donnee);
        this.listeners.forEach(
                settingChangeMappingEvent -> settingChangeMappingEvent.event(new ChangementDonnee(key, donnee))
        );
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public void addListener(SettingChangeMappingEvent event) {
        this.listeners.add(event);
    }
}
