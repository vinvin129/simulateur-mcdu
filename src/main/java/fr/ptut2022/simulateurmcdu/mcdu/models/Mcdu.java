package fr.ptut2022.simulateurmcdu.mcdu.models;

public class Mcdu {

    public static final Mcdu INSTANCE = new Mcdu();

    private Mcdu() {}
    private Window window;

    public Window getWindow() {
        return window;
    }

    public void setWindow(Window window) {
        this.window = window;
    }
}
