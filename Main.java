package Exercicio_avaliativo;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList<Robo> robos = new ArrayList<>();
        int opt = -1;

        do {
            System.out.println("==== Robotolandia ====");
            System.out.println("0. Sair");
            System.out.println("1. Cadastrar robô");
            System.out.println("2. Buscar robô por código");
            System.out.println("3. Listar robôs");
            System.out.println("4. Realizar combate");
            System.out.println("5. Recuperar energia");
            System.out.println("6. Rodada geral");
            System.out.println("7. Classificação");
            System.out.println("8. Estatísticas");
            System.out.println("9. Excluir participante");
            System.out.println("Digite a operação: ");
            opt = buscarOperacao(s);

            switch (opt) {
                case 0:
                    System.out.println("Adeus!");
                    break;
                case 1:
                    cadastrarRobo(s, robos);
                    break;
                case 2:
                    consultarRobo(s, robos);
                    break;
                case 3:
                    listarRobos(robos);
                    break;
                case 4:
                    realizarCombate(s, robos);
                    break;
                case 5:
                    recuperarEnergiaRobo(s, robos);
                    break;
                
                default:
                    System.out.println("Operação inválida.");
                    break;
            }
        } while (opt != 0);

        s.close();
    }

    public static int buscarOperacao(Scanner s) {
        try {
            return Integer.parseInt(s.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
   
    public static int lerInteiro(Scanner s, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String linha = s.nextLine();
            try {
                return Integer.parseInt(linha.trim());
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite um número inteiro.");
            }
        }
    }
  
    public static Robo buscarPorCodigo(ArrayList<Robo> robos, int codigo) {
        for (int i = 0; i < robos.size(); i++) {
            if (robos.get(i).getCodigo() == codigo) {
                return robos.get(i);
            }
        }
        return null;
    }

    public static void cadastrarRobo(Scanner s, ArrayList<Robo> robos) {
        int codigo = lerInteiro(s, "Digite o código do robô: ");

        if (codigo <= 0) {
            System.out.println("Erro: o código deve ser positivo.");
            return;
        }
        if (buscarPorCodigo(robos, codigo) != null) {
            System.out.println("Erro: já existe um robô com esse código.");
            return;
        }

        System.out.print("Digite o nome do robô: ");
        String nome = s.nextLine();

        int ataque = lerInteiro(s, "Digite o valor do ataque (10 a 30): ");
        int defesa = lerInteiro(s, "Digite o valor da defesa (0 a 20): ");

        try {
            Robo novo = new Robo(codigo, nome, ataque, defesa);
            robos.add(novo);
            System.out.println("Robô cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
   
    public static void consultarRobo(Scanner s, ArrayList<Robo> robos) {
        if (robos.isEmpty()) {
            System.out.println("Nenhum robô cadastrado.");
            return;
        }
        int codigo = lerInteiro(s, "Digite o código do robô: ");
        Robo r = buscarPorCodigo(robos, codigo);
        if (r == null) {
            System.out.println("Robô não encontrado.");
        } else {
            exibirRobo(r);
        }
    }

    public static void exibirRobo(Robo r) {
        System.out.println(
            "Código: " + r.getCodigo() +
            " | Nome: " + r.getNome() +
            " | Ataque: " + r.getAtaque() +
            " | Defesa: " + r.getDefesa() +
            " | Energia: " + r.getEnergia() +
            " | Vitórias: " + r.getVitorias() +
            " | Derrotas: " + r.getDerrotas() +
            " | Empates: " + r.getEmpates() +
            " | Pontos: " + r.getPontos() +
            " | Situação: " + (r.estaDisponivel() ? "Disponível" : "Em recuperação")
        );
    }
  
    public static void listarRobos(ArrayList<Robo> robos) {
        if (robos.isEmpty()) {
            System.out.println("Nenhum robô cadastrado.");
            return;
        }
        for (int i = 0; i < robos.size(); i++) {
            exibirRobo(robos.get(i));
        }
    }

    public static void realizarCombate(Scanner s, ArrayList<Robo> robos) {
        if (robos.size() < 2) {
            System.out.println("Erro: é preciso ter pelo menos 2 robôs cadastrados.");
            return;
        }
        int codigo1 = lerInteiro(s, "Digite o código do primeiro robô: ");
        int codigo2 = lerInteiro(s, "Digite o código do segundo robô: ");

        if (codigo1 == codigo2) {
            System.out.println("Erro: os robôs devem ser diferentes.");
            return;
        }
        Robo a = buscarPorCodigo(robos, codigo1);
        Robo b = buscarPorCodigo(robos, codigo2);

        if (a == null || b == null) {
            System.out.println("Erro: um dos códigos não existe.");
            return;
        }
        if (!a.estaDisponivel() || !b.estaDisponivel()) { 
            System.out.println("Erro: ambos os robôs precisam ter pelo menos 30 de energia.");
            return;
        }
        combater(a, b);
    }
  
    public static void combater(Robo a, Robo b) {
  
        Robo primeiro;
        Robo segundo;
        if (a.getPontos() < b.getPontos()) {
            primeiro = a;
            segundo = b;
        } else if (b.getPontos() < a.getPontos()) {
            primeiro = b;
            segundo = a;
        } else if (a.getCodigo() < b.getCodigo()) {
            primeiro = a;
            segundo = b;
        } else {
            primeiro = b;
            segundo = a;
        }

        System.out.println("=== COMBATE: " + a.getNome() + " x " + b.getNome() + " ===");
        System.out.println("Ataca primeiro: " + primeiro.getNome());

        for (int rodada = 1; rodada <= 5; rodada++) {
            System.out.println("--- Rodada " + rodada + " ---");

            atacar(primeiro, segundo, rodada);
            if (segundo.getEnergia() == 0) {
                break; 
            }

            atacar(segundo, primeiro, rodada);
            if (primeiro.getEnergia() == 0) {
                break;
            }
        }

        Robo vencedor = null;
        Robo perdedor = null;
        if (primeiro.getEnergia() == 0) {
            vencedor = segundo;
            perdedor = primeiro;
        } else if (segundo.getEnergia() == 0) {
            vencedor = primeiro;
            perdedor = segundo;
        } else if (primeiro.getEnergia() > segundo.getEnergia()) {
            vencedor = primeiro;
            perdedor = segundo;
        } else if (segundo.getEnergia() > primeiro.getEnergia()) {
            vencedor = segundo;
            perdedor = primeiro;
        }

        if (vencedor == null) {
            primeiro.registrarEmpate();
            segundo.registrarEmpate();
            System.out.println("Resultado: EMPATE (1 ponto para cada).");
        } else {
            vencedor.registrarVitoria();
            perdedor.registrarDerrota();
            System.out.println("Resultado: " + vencedor.getNome() + " venceu! (+3 pontos)");
        }
        System.out.println(a.getNome() + " terminou com " + a.getEnergia() + " de energia.");
        System.out.println(b.getNome() + " terminou com " + b.getEnergia() + " de energia.");
    }

    public static void atacar(Robo atacante, Robo defensor, int rodada) {
        int dano = atacante.getAtaque() - defensor.getDefesa();
        if (dano < 5) {
            dano = 5;
        }
        if (rodada % 2 == 0) {
            dano += 5;
        }
        defensor.receberDano(dano);
        System.out.println(atacante.getNome() + " ataca " + defensor.getNome()
            + " e causa " + dano + " de dano. Energia de "
            + defensor.getNome() + ": " + defensor.getEnergia());
    }
        // Pede o código e a quantidade, e tenta recuperar a energia (case 5).
    public static void recuperarEnergiaRobo(Scanner s, ArrayList<Robo> robos) {
        if (robos.isEmpty()) {
            System.out.println("Nenhum robô cadastrado.");
            return;
        }
        int codigo = lerInteiro(s, "Digite o código do robô: ");
        Robo r = buscarPorCodigo(robos, codigo);
        if (r == null) {
            System.out.println("Robô não encontrado.");
            return;
        }
        int quantidade = lerInteiro(s, "Digite a quantidade de energia (múltiplo de 10): ");

        try {
            r.recuperarEnergia(quantidade);
            System.out.println("Recuperação aceita! Energia atual: " + r.getEnergia()
                + " | Pontos restantes: " + r.getPontos());
        } catch (IllegalArgumentException e) {
            System.out.println("Recuperação recusada: " + e.getMessage());
        }
    }
    
}