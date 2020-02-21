package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        IOCommand command = new IOCommand();
        //Fichier fichier = new Fichier();
        boolean connexion = command.connexion("192.168.1.131", 6002);
//        boolean connexion = command.connexion("192.168.1.24", 6003);
//        boolean connexion = command.connexion("192.168.1.131", 6001);
       // boolean connexion = command.connexion("192.168.100.25", 5999);
        if(!connexion) return;
        command.run();
        String str = "";
        String choix = "";
        String speak = "";
        String nom;
        String envoieA;
        Boolean commandeConnue = false;

        command.ecrireEcran("Quel est ton login ?");
        command.ecrireReseau(command.lireEcran());
        Thread.sleep(100);

        do {
            command.ecrireEcran("Que voulez vous faire ?");
            command.ecrireEcran("1. /serveur\n2. /clientDispo\n3. /speak + login\n4. /quit");
            choix = command.lireEcran();
            speak = choix;


            if(choix.length() > 7 && choix.equals("/serveur")) {
                do {
                    str = command.lireEcran();

                    command.ecrireReseau(str);
                    choix = str;
                } while (!str.equals("/back") && !str.equals("/quit"));

                Thread.sleep(100);
                commandeConnue = true;
            } if (choix.length() > 11 && choix.equals("/clientDispo")) {
                command.ecrireReseau("getUtilisateursOnline");
                Thread.sleep(100);
                commandeConnue = true;
            } if(choix.length() > 7 && speak.substring(0,7).equals("/speak ")) {
                // /speak

                nom = speak.substring(7);
                command.ecrireEcran("Que voulez vous envoyer a " + nom);

                do {
                    envoieA = command.lireEcran();
                    if(!envoieA.equals("/back") && !envoieA.equals("/quit"))
                        command.ecrireReseau("speakTo/" + nom + "/" + envoieA);
                    Thread.sleep(100);
                }while(!envoieA.equals("/back") && !envoieA.equals("/quit"));
                commandeConnue = true;
            } else {
                if(!choix.equals("/quit") && !commandeConnue)
                    command.ecrireEcran("Commande inconnue");
            }
        } while(!choix.equals("/quit"));
        command.ecrireReseau("quit");
        command.deconnexion();
    }
}