package fr.ptut2022.simulateurmcdu.mcdu.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ClickResult {
    @NonNull private LSKKey lskKey;
    @NonNull private String input;
}
