import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class LinearRegression {

    private double[] weights;
    private double bias;

    private double[] means;
    private double[] stds;

    private double learningRate;
    private int epochs;

    public LinearRegression(double learningRate, int epochs) {
        this.learningRate = learningRate;
        this.epochs = epochs;
    }

    public static double[][] loadCSV_X(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        int rows = 0;
        int cols = 0;

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            cols = parts.length;
            rows++;
        }
        br.close();

        double[][] X = new double[rows][cols];
        br = new BufferedReader(new FileReader(filePath));
        int i = 0;

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            for (int j = 0; j < cols; j++) {
                X[i][j] = Double.parseDouble(parts[j]);
            }
            i++;
        }
        br.close();
        return X;
    }

    public static double[] loadCSV_y(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        int rows = 0;

        while ((line = br.readLine()) != null) rows++;
        br.close();

        double[] y = new double[rows];

        br = new BufferedReader(new FileReader(filePath));
        int i = 0;

        while ((line = br.readLine()) != null) {
            y[i++] = Double.parseDouble(line.trim());
        }
        br.close();
        return y;
    }

    private double[][] dataScaling(double[][] X) {

        int n = X.length;
        int m = X[0].length;

        means = new double[m];
        stds = new double[m];
        double[][] X_scaled = new double[n][m];

        for (int j = 0; j < m; j++) {

            double sum = 0;
            for (int i = 0; i < n; i++) sum += X[i][j];
            means[j] = sum / n;

            double acc = 0;
            for (int i = 0; i < n; i++) {
                double diff = X[i][j] - means[j];
                acc += diff * diff;
            }
            stds[j] = Math.sqrt(acc / n);

            for (int i = 0; i < n; i++) {
                if (stds[j] == 0) X_scaled[i][j] = 0;
                else X_scaled[i][j] = (X[i][j] - means[j]) / stds[j];
            }
        }

        return X_scaled;
    }

    private double[][] applyScaling(double[][] X) {

        int n = X.length;
        int m = X[0].length;

        double[][] X_scaled = new double[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (stds[j] == 0) {
                    X_scaled[i][j] = 0;
                } else {
                    X_scaled[i][j] = (X[i][j] - means[j]) / stds[j];
                }
            }
        }

        return X_scaled;
    }

    public void fit(double[][] X, double[] y) {

        double[][] X_scaled = dataScaling(X);

        int n = X_scaled.length;
        int m = X_scaled[0].length;

        weights = new double[m];
        bias = 0;

        for (int epoch = 0; epoch < epochs; epoch++) {

            double[] gradW = new double[m];
            double gradB = 0;

            for (int i = 0; i < n; i++) {

                double y_hat = bias;
                for (int j = 0; j < m; j++) {
                    y_hat += weights[j] * X_scaled[i][j];
                }

                double error = y_hat - y[i];

                for (int j = 0; j < m; j++) {
                    gradW[j] += error * X_scaled[i][j];
                }
                gradB += error;
            }

            for (int j = 0; j < m; j++) {
                weights[j] -= learningRate * (gradW[j] / n);
            }
            bias -= learningRate * (gradB / n);
        }
    }

    public double[] predict(double[][] X) {

        double[][] X_scaled = applyScaling(X);

        int n = X_scaled.length;
        int m = X_scaled[0].length;

        double[] preds = new double[n];

        for (int i = 0; i < n; i++) {
            double y_hat = bias;

            for (int j = 0; j < m; j++) {
                y_hat += weights[j] * X_scaled[i][j];
            }
            preds[i] = y_hat;
        }

        return preds;
    }

    public double score(double[][] X, double[] y) {

        double[] preds = predict(X);
        int n = y.length;

        double mse = 0;

        for (int i = 0; i < n; i++) {
            double diff = preds[i] - y[i];
            mse += diff * diff;
        }

        return mse / n;
    }

    public double[] getWeights() { return weights; }
    public double getBias() { return bias; }

    public static void main(String[] args) throws IOException {

        double[][] X_train = loadCSV_X("train_X.csv");
        double[] y_train = loadCSV_y("train_y.csv");

        LinearRegression model = new LinearRegression(0.01, 1000);

        model.fit(X_train, y_train);

        System.out.println("Weights: " + Arrays.toString(model.getWeights()));
        System.out.println("Bias: " + model.getBias());

        double[][] X_test = loadCSV_X("test_X.csv");
        double[] preds = model.predict(X_test);

        System.out.println("Predicciones:");
        for (double p : preds) System.out.println("y_hat = " + p);

        double[] y_test = loadCSV_y("test_y.csv");
        System.out.println("MSE: " + model.score(X_test, y_test));
    }
}
