public class NeuralNetwork {
    private final Perceptron[] perceptrons;
    final int vectorSize;

    public NeuralNetwork(int perceptrons, int vectorSize) {
        this.perceptrons = new Perceptron[perceptrons];
        this.vectorSize = vectorSize;
        for (int i = 0; i < perceptrons; i++) {
            this.perceptrons[i] = new Perceptron(vectorSize);
        }
    }

    public int calculate(double[] data) {
        if (data.length != vectorSize)
            throw new IllegalArgumentException("Wrong input vector size");

        double max = 0;
        int index = -1;
        for (int i = 0; i < perceptrons.length; i++) {
            double net = perceptrons[i].calculate(data);
            if (net > max) {
                index = i;
                max = net;
            }
            System.out.println("p" + i + " net: " + net);
        }
        return index;
    }

    public boolean learn(double[] data, int expected, double learningRate) {
        if (data.length != vectorSize)
            throw new IllegalArgumentException("Wrong input vector size");

        if (expected > perceptrons.length-1 || expected < 0)
            throw new IllegalArgumentException();

        boolean hasLearned = true;
        for (int i = 0; i < perceptrons.length; i++) {
            boolean exp = expected == i;
            if (!perceptrons[i].learn(data, exp, learningRate))
                hasLearned = false;
        }
        return hasLearned;
    }
}
