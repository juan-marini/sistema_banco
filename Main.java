import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        String opcao;

        do {
            mostrarMenu();
            opcao = read.nextLine();

            switch (opcao) {
                case "1":
                    System.out.println("Digite seu CPF:");
                    String cpfLogin = read.nextLine();
                    System.out.println("Digite sua senha:");
                    String senhaLogin = read.nextLine();
                    GerenciamentoDeUsuarios.fazerLogin(cpfLogin, senhaLogin, read);
                    break;

                case "2":
                    System.out.println("Digite seu nome completo:");
                    String nome = read.nextLine();
                    System.out.println("Digite seu CPF:");
                    String cpfCadastro = read.nextLine();
                    System.out.println("Digite seu RG:");
                    String rgCadastro = read.nextLine();
                    System.out.println("Digite sua idade:");
                    String idadeCadastro = read.nextLine();
                    System.out.println("Digite sua senha:");
                    String senhaCadastro = read.nextLine();
                    GerenciamentoDeUsuarios.cadastrarPessoa(nome, cpfCadastro, rgCadastro, idadeCadastro, senhaCadastro, read);
                    break;

                case "3":
                    System.out.println("Digite o login de administrador:");
                    String loginAdmin = read.nextLine();
                    System.out.println("Digite a senha de administrador:");
                    String senhaAdmin = read.nextLine();
                    GerenciamentoDeUsuarios.telaAdmin(loginAdmin, senhaAdmin, read);
                    break;

                case "4":
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        } while (!opcao.equals("4"));

        read.close();
    }

    public static void mostrarMenu() {
        System.out.println("Escolha uma opção:");
        System.out.println("[1] Login");
        System.out.println("[2] Cadastrar-se");
        System.out.println("[3] Acesso administrativo");
        System.out.println("[4] Sair");
    }
}
