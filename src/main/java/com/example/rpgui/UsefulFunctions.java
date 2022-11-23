package com.example.rpgui;
import java.util.concurrent.ThreadLocalRandom;

abstract class UsefulFunctions {

    public static int randomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
    public static double randomDouble(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }
    public static double roundADouble(double value){
        value *= 100;
        value = Math.round(value);
        value /= 100;
        return value;
    }
}