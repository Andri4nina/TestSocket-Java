
import java.io.*;
import java.net.*;

public class TCPServeur {

    public static void main(String[] args) {
        int port = 8080;

        try {
            // Création du ServerSocket pour écouter les connexions entrantes
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Serveur en attente de connexions...");

            // Boucle infinie pour accepter les connexions clientes
            while (true) {

                // Accepter une connexion cliente
                Socket clientSocket = serverSocket.accept();

                // Flux d'entrée et de sortie pour la communication avec le client
                InputStream in = clientSocket.getInputStream();
                OutputStream out = clientSocket.getOutputStream();

                // Lecture des données envoyées par le client
                byte[] buffer = new byte[1024];
                int bytesRead = in.read(buffer);
                String messageFromClient = new String(buffer, 0, bytesRead);
                System.out.println("Rechercher les resultats de l'examen du numero: " + messageFromClient);

                //envoyer une réponse au client
                String reponse = messageFromClient + " passer";
                System.out.println(reponse);

                out.write(reponse.getBytes());

                // Fermeture des flux et du socket client
                in.close();
                out.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
