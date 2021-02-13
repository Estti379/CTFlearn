import SimpleProg.LineDecoder;

public class Main {
    //https://ctflearn.com/challenge/174
    public static void main(String[] args) {
        LineDecoder decoder = new LineDecoder("src/data.dat");
        decoder.start();
    }
}
