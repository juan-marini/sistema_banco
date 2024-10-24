import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GerenciamentoDeUsuarios {
    private static ArrayList<Pessoa> pessoas = new ArrayList<>();

    public static void cadastrarPessoa(String nome, String numCpf, String numRg, String idade, String senha, Scanner read) {
        if (validarDados(nome, numCpf, numRg, idade, senha)) {
            Pessoa novaPessoa = new Pessoa(nome, senha, numRg, idade, numCpf);
            pessoas.add(novaPessoa);
            System.out.println("[ATENÇÃO] Conta cadastrada com sucesso. Seja bem-vindo(a)!");
        } else {
            System.out.println("[ATENÇÃO] Conta não cadastrada. Dados inválidos.");
        }
    }

    public static void fazerLogin(String numCpf, String senha, Scanner read) {
        boolean loginRealizado = false;
        if(!pessoas.isEmpty()){
            for (Pessoa p : pessoas) {
                if (p.getNumCpf().equals(numCpf) && p.getSenha().equals(senha)) {
                    System.out.println("Bem-vindo(a), " + p.getNome() + "!");
                    mostrarOpcoesConta(p, read);
                    loginRealizado = true;
                    break;
                }
            }
            if (!loginRealizado) {
                System.out.println("[ATENÇÃO] CPF ou senha incorretos.");
            }
        } else {
            System.out.println("Nenhuma pessoa cadastrada");
        }
    }
    private static void mostrarOpcoesConta(Pessoa pessoa, Scanner read) {
        String opcao;
        double limite = pessoa.getSaldo()*3;
        do {
            System.out.println("[ATENÇÃO]: Informações da conta.");
            System.out.println("CPF:" + pessoa.formatCPF(pessoa.getNumCpf()));
            System.out.println("RG:" + pessoa.formatRG(pessoa.getNumRg()));
            System.out.println("Número da agência:" + pessoa.formatAgencia(pessoa.getNumAgencia()));
            System.out.println(("Número da conta: " + pessoa.formatNumeroConta(pessoa.getNumConta())));
            System.out.println("Saldo: " + pessoa.getSaldo());
            System.out.println("Limite: " + limite);
            System.out.println("[1] Depositar");
            System.out.println("[2] Pagar");
            System.out.println("[3] Sacar");
            System.out.println("[4] Sair");
            opcao = read.nextLine();

            switch (opcao) {
                case "1":
                    System.out.println("Digite o valor do depósito: ");
                    double valorDeposito = read.nextDouble();
                    pessoa.setSaldo(pessoa.getSaldo() + valorDeposito);
                    System.out.println("Depósito realizado com sucesso!");
                    read.nextLine();
                    break;
                case "3":
                    System.out.println("Digite o valor do saque: ");
                    double valorSaque = read.nextDouble();
                    if (pessoa.getSaldo() >= valorSaque) {
                        pessoa.setSaldo(pessoa.getSaldo() - valorSaque);
                        System.out.println("Saque realizado com sucesso!");
                    } else {
                        System.out.println("Saldo insuficiente.");
                    }
                    read.nextLine();
                    break;
                case "2":

                    System.out.println("Digite o valor a ser pago");
                    double valorPago = read.nextDouble();
                    read.nextLine();

                    if(valorPago <= limite){
                        pessoa.setSaldo(pessoa.getSaldo() - valorPago);
                        limite -= valorPago;
                    } else {
                        System.out.println("Limite insuficiente.");
                    }

                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (!opcao.equals("4"));
    }
    private static boolean validarNome(String nome){

        String invalidosNome = "1234567890-=!@#$%¨&*()_+´[{]};:~,.<>";
        boolean status = true;

        for (char c : invalidosNome.toCharArray()) {
            if (nome.contains(String.valueOf(c))) {
                status = false;
                break;
            }
        }
        if(status) {
            if(nome.isBlank() || nome.length() < 4) {
                status = false;
            }
        }
        return status;
    }
    private static boolean validarCpf(String numCpf){

        String invalidosNum = "abcdefghijklmnopqrstuvwxyz-=!@#$%¨&*()_+´[{]};:~,.<>";
        boolean status = true;

        for (char c : invalidosNum.toCharArray()) {
            if (numCpf.contains(String.valueOf(c))) {
                status = false;
                break;
            }
        }
        if(status) {
            if(numCpf.isBlank() || numCpf.length() != 11) {
                status = false;
            }
        }
        return status;
    }

    private static boolean validarRg(String numRg){

        String invalidosNum = "abcdefghijklmnopqrstuvwxyz-=!@#$%¨&*()_+´[{]};:~,.<>";
        boolean status = true;

        for (char c : invalidosNum.toCharArray()) {
            if (numRg.contains(String.valueOf(c))) {
                status = false;
            }
        }
        if(status) {
            if(numRg.isBlank() || numRg.length() != 9) {
                status = false;
            }
        }
        return status;
    }

    private static boolean validarSenha(String senha){

        boolean status = true;

        if(senha.length() < 4){
            status = false;
        }

        return status;
    }

    private static boolean validarIdade(String idade){

        boolean status = true;

        if(Integer.parseInt(idade) < 18){
            status = false;
        }
        return status;
    }

    private static boolean validarDados(String nome, String numCpf, String numRg, String idade, String senha) {
        if (validarNome(nome) && validarCpf(numCpf) && validarRg(numRg) && validarSenha(senha) && validarIdade(idade)) {
            return true;
        } else {
            return false;
        }
    }
    public static void telaAdmin(String loginAdmin, String senhaAdmin, Scanner read) {
        if (loginAdmin.equals("admin") && senhaAdmin.equals("admin")) {

            menuAdmin();
            String opc = read.nextLine();

            switch (opc) {
                case "1":
                    System.out.println("Lista dos usuários cadastrados.");
                    for (Pessoa p : pessoas) {
                        System.out.println("Nome: " + p.getNome() + ", CPF: " + Pessoa.formatCPF(p.getNumCpf()) +
                                ", Agência: " + Pessoa.formatAgencia(p.getNumAgencia()) + ", Conta: " + Pessoa.formatNumeroConta(p.getNumConta()) +
                                ", Saldo: R$ " + p.getSaldo());
                    }
                    break;
                case "2":

                    String cpfLocalizado;

                    System.out.println("Você gostaria de alterar: ");
                    System.out.println("[1] Nome");
                    System.out.println("[2] Senha");
                    System.out.println("[3] Número da agência");
                    System.out.println("[4] Número da conta");
                    System.out.println("[5] Voltar para menu principal");
                    String leituraOpc = read.nextLine();
                    switch (leituraOpc) {
                        case "1":
                            System.out.println("Qual CPF da pessoa");
                            cpfLocalizado = read.nextLine();
                            for (Pessoa p : pessoas) {
                                if(p.getNumCpf().equals(cpfLocalizado)) {
                                    System.out.println("Qual novo nome da pessoa");
                                    String novoNome = read.nextLine();

                                    p.setNome(novoNome);

                                    System.out.println("Nome alterado com sucesso!");
                                } else {
                                    System.out.println("Nome não localizado!");
                                }
                            }
                            break;
                        case "2":
                            System.out.println("Qual CPF da pessoa");
                            cpfLocalizado = read.nextLine();

                            for (Pessoa p : pessoas) {
                                if(p.getNumCpf().equals(cpfLocalizado)) {
                                    System.out.println("Qual nova senha da pessoa");
                                    String novaSenha = read.nextLine();

                                    p.setSenha(novaSenha);

                                    System.out.println("Senha alterada com sucesso!");
                                } else {
                                    System.out.println("Senha não alterada!");
                                }
                            }
                            break;
                        case "3":
                            System.out.println("Qual CPF da pessoa");
                            cpfLocalizado = read.nextLine();
                            for (Pessoa p : pessoas) {
                                if(p.getNumCpf().equals(cpfLocalizado)) {
                                    Random rd = new Random();
                                    p.setNumAgencia(String.format("%05d", rd.nextInt(100000)));
                                    System.out.println("Número da agência alterado com sucesso!");
                                } else {
                                    System.out.println("Número da agência não alterado.");
                                }
                            }
                            break;
                        case "4":
                            System.out.println("Qual CPF da pessoa");
                            cpfLocalizado = read.nextLine();
                            for (Pessoa p : pessoas) {
                                if(p.getNumCpf().equals(cpfLocalizado)) {
                                    Random rd = new Random();
                                    p.setNumConta(String.format("%05d", rd.nextInt(100000)));
                                    System.out.println("Número da conta alterado com sucesso!");
                                } else {
                                    System.out.println("Número da conta não alterado.");
                                }
                            }
                        default:
                            System.out.println("Opção inválida.");
                    }
            }
        } else {
            System.out.println("[ATENÇÃO] Login ou senha de administrador incorretos.");
        }
    }

    public static void menuAdmin(){
        System.out.println("Bem-vindo(a) administrador(a)!");
        System.out.println("Você gostaria de: ");
        System.out.println("[1] Listar os usuários cadastrados");
        System.out.println("[2] Alterar as informações dos usuários");
    }
}
