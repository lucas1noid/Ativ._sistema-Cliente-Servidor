import java.io.*;
import java.net.*;
import java.util.Arrays;

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

            String mensagemRecebida;

            // 3. Lê a tarefa enviada pelo cliente em loop
            while ((mensagemRecebida = entrada.readLine()) != null) {
                System.out.println("Recebido do cliente: " + mensagemRecebida);

                if (mensagemRecebida.trim().equalsIgnoreCase("SAIR")) {
                    saida.println("Conexão encerrada pelo servidor.");
                    break;
                }

                String resposta;

                // 4. executa a tarefa escolhida pelo cliente
                if (mensagemRecebida.contains(":")) {
                    String[] partes = mensagemRecebida.split(":", 2);
                    String opcao = partes[0].trim();
                    String texto = partes[1].trim();

                    switch (opcao) {
                        case "1":
                            // op 1
                            resposta = "MAIÚSCULAS: " + texto.toUpperCase();
                            break;

                        case "2":
                            // op 2
                            char[] caracteres = texto.toCharArray();
                            Arrays.sort(caracteres);
                            resposta = "ORDEM ALFABÉTICA: " + new String(caracteres);
                            break;

                        case "3":
                            // op 3
                            String textoInvertido = new StringBuilder(texto).reverse().toString();
                            resposta = "TEXTO INVERTIDO: " + textoInvertido;
                            break;

                        default:
                            resposta = "ERRO: Opção inválida! Escolha 1, 2 ou 3.";
                            break;
                    }
                } else {
                    resposta = "ERRO: Formato inválido! Envie no formato 'OPÇÃO: TEXTO' ou 'SAIR'";
                }

                // 5. Envia a resposta de volta ao cliente
                saida.println(resposta);
            }

            // 6. Encerra a conexão
            socket.close();
            System.out.println("Atendimento finalizado.");

        } catch (IOException e) {
            e.printStackTrace();// Trata de erros
        }
    }
//NOID
}