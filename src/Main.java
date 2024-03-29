import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    static final int SIZE = 'z' - 'a';

    private static double[] english = new double[SIZE];
    private static double[] spanish = new double[SIZE];
    private static double[] polish  = new double[SIZE];

    public static void main(String[] args) {
        double learningRate = Double.parseDouble(args[0]);

        loadData("train_data/eng.txt", english);
        loadData("train_data/esp.txt", spanish);
        loadData("train_data/pol.txt", polish);

        NeuralNetwork nn = new NeuralNetwork(3, SIZE);
        for (int i = 0; i < 1000; i++) {
            nn.learn(english, 0, learningRate);
            nn.learn(spanish, 1, learningRate);
            nn.learn(polish, 2, learningRate);
        }

        loadData("test_data/eng.txt", english);
        loadData("test_data/esp.txt", spanish);
        loadData("test_data/pol.txt", polish);

        System.out.println(interpret(nn.calculate(english)));
        System.out.println(interpret(nn.calculate(spanish)));
        System.out.println(interpret(nn.calculate(polish)));
    }

    private static void loadData(String path, double[] array) {
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            int c;
            int count = 0;
            while ((c = in.read()) != -1) {
                if (Character.isAlphabetic(c))
                    c = Character.toLowerCase(c);

                int index = c - 'a';
                if (index >= 0 && index < array.length) {
                    array[index]++;
                    count++;
                }
            }
            for (int i = 0; i < array.length; i++) {
                array[i] /= count;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private static String interpret(int result) {
        return switch (result) {
            case 0 -> "english";
            case 1 -> "spanish";
            case 2 -> "polish";
            default -> null;
        };
    }
}
