package be.bnair.hm.utils;

import javax.swing.*;

public class Utils {

    public static void drawLogo()
    {
        System.out.println(ConsoleColor.RED + "BIENVENUE DANS LE MONDE DE: " + ConsoleColor.PURPLE + "HEROES VS MONSTERS !" + ConsoleColor.RESET);
        System.out.println();
    }

    public final static void clearConsole()
    {
        final String ANSI_CLS = "\u001b[2J";
        final String ANSI_HOME = "\u001b[H";
        System.out.print(ANSI_CLS + ANSI_HOME);
        System.out.flush();
    }

    public static void close()
    {
        JOptionPane.showConfirmDialog(null, "Merci d'avoir joué a Heroes VS Monsters", "HvsM", JOptionPane.CLOSED_OPTION);
        System.exit(-1);
    }
}
