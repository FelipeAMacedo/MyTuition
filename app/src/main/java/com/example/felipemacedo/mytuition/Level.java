package com.example.felipemacedo.mytuition;

/**
 * Created by felipemacedo on 23/10/17.
 */

public final class Level {

    private Level() { }

    public static int calculateLevel (int xp) {
        return (int) (1 + Math.sqrt(1 + 8 * xp / 50)) / 2;
    }

    public static int calculateNeededExp (int level) {
        return (int) ((level * level - level) * 50) / 2;
    }
}
