package fr.ptut2022.simulateurmcdu.mcdu.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LSKClickResult {
    @NonNull private LSKKey key;
    @NonNull private String input;
}
