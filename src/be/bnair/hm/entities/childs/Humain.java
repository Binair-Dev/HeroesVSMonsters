package be.bnair.hm.entities.childs;

import be.bnair.hm.entities.Hero;

public class Humain extends Hero {
    public Humain(String username, int x, int y)
    {
        super(username, Race.Humain, x, y);
    }

    @Override
    public int getForce() {
        return getForce() + 1;
    }

    @Override
    public int getEndurance() {
        return getEndurance() + 1;
    }
}
