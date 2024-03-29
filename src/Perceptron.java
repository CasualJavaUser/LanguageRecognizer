public class Perceptron {
    double bias;
    double[] weights;

    public Perceptron(int size) {
        bias = 1;
        weights = new double[size];
    }

    public double calculate(double[] data) {
        if (data.length != weights.length)
            throw new IllegalArgumentException("Wrong input vector size");

        double sum = 0;
        for (int i = 0; i < data.length; i++) {
            sum += data[i] * weights[i];
        }
        return sum - bias;
    }

    public boolean learn(double[] data, boolean expected, double learningRate) {
        if ((calculate(data) > 0) == expected)
            return true;

        int n = expected ? 1 : -1;
        for (int i = 0; i < data.length; i++) {
            weights[i] += data[i] * (double)n * learningRate;
        }
        bias -= (double)n * learningRate;
        return false;
    }
}
