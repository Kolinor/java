package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        IOCommand command = new IOCommand();
        //Fichier fichier = new Fichier();
        boolean connexion = command.connexion("192.168.1.24", 6001);
//        boolean connexion = command.connexion("192.168.1.24", 6003);
//        boolean connexion = command.connexion("192.168.1.131", 6001);
       // boolean connexion = command.connexion("192.168.100.25", 5999);
        if(!connexion) return;
        command.run();

        String str = "";
        String choix = "";

        command.ecrireEcran("Quel est ton login ?");
        command.ecrireReseau(command.lireEcran());
        Thread.sleep(100);

        do {
            command.ecrireEcran("Que voulez vous faire ?");
            command.ecrireEcran("1. /serveur\n2. /clientDispo");
            choix = command.lireEcran();

            switch (choix) {
                case "/serveur":
                    do {
                        str = command.lireEcran();
                        command.ecrireReseau(str);
                        Thread.sleep(100);
                    } while(!str.equals("back"));

                    break;
                case "/clientDispo":
                    command.ecrireReseau("getUtilisateursOnline");
                    Thread.sleep(100);
                    command.ecrireEcran(command.lireReseau());
                    break;
                default:
                    command.ecrireEcran("Commande inconnue");
            }
            command.ecrireEcran("coucou");
            Thread.sleep(100);
        } while(!choix.equals("quit"));
        System.out.print("sorti");
        command.deconnexion();
    }
}