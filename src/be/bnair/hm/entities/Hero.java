package be.bnair.hm.entities;

public class Hero extends EtreVivant{
    public enum Race
    {
        Humain, Nain
    }

    private String username;
    private Race race;
    public Hero(String username, Race race, int x, int y)
    {
        super(x, y);
        this.username = username;
        this.race = race;
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
