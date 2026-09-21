import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        String ipServidor = "127.0.0.1"; // Localhost
        int portaServidor = 12345;

        try (Socket socket = new Socket(ipServidor, portaServidor)) {
            System.out.println("Conectado ao servidor!");

            // 1. Configura a leitura e envio de dados
            PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 2. Envia a solicitação/tarefa para o servidor
            String minhaTarefa = "redes de computadores com o Prof. Augusto";
            System.out.println("Enviando tarefa: " + minhaTarefa);
            saida.println(minhaTarefa);

            // 3. Aguarda e lê a resposta do servidor
            String resposta = entrada.readLine();
            System.out.println("Recebido: " + resposta);

        } catch (IOException e) {
            e.printStackTrace();// Trata de erros 
        }
    }
}