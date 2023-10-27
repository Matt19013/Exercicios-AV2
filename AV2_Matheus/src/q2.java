
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class q2 {

    private static final String ARQUIVO_USUARIOS = "usuarios.txt";
    private static final Map<String, String> usuarios = new HashMap<>();

    public static void main(String[] args) {
        carregarUsuarios();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao Sistema de login!");
        while (true) {
            exibirOpcoes();
            int escolha = scanner.nextInt();
            scanner.nextLine(); 

            switch (escolha) {
                case 1:
                    cadastrarUsuario(scanner);
                    break;
                case 2:
                    fazerLogin(scanner);
                    break;
                case 3:
                    salvarUsuarios();
                    System.out.println("Saindo do programa. Até mais!");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void exibirOpcoes() {
        System.out.println("\nEscolha uma opção:");
        System.out.println("1 - Cadastrar novo usuário");
        System.out.println("2 - Fazer login");
        System.out.println("3 - Sair");
    }
    private static void carregarUsuarios() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_USUARIOS))) {
            br.lines()
                    .map(line -> line.split(":"))
                    .filter(parts -> parts.length == 2)
                    .forEach(parts -> usuarios.put(parts[0], parts[1]));
        } catch (IOException e) {
            System.out.println("Erro ao carregar usuários. Criando novo arquivo.");
        }
    }
    private static void salvarUsuarios() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_USUARIOS))) {
            usuarios.forEach((login, senha) -> {
                try {
                    bw.write(login + ":" + senha);
                    bw.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuários.");
        }
    }
    private static void cadastrarUsuario(Scanner scanner) {
        System.out.println("Digite o novo login:");
        String novoLogin = scanner.nextLine();

        if (usuarios.containsKey(novoLogin)) {
            System.out.println("Login já cadastrado. Tente outro.");
        } else {
            System.out.println("Digite a senha para o novo usuário:");
            String novaSenha = scanner.nextLine();
            usuarios.put(novoLogin, novaSenha);
            System.out.println("Usuário cadastrado com sucesso!");
        }
    }
    private static void fazerLogin(Scanner scanner) {
        System.out.println("Digite o login:");
        String login = scanner.nextLine();

        if (usuarios.containsKey(login)) {
            System.out.println("Digite a senha:");
            String senha = scanner.nextLine();
            if (usuarios.get(login).equals(senha)) {
                System.out.println("SUCESSO! Login realizado com sucesso.");
            } else {
                System.out.println("FRACASSO! Senha incorreta.");
            }
        } else {
            System.out.println("FRACASSO! Usuário não cadastrado.");
     }
}
}
