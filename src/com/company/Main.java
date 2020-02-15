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

            if(choix.equals("/serveur")) {
                do {
                    str = command.lireEcran();
                    command.ecrireReseau(str);
                    Thread.sleep(100);
                } while (!str.equals("back") && !str.equals("quit"));
                Thread.sleep(100);
            } else if (choix.equals("/clientDispo")) {
                command.ecrireReseau("getUtilisateursOnline");
                Thread.sleep(100);
            } else if(choix.substring(0,7).equals("/speak ")) {
                // /speak
                command.ecrireReseau("speakTo " + choix.substring(7));
                Thread.sleep(100);
                do {


                }while(!str.equals("back"));
            } else {
                command.ecrireEcran("Commande inconnue");
            }
            Thread.sleep(100);
        } while(!choix.equals("quit"));
        command.setRunning(false);
        command.deconnexion();
    }
}