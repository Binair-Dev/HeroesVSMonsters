package be.bnair.hm.entities.childs;

import be.bnair.hm.entities.Hero;

public class Nain extends Hero {
    public Nain(String username, int x, int y)
    {
        super(username, Race.Nain, x, y);
    }

    @Override
    public int getEndurance() {
        return getEndurance() + 2;
    }
}
