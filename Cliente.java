import java.io.*;
import java.net.*;
import java.util.Scanner; // Import do Scanner

public class Cliente {
    public static void main(String[] args) {
        String ipServidor = "127.0.0.1";
        int portaServidor = 12345;

        try (
            Socket socket = new Socket(ipServidor, portaServidor);
            PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner teclado = new Scanner(System.in) // leitor
        ) {
            System.out.println("Conectado ao servidor!");

            // 1. Solicita e lê o texto do usuário
            System.out.print("Digite uma frase para enviar: ");
            String minhaTarefa = teclado.nextLine();

            // 2. Envia a frase digitada para o servidor
            saida.println(minhaTarefa);

            // 3. Aguarda e le a resposta do servidor
            String resposta = entrada.readLine();
            System.out.println("Servidor respondeu: " + resposta);

        } catch (IOException e) {
            e.printStackTrace();// Trata de erros
        }
    }
}