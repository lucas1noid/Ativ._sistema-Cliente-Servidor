import java.io.*;
import java.net.*;
import java.util.Scanner;

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
            System.out.println("  Conectado ao Servidor TCP em " + ipServidor + ":" + portaServidor);

            while (true) {
                System.out.println("\nEscolha uma funcionalidade:");
                System.out.println("  [1] Converter para Maiúsculas");
                System.out.println("  [2] Ordenar em Ordem Alfabética");
                System.out.println("  [3] Inverter Texto");
                System.out.println("  Digite 'SAIR' para encerrar.");

                // 1. Solicita as informações ao user
                System.out.print("Digite o número da opção (1, 2, 3 ou SAIR): ");
                String opcao = teclado.nextLine().trim();

                if (opcao.equalsIgnoreCase("SAIR")) {
                    saida.println("SAIR");
                    String respostaFinal = entrada.readLine();
                    System.out.println("\nServidor respondeu: " + respostaFinal);
                    break;
                }

                System.out.print("Digite o texto de entrada: ");
                String texto = teclado.nextLine();

                // 2. Envia a tarefa para o servidor
                saida.println(opcao + ":" + texto);

                // 3. Aguarda e le a resposta do servidor
                String resposta = entrada.readLine();
                System.out.println("\nServidor respondeu:\n" + resposta);
            }

        } catch (IOException e) {
            e.printStackTrace();// Trata de erros
        }
    }
//NOID
}