package be.bnair.hm.event;

import be.bnair.hm.Main;
import be.bnair.hm.entities.Hero;
import be.bnair.hm.event.imp.FightStartEvent;
import be.bnair.hm.event.imp.InitEvent;
import be.bnair.hm.utils.ConsoleColor;
import be.bnair.hm.utils.Utils;
import be.bnair.hm.utils.WindowsUtils;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;

public class EventListener {

    @EventTarget
    public static void init(InitEvent event) {
        WindowsUtils.init();
        try
        {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException e)
        {
            System.out.println("Error: " + e.getMessage());
            System.exit(-1);
        }

        GlobalScreen.addNativeKeyListener(new Main());
    }
    @EventTarget
    public static void fightTriggerEvent(FightStartEvent e) {
        boolean isFighting = true;
        Utils.clearConsole();
        System.out.println(ConsoleColor.RED + "Un monstre surgit a coté de vous et tente de vous attaquer !" + ConsoleColor.RESET);
        while(isFighting) {
            if(e.getHero().getPV() > 0 || e.getMonstre().getPV() > 0) {
                try {
                    if(e.getHero().getPV() > 0)
                    e.getHero().Frappe(e.getMonstre());
                    else isFighting = false;
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                try {
                    if(e.getMonstre().getPV() > 0)
                    e.getMonstre().Frappe(e.getHero());
                    else isFighting = false;
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        try {
            System.out.println("Récapitulatif: ");
            System.out.println(ConsoleColor.GREEN + "    * Nom: " + ConsoleColor.RED + e.getHero().GetUsername());
            System.out.println(ConsoleColor.GREEN + "    * Race: " + ConsoleColor.RED + e.getHero().GetRace());
            System.out.println(ConsoleColor.GREEN + "    * Vie: " + ConsoleColor.RED + e.getHero().getPV() + "/" + e.getHero().getMaxPV());
            System.out.println(ConsoleColor.GREEN + "    * Force: " + ConsoleColor.RED + e.getHero().getForce());
            System.out.println(ConsoleColor.GREEN + "    * Endurance: " + ConsoleColor.RED + e.getHero().getEndurance());
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
