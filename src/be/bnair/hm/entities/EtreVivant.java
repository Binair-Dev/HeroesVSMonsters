package be.bnair.hm.entities;

import be.bnair.hm.De;
import be.bnair.hm.entities.childs.Humain;
import be.bnair.hm.entities.childs.Nain;
import be.bnair.hm.materials.Material;
import be.bnair.hm.utils.ConsoleColor;
import be.bnair.hm.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EtreVivant {
    private int Endurance;
    private int Force;
    private int PV;
    private int MaxPV;
    private List<Material> Inventaire;
    private int x, y;
    public EtreVivant(int x, int y)
    {
        setupStats();
        setX(x);
        setY(y);
    }

    private void setupStats()
    {
        int tempEnd = 0;
        int tempForce = 0;
        List<Integer> enduranceList = new ArrayList<Integer>();
        List<Integer> forceList = new ArrayList<Integer>();
        De de = new De();

        for (int i = 0; i < 4; i++)
        {
            enduranceList.add(de.Lance());
            forceList.add(de.Lance());
        }
        for (int i = 0; i < 3; i++)
        {
            tempEnd += Collections.max(enduranceList);
            tempForce += Collections.max(forceList);
            enduranceList.remove(Collections.max(enduranceList));
            forceList.remove(Collections.max(forceList));
        }

        setEndurance(tempEnd);
        setForce(tempForce);
        setInventaire(new ArrayList<Material>());

        if (tempEnd < 5)
            setMaxPV(tempEnd + (tempEnd - 1));
        else if (tempEnd > 5 && tempEnd < 10)
            setMaxPV(tempEnd * 2);
        else
            setMaxPV(tempEnd + (tempEnd + 1));
        setPV(getMaxPV());
    }

    public List<Material> getInventaire() {
        return Inventaire;
    }

    public int getEndurance() {
        return Endurance;
    }

    public int getForce() {
        return Force;
    }

    public int getMaxPV() {
        return MaxPV;
    }

    public int getPV() {
        return PV;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setEndurance(int endurance) {
        Endurance = endurance;
    }

    public void setForce(int force) {
        Force = force;
    }

    public void setInventaire(List<Material> inventaire) {
        Inventaire = inventaire;
    }

    public void setMaxPV(int maxPV) {
        MaxPV = maxPV;
    }

    public void setPV(int PV) {
        this.PV = PV;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void addInventaire(Material mat) {
        this.Inventaire.add(mat);
    }

    public void Frappe(EtreVivant etreVivant)
    {
        De de = new De();
        int damages = de.Lance(4) + getForce();

        etreVivant.removePV(damages);
        if(etreVivant instanceof Hero) System.out.println(ConsoleColor.RED + "Tu viens d'encaisser " + damages + " points de dégats !" + ConsoleColor.RESET);
        if(etreVivant instanceof Monstre) System.out.println(ConsoleColor.PURPLE + "Tu viens de mettre " + damages + " points de dégats au " + ((Monstre)etreVivant).GetRace() + ConsoleColor.RESET);
        if (etreVivant.getPV() <= 0)
        {
            Meurt(etreVivant, this);
        }
    }

    private void Meurt(EtreVivant tuer, EtreVivant tueur)
    {
        if(tuer instanceof Hero) {
            System.out.println(ConsoleColor.RED + ((Hero) tuer).GetUsername() + " vient de mourrir !" + ConsoleColor.RESET);
            Utils.close();
        } else System.out.println(ConsoleColor.PURPLE + "Le " + ((Monstre)tuer).GetRace() + " vient de mourrir !" + ConsoleColor.RESET);
        for(Material material : tuer.getInventaire())
        {
            if(tueur.getInventaire() != null && (tueur instanceof Humain || tueur instanceof Nain)) {
                System.out.println(ConsoleColor.YELLOW_BOLD + "Vous venez de récupèrer un " + material.GetMatType() + " sur ce monstre !" + ConsoleColor.RESET);
                tueur.addInventaire(material);
            }
        }
    }

    private void removePV(int amount) {
        PV -= amount;
    }

    private void Repos()
    {
        setPV(getMaxPV());
    }
}
