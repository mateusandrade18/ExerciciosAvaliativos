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
                case 6:
                    rodadaGeral(robos);
                    break;
                case 7:
                    exibirClassificacao(robos);
                    break;
                case 8:
                    exibirEstatisticas(robos);
                    break;
                case 9:
                    excluirRobo(s, robos);
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
   
    public static boolean vemAntes(Robo a, Robo b) {
        if (a.getPontos() != b.getPontos()) {
            return a.getPontos() > b.getPontos();
        }
        if (a.getVitorias() != b.getVitorias()) {
            return a.getVitorias() > b.getVitorias();
        }
        if (a.getEnergia() != b.getEnergia()) {
            return a.getEnergia() > b.getEnergia();
        }
        return a.getCodigo() < b.getCodigo();
    }
   
    public static ArrayList<Robo> ordenarClassificacao(ArrayList<Robo> robos) {
        ArrayList<Robo> copia = new ArrayList<>();
        for (int i = 0; i < robos.size(); i++) {
            copia.add(robos.get(i));
        }

        for (int i = 0; i < copia.size() - 1; i++) {
            int melhor = i;
            for (int j = i + 1; j < copia.size(); j++) {
                if (vemAntes(copia.get(j), copia.get(melhor))) {
                    melhor = j;
                }
            }
            if (melhor != i) {
                Robo aux = copia.get(i);
                copia.set(i, copia.get(melhor));
                copia.set(melhor, aux);
            }
        }
        return copia;
    }
    
    public static void exibirClassificacao(ArrayList<Robo> robos) {
        if (robos.isEmpty()) {
            System.out.println("Nenhum robô cadastrado.");
            return;
        }
        ArrayList<Robo> ranking = ordenarClassificacao(robos);
        System.out.println("=== CLASSIFICAÇÃO ===");
        for (int i = 0; i < ranking.size(); i++) {
            Robo r = ranking.get(i);
            System.out.println((i + 1) + "º | Código: " + r.getCodigo()
                + " | Nome: " + r.getNome()
                + " | Pontos: " + r.getPontos()
                + " | Combates: " + r.getTotalCombates()
                + " | Vitórias: " + r.getVitorias()
                + " | Empates: " + r.getEmpates()
                + " | Derrotas: " + r.getDerrotas()
                + " | Energia: " + r.getEnergia());
        }
    }
    
    public static void rodadaGeral(ArrayList<Robo> robos) {
        if (robos.isEmpty()) {
            System.out.println("Nenhum robô cadastrado.");
            return;
        }
      
        ArrayList<Robo> ranking = ordenarClassificacao(robos);
        ArrayList<Robo> disponiveis = new ArrayList<>();
        for (int i = 0; i < ranking.size(); i++) {
            if (ranking.get(i).estaDisponivel()) {
                disponiveis.add(ranking.get(i));
            }
        }

        if (disponiveis.size() < 2) {
            System.out.println("Rodada recusada: é preciso ter pelo menos 2 robôs disponíveis.");
            return;
        }
        
        ArrayList<Robo> lado1 = new ArrayList<>();
        ArrayList<Robo> lado2 = new ArrayList<>();
        for (int i = 0; i + 1 < disponiveis.size(); i += 2) {
            lado1.add(disponiveis.get(i));
            lado2.add(disponiveis.get(i + 1));
        }
        Robo folga = null;
        if (disponiveis.size() % 2 != 0) {
            folga = disponiveis.get(disponiveis.size() - 1);
        }

        System.out.println("=== RODADA GERAL ===");
        System.out.println("Confrontos definidos:");
        for (int i = 0; i < lado1.size(); i++) {
            System.out.println((i + 1) + ") " + lado1.get(i).getNome() + " x " + lado2.get(i).getNome());
        }
        if (folga != null) {
            System.out.println("Folga: " + folga.getNome());
        }
  
        for (int i = 0; i < lado1.size(); i++) {
            combater(lado1.get(i), lado2.get(i));
        }

        if (folga != null) {
            folga.receberFolga();
            System.out.println(folga.getNome() + " ficou sem adversário e ganhou 1 ponto de folga.");
        }
    }

    public static void exibirEstatisticas(ArrayList<Robo> robos) {
        if (robos.isEmpty()) {
            System.out.println("Nenhum robô cadastrado.");
            return;
        }

        System.out.println("=== ESTATÍSTICAS ===");
        System.out.println("Robôs cadastrados: " + robos.size());

        int soma = 0;
        for (int i = 0; i < robos.size(); i++) {
            soma += robos.get(i).getEnergia();
        }
        double media = (double) soma / robos.size();
        System.out.printf("Média de energia: %.2f%n", media);

        System.out.println("Robôs em recuperação:");
        boolean achou = false;
        for (int i = 0; i < robos.size(); i++) {
            if (!robos.get(i).estaDisponivel()) {
                System.out.println("- " + robos.get(i).getNome() + " (energia " + robos.get(i).getEnergia() + ")");
                achou = true;
            }
        }
        if (!achou) {
            System.out.println("Nenhum.");
        }

       
        Robo melhor = null;
        for (int i = 0; i < robos.size(); i++) {
            Robo r = robos.get(i);
            if (r.getTotalCombates() == 0) {
                continue;
            }
            if (melhor == null
                || r.getVitorias() * melhor.getTotalCombates() > melhor.getVitorias() * r.getTotalCombates()) {
                melhor = r;
            }
        }

        if (melhor == null) {
            System.out.println("Maior aproveitamento: nenhum robô lutou ainda.");
        } else {
            System.out.println("Maior aproveitamento:");
            for (int i = 0; i < robos.size(); i++) {
                Robo r = robos.get(i);
                if (r.getTotalCombates() == 0) {
                    continue;
                }
                if (r.getVitorias() * melhor.getTotalCombates() == melhor.getVitorias() * r.getTotalCombates()) {
                    System.out.printf("- %s: %.2f%% (%d combate(s): %d vitória(s), %d empate(s), %d derrota(s))%n",
                        r.getNome(), r.calcularAproveitamento(), r.getTotalCombates(),
                        r.getVitorias(), r.getEmpates(), r.getDerrotas());
                }
            }
        }
    }

    
    public static void excluirRobo(Scanner s, ArrayList<Robo> robos) {
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
        if (r.getTotalCombates() > 0) {
            System.out.println("Exclusão recusada: " + r.getNome() + " já realizou combate(s).");
            return;
        }
        robos.remove(r);
        System.out.println("Robô " + r.getNome() + " excluído com sucesso.");
    }

}