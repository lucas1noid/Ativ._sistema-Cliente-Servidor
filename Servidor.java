import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        int porta = 12345;

        try (ServerSocket server = new ServerSocket(porta)) {
            System.out.println("Servidor aguardando conexão na porta " + porta + "...");

            // 1. Pausa o programa até um cliente se conectar
            Socket socket = server.accept();
            System.out.println("Cliente conectado!");

            // 2. Configura a leitura e envio de dados
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);

            // 3. Lê a tarefa enviada pelo cliente
            String mensagemRecebida = entrada.readLine();
            System.out.println("Recebido do cliente: " + mensagemRecebida);

            // 4. Executa a tarefa
            String resposta = mensagemRecebida.toUpperCase();

            // 5. Envia a resposta de volta ao cliente
            saida.println("RESPOSTA DO SERVIDOR: " + resposta);

            // 6. Encerra a conexão
            socket.close();
            System.out.println("Atendimento finalizado.");

        } catch (IOException e) {
            e.printStackTrace();// Trata de erros
        }
    }
}