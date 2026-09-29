package Exercicio_avaliativo;

public class Robo {

    private int codigo;
    private String nome;
    private int ataque;
    private int defesa;
    private int energia;
    private int vitorias;
    private int derrotas;
    private int empates;
    private int pontos;

    public Robo(int codigo, String nome, int ataque, int defesa) {
        if (codigo <= 0) {
            throw new IllegalArgumentException("O código deve ser positivo.");
        }
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }
        if (ataque < 10 || ataque > 30) {
            throw new IllegalArgumentException("O ataque deve estar entre 10 e 30.");
        }
        if (defesa < 0 || defesa > 20) {
            throw new IllegalArgumentException("A defesa deve estar entre 0 e 20.");
        }
        this.codigo = codigo;
        this.nome = nome.trim();
        this.ataque = ataque;
        this.defesa = defesa;
        this.energia = 100;
        this.vitorias = 0;
        this.derrotas = 0;
        this.empates = 0;
        this.pontos = 0;
    }

    public int getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public int getAtaque() { return ataque; }
    public int getDefesa() { return defesa; }
    public int getEnergia() { return energia; }
    public int getVitorias() { return vitorias; }
    public int getDerrotas() { return derrotas; }
    public int getEmpates() { return empates; }
    public int getPontos() { return pontos; }

    
    public boolean estaDisponivel() {
        return energia >= 30;
    }

    public int getTotalCombates() {
        return vitorias + derrotas + empates;
    }

    public double calcularAproveitamento() {
        if (getTotalCombates() == 0) {
            return 0;
        }
        return (double) vitorias / getTotalCombates() * 100;
    }

    public void receberDano(int dano) {
        energia -= dano;
        if (energia < 0) {
            energia = 0;
        }
    }

    public void registrarVitoria() {
        vitorias++;
        pontos += 3;
    }

    public void registrarDerrota() {
        derrotas++;
    }

    public void registrarEmpate() {
        empates++;
        pontos += 1; 
    }

    public void receberFolga() {
        pontos += 1;
    }
  
    public void recuperarEnergia(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser um número positivo.");
        }
        if (quantidade % 10 != 0) {
            throw new IllegalArgumentException("A quantidade deve ser múltiplo de 10.");
        }
        int custo = quantidade / 10;
        if (custo > pontos) {
            throw new IllegalArgumentException(
                "Pontos insuficientes. Custo: " + custo + " ponto(s), disponível: " + pontos + ".");
        }
        if (energia + quantidade > 100) {
            throw new IllegalArgumentException(
                "A energia final não pode ultrapassar 100. Energia atual: " + energia + ".");
        }

        pontos -= custo;
        energia += quantidade;
    }
    
}       