package be.bnair.hm.entities;

import java.util.Random;

public class Monstre extends EtreVivant {

    public enum Race
    {
        Loup, Orque, Dragonnet
    }

    private String username;
    private Race race;

    public Monstre(int x, int y)
    {
        super(x,y);
        int choice = new Random().nextInt(3);
        if(choice == 0) this.race = Race.Loup;
        else if(choice == 1) this.race = Race.Orque;
        else if(choice == 2) this.race = Race.Dragonnet;
        else this.race = Race.Dragonnet;
    }
    public Race GetRace() {
        return race;
    }
    public String GetUsername() {
        return username;
    }
    public void SetRace(Race race) {
        this.race = race;
    }
    public void SetUsername(String username) {
        this.username = username;
    }
}
