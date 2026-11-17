public class RoboOfensivo extends Robo {

    public RoboOfensivo(int codigo, String nome, int ataque, int defesa) {
        super(codigo, nome, ataque, defesa);
    }

    @Override
    public String getTipo() {
        return "Ofensivo";
    }

    @Override
    public int calcularAtaque() {
        return getAtaque() + 5;
    }
}
