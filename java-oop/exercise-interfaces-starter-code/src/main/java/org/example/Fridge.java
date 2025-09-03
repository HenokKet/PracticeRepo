package org.example;

public class Fridge implements Connectable{
    private boolean on;

    @Override
    public void turnOn()  { on = true; }
    @Override
    public void turnOff() { on = false; }

    @Override
    public boolean getState() {
        return on;
    }

    @Override
    public String getName() {
        return "Fridge";
    }
}
