package be.bnair.hm.event.imp;

import be.bnair.hm.entities.Hero;
import be.bnair.hm.entities.Monstre;
import be.bnair.hm.event.Event;

public class FightStartEvent extends Event {
    private Hero hero;
    private Monstre monstre;

    public FightStartEvent(Hero hero, Monstre monstre) {
        this.hero = hero;
        this.monstre = monstre;
    }

    public Hero getHero() {
        return hero;
    }

    public Monstre getMonstre() {
        return monstre;
    }
}
