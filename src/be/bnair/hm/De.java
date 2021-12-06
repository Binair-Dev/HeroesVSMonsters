package be.bnair.hm;

import java.util.Random;

public class De {
    private final int min = 1;
    private final int max = 6;

    public int Lance() {
        return new Random().nextInt(max-min) + min;
    }
    public int Lance(int max) {
        return new Random().nextInt(max-min) + min;
    }
}
