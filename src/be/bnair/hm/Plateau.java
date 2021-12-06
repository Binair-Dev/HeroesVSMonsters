package be.bnair.hm;

import be.bnair.hm.entities.EtreVivant;
import be.bnair.hm.entities.Monstre;
import be.bnair.hm.utils.ConsoleColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Plateau {

    private List<Monstre> etreVivants;
    private List<Monstre> toRemove = new ArrayList<Monstre>();
    private int[][] cases = new int[15][15];
    public Plateau()
    {
        this.etreVivants = new ArrayList<Monstre>();
        GenerateMonsters();
    }

    public List<Monstre> GetEtreVivants() {
        return etreVivants;
    };
    public int[][] GetCases() {
        return cases;
    }
    public void AjouterEtreVivants(Monstre etre) {
        etreVivants.add(etre);
    }
    public void RetirerEtreVivants(Monstre etre) {
        etreVivants.remove(etre);
    }

    public void GenerateMonsters()
    {
        int spawnned = 0;
        while(spawnned  < 10)
        {
            boolean isTaken = false;

            int randX = new Random().nextInt(15-1) + 1;
            int randY = new Random().nextInt(15-1) + 1;

            Monstre toAdd = new Monstre(randX, randY);

            if (spawnned > 0)
            {
                for(Monstre etre : GetEtreVivants())
                {
                    for (int x = -2; x < 2; x++)
                    {
                        for (int y = -2; y < 2; y++)
                        {
                            if (etre.getX() == x + randX && etre.getY() == y + randY)
                            {
                                isTaken = true;
                            }
                        }
                    }
                }
                if (!isTaken)
                {
                    this.AjouterEtreVivants(toAdd);
                    spawnned++;
                }
            }
            else
            {
                this.AjouterEtreVivants(toAdd);
                spawnned++;
            }
        }
    }
    public void DrawShowed(int playerX, int playerY)
    {
        String[][] toDraw = new String[15][15];

        for (int x = 0; x < 15; x++)
        {
            for (int y = 0; y < 15; y++)
            {
                toDraw[x][y] = " ";
                for(Monstre etre : GetEtreVivants())
                {
                    if (etre.getX() == x && etre.getY() == y)
                    {
                        if(etre.getPV() > 0) {
                            if(etre.GetRace() == Monstre.Race.Loup) toDraw[x][y] = "L";
                            if(etre.GetRace() == Monstre.Race.Orque) toDraw[x][y] = "O";
                            if(etre.GetRace() == Monstre.Race.Dragonnet) toDraw[x][y] = "D";
                        }
                        else {
                            toRemove.add(etre);
                            toDraw[x][y] = "X";
                        }

                    }
                }
                for(Monstre etre : toRemove)
                {
                    if(etre != null) {
                        etreVivants.remove(etre);
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                toRemove.clear();
            }
        }

        toDraw[playerX][playerY] = "H";

        int amount = 0;
        for(int i = 0; i < 15; i++)
        {
            for(int j = 0; j < 15; j++) {
                amount++;
                System.out.print(ConsoleColor.WHITE + "|");
                if (amount == 15)
                {
                    amount = 0;
                    System.out.println();
                }
                else
                {
//                    if (toDraw[i][j].equals("L")) System.out.print(ConsoleColor.RED + toDraw[i][j] + ConsoleColor.RESET);
//                    else if (toDraw[i][j].equals("D")) System.out.print(ConsoleColor.RED + toDraw[i][j] + ConsoleColor.RESET);
//                    else if (toDraw[i][j].equals("O")) System.out.print(ConsoleColor.RED + toDraw[i][j] + ConsoleColor.RESET);
//                    else if(toDraw[i][j].equals("H"))
//                        System.out.print(ConsoleColor.GREEN + "H" + ConsoleColor.RESET);
//                    else
//                        System.out.print(ConsoleColor.PURPLE + " " + ConsoleColor.RESET);

                    if(toDraw[i][j].equals("H"))
                        System.out.print(ConsoleColor.GREEN + "H" + ConsoleColor.RESET);
                    else
                        System.out.print(ConsoleColor.PURPLE + " " + ConsoleColor.RESET);

                }
            }
        }
    }

}
