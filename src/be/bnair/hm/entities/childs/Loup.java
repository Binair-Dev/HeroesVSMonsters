package be.bnair.hm.entities.childs;

import be.bnair.hm.De;
import be.bnair.hm.entities.EtreVivant;
import be.bnair.hm.entities.Monstre;
import be.bnair.hm.interfaces.IDroppable;
import be.bnair.hm.materials.Material;

public class Loup extends Monstre implements IDroppable {
    public Loup(int x, int y)
    {
        super(x, y);
    }
    public void Drop(EtreVivant tueur)
    {
        De de = new De();
        for(int i = 0; i < de.Lance(3); i++)
            tueur.addInventaire(new Material(Material.Type.Cuir));
    }
}
