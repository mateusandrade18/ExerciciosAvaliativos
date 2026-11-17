public class RoboEquilibrado extends Robo {

    public RoboEquilibrado(int codigo, String nome, int ataque, int defesa) {
        super(codigo, nome, ataque, defesa);
    }

    @Override
    public String getTipo() {
        return "Equilibrado";
    }
}
