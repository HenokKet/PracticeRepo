package org.example;

public class TV implements Connectable{
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
        return "Toaster";
    }
}
