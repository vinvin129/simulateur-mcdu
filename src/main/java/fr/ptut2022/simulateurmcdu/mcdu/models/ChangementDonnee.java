package fr.ptut2022.simulateurmcdu.mcdu.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class ChangementDonnee {
    @NonNull private LSKKey lskKey;
    private Donnee donnee;
}
