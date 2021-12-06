package be.bnair.hm;

import be.bnair.hm.entities.Hero;
import be.bnair.hm.entities.Monstre;
import be.bnair.hm.event.EventListener;
import be.bnair.hm.event.EventManager;
import be.bnair.hm.event.imp.FightStartEvent;
import be.bnair.hm.event.imp.InitEvent;
import be.bnair.hm.utils.ConsoleColor;
import be.bnair.hm.utils.Utils;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import java.util.Scanner;

public class Main implements NativeKeyListener {

    public static int movements;
    public static Plateau HvsM;
    public static Hero player;
    private static String lastPressed = null;

    public static void main(String[] args)
    {
        EventManager.register(new EventListener());
        new InitEvent().call();

        Utils.drawLogo();
        System.out.println(ConsoleColor.CYAN + "Veuillez créer votre personnage !");
        System.out.println("Nom de votre personnage: ");
        Scanner scan = new Scanner(System.in);
        String username = scan.nextLine();
        System.out.println("Race de votre personnage: ([" + ConsoleColor.RED + 1 + ConsoleColor.CYAN + "] Humain, [" + ConsoleColor.RED + 2 + ConsoleColor.CYAN + "] Nain)");
        String race = scan.nextLine();
        int raceType = 0;
        Hero.Race heroRace = Hero.Race.Humain;
        while(raceType == 0)
        {
            try
            {
                raceType = Integer.parseInt(race);
            }
            catch (Exception e)
            {
                System.out.println("Erreur, vous devez entrer un nombre entre 1 et 2 !");
                System.out.println("Race de votre personnage: ([" + ConsoleColor.RED + 1 + ConsoleColor.CYAN + "] Humain, [" + ConsoleColor.RED + 2 + ConsoleColor.CYAN + "] Nain)");
                race = scan.nextLine();
            };
        }

        System.out.println("Génération du plateau...");
        HvsM = new Plateau();

        switch (raceType)
        {
            case 1:
                heroRace = Hero.Race.Humain;
                break;
            case 2:
                heroRace = Hero.Race.Nain;
                break;
            default:
                break;
        }
        player = new Hero(username, heroRace, 0,0);
        System.out.println();
        System.out.println("Récapitulatif: ");
        System.out.println(ConsoleColor.GREEN + "    * Nom: " + ConsoleColor.RED + player.GetUsername());
        System.out.println(ConsoleColor.GREEN + "    * Race: " + ConsoleColor.RED + player.GetRace());
        System.out.println(ConsoleColor.GREEN + "    * Vie: " + ConsoleColor.RED + player.getPV() + "/" + player.getMaxPV());
        System.out.println(ConsoleColor.GREEN + "    * Force: " + ConsoleColor.RED + player.getForce());
        System.out.println(ConsoleColor.GREEN + "    * Endurance: " + ConsoleColor.RED + player.getEndurance());
        System.out.println();
        System.out.println("IMPORTANT: Utilisez les flèches de votre clavier pour vous déplacer !");
        System.out.println();

        boolean isRunning = true;
        while (isRunning) {
            try {
                Utils.clearConsole();
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(ConsoleColor.CYAN + "[" + ConsoleColor.RED + 1 + ConsoleColor.CYAN + "] Lancer les dés, [" + ConsoleColor.RED + 2 + ConsoleColor.CYAN + "] Fermer");
            HvsM.DrawShowed(player.getX(), player.getY());
            int choice = 0;
            while(choice == 0) {
                try {
                    String response = scan.next();
                    choice = Integer.parseInt(response);
                    movements = new De().Lance();
                    lastPressed = null;
                }
                catch (Exception e) {
                    System.out.println(ConsoleColor.RED + "Erreur, veuillez entrer un nombre valide !" + ConsoleColor.CYAN);
                    System.out.println(ConsoleColor.CYAN + "[" + ConsoleColor.RED + 1 + ConsoleColor.CYAN + "] Lancer les dés, [" + ConsoleColor.RED + 2 + ConsoleColor.CYAN + "] Fermer");
                }
            }
            if(choice == 2) {
                System.out.println(ConsoleColor.GREEN + "Merci d'avoir joué a Heroes and Monsters !");
                Utils.close();
            }
            else {
                System.out.println("Mouvement restant: " + movements);
                System.out.println("Monstre restant : " + HvsM.GetEtreVivants().size());
                while (movements > 0) {
                    if(lastPressed != null)
                    {
                        switch (lastPressed) {
                            case "Droite":
                                if(player.getY() < 14) {
                                    player.setY(player.getY() + 1);
                                    movements -= 1;
                                    System.out.println("Déplacement vers la Droite !");
                                    Utils.clearConsole();
                                    System.out.println("Mouvement restant: " + movements);
                                    Main.HvsM.DrawShowed(player.getX(), player.getY());
                                    lastPressed = null;
                                }
                                else {
                                    System.out.println("Déplacement impossible dans cette direction");
                                }
                                break;
                            case "Gauche":
                                if(player.getY() > 0) {
                                    player.setY(player.getY() - 1);
                                    movements -= 1;
                                    System.out.println("Déplacement vers la Gauche !");
                                    Utils.clearConsole();
                                    System.out.println("Mouvement restant: " + movements);
                                    Main.HvsM.DrawShowed(player.getX(), player.getY());
                                    lastPressed = null;
                                }
                                else {
                                    System.out.println("Déplacement impossible dans cette direction");
                                }
                                break;
                            case "Haut":
                                if(player.getX() > 0) {
                                    player.setX(player.getX() - 1);
                                    movements -= 1;
                                    System.out.println("Déplacement vers le Haut !");
                                    Utils.clearConsole();
                                    System.out.println("Mouvement restant: " + movements);
                                    Main.HvsM.DrawShowed(player.getX(), player.getY());
                                    lastPressed = null;
                                }
                                else {
                                    System.out.println("Déplacement impossible dans cette direction");
                                }
                                break;
                            case "Bas":
                                if(player.getX() < 14) {
                                    player.setX(player.getX() + 1);
                                    movements -= 1;
                                    System.out.println("Déplacement vers le Bas !");
                                    Utils.clearConsole();
                                    System.out.println("Mouvement restant: " + movements);
                                    Main.HvsM.DrawShowed(player.getX(), player.getY());
                                    lastPressed = null;
                                }
                                else {
                                    System.out.println("Déplacement impossible dans cette direction");
                                }
                                break;
                        }
                        for(Monstre v : HvsM.GetEtreVivants()) {
                            if(v.getX() == player.getX() && v.getY() == player.getY()) {
                                new FightStartEvent(player, v).call();
                            }
                        }
                    }
                    else {
                        Utils.clearConsole();
                        Main.HvsM.DrawShowed(player.getX(), player.getY());
                        System.out.println("Mouvement restant: " + movements);
                        try {
                            Thread.sleep(250);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public void nativeKeyPressed(NativeKeyEvent e)
    {
        String typed = NativeKeyEvent.getKeyText(e.getKeyCode());
        if((typed.equals("Haut") || typed.equals("Bas") || typed.equals("Gauche") || typed.equals("Droite")) && lastPressed == null) {
            lastPressed = typed;
        }
    }
}
