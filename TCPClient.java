import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) {
        // Déclaration des adresse et du port
        int port = 8080;
        String adresse = "127.0.0.1";

        try {
            // Création du socket pour se connecter au serveur
            Socket socket = new Socket(adresse, port);

            // Création du flux de sortie pour envoyer des données au serveur
            OutputStream out = socket.getOutputStream();

            // Scanner pour lire l'entrée de l'utilisateur
            Scanner scanner = new Scanner(System.in);
            String message;

            // Boucle pour permettre l'envoi de plusieurs messages
            while (true) {
                System.out.println("Entrer le numéro de l'étudiant (exemple: 113I22), ou 'exit' pour quitter : ");
                message = scanner.nextLine();

                // Vérification si l'utilisateur veut quitter
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }

                // Envoi des données au serveur
                out.write(message.getBytes());

                // Flux d'entrée pour recevoir les données du serveur
                InputStream in = socket.getInputStream();

                // Taille du tampon pour lire les données
                byte[] buffer = new byte[1024];

                // Lecture des données dans le tampon
                int bytesRead = in.read(buffer);

                // Conversion en chaîne de caractères
                String reponse = new String(buffer, 0, bytesRead);

                // Affichage de la réponse du serveur
                System.out.println("Réponse du serveur : " + reponse);

                // Fermeture du flux d'entrée
                in.close();
            }

            // Fermeture des flux et du socket
            out.close();
            socket.close();

            // Fermeture du scanner
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
