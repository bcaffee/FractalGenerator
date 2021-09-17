public class Main {

    public static void main(String[] args) {
        Generator fractalGen = new Generator();
        new Gui(fractalGen);
        new Drawing(fractalGen);
    }
}
