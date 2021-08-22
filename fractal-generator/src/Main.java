public class Main {

    public static void main(String[] args) {
        Generator fractalGen = new Generator();
        new GUI(fractalGen);
        new Drawing(fractalGen);
    }
}
