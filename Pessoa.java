import java.util.Random;

public class Pessoa {
    private String nome;
    private String senha;
    private String numRg;
    private String idade;
    private String numCpf;
    private String numAgencia;
    private String numConta;
    private double saldo;

    // Construtor
    public Pessoa(String nome, String senha, String numRg, String idade, String numCpf) {
        this.nome = nome;
        this.senha = senha;
        this.numRg = numRg;
        this.idade = idade;
        this.numCpf = numCpf;

        Random rd = new Random();
        this.numAgencia = String.format("%05d", rd.nextInt(100000));
        this.numConta = String.format("%06d", rd.nextInt(1000000));
        this.saldo = Math.round(rd.nextDouble(1000, 10000) * 100.0) / 100.0;
    }

    public String getNome() { return nome; }
    public String getSenha() { return senha; }
    public String getNumRg() { return numRg; }
    public String getIdade() { return idade; }
    public String getNumCpf() { return numCpf; }
    public String getNumAgencia() { return numAgencia; }
    public String getNumConta() { return numConta; }
    public double getSaldo() { return saldo; }

    public void setSaldo(double saldo) { this.saldo = saldo; }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setNumRg(String numRg) {
        this.numRg = numRg;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public void setNumCpf(String numCpf) {
        this.numCpf = numCpf;
    }

    public void setNumAgencia(String numAgencia) {
        this.numAgencia = numAgencia;
    }

    public void setNumConta(String numConta) {
        this.numConta = numConta;
    }

    public static String formatCPF(String numCpf) {
        return numCpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    public static String formatRG(String numRg) {
        return numRg.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{1})", "$1.$2.$3-$4");
    }

    public static String formatNumeroConta(String numConta) {
        return numConta.replaceAll("(\\d{4,5})(\\d{1})", "$1-$2");
    }

    public static String formatAgencia(String numAgencia) {
        return numAgencia.replaceAll("(\\d{4})(\\d{1})", "$1-$2");
    }
}
