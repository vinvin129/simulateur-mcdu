package fr.ptut2022.simulateurmcdu.mcdu.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ChangementDonnee {
    @NonNull private LSKKey lskKey;
    @NonNull private Donnee donnee;
}
