package fr.ptut2022.simulateurmcdu.mcdu.models;

import java.util.ArrayList;
import java.util.List;

public class Mcdu {

    public static final Mcdu INSTANCE = new Mcdu();

    private Mcdu() {}
    private final List<Window> windows = new ArrayList<>();

    public List<Window> getWindows() {
        return windows;
    }
}
