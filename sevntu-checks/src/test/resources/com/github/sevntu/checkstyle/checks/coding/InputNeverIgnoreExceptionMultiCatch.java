package com.github.sevntu.checkstyle.checks.coding;

public class InputNeverIgnoreExceptionMultiCatch
{

    public void method()
    {
        try {
            throw new InterruptedException();
        }
        catch (java.lang.InterruptedException | NullPointerException| IndexOutOfBoundsException iE) {
        }
    }

    private void handleInterrupt(InterruptedException iE)
    {

    }

}