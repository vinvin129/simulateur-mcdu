package fr.ptut2022.simulateurmcdu.mcdu.models;

import lombok.Data;
import lombok.NonNull;

@Data
public class Donnee {
    @NonNull private String label;
    @NonNull private String valeur;
    @NonNull private String couleur;
}
