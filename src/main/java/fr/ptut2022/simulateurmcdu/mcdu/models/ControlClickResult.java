package fr.ptut2022.simulateurmcdu.mcdu.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ControlClickResult {
    @NonNull private ControlKey key;
    @NonNull private String input;
}
