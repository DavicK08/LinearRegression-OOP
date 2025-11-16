import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LinearRegression {

    // Atributos
    private double[] weights;
    private double bias;
    private double[] mean;
    private double[] stds;
    private double learningRate;
    private int epochs;

    // Constructor
    public LinearRegression(double learningRate, int epochs){
        this.learningRate = learningRate;
        this.epochs = epochs;
    }

    // Clase para almacenar X e y
    public static class Data {
        public double[][] X;
        public double[] y;

        public Data(double[][] X, double[] y){
            this.X = X;
            this.y = y;
        }
    }

    // Lector de CSV con encabezado y última columna = y
    public static Data loadCSV(String filePath) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(filePath));

        String line = br.readLine();  
        if (line == null) throw new IOException("Archivo CSV vacío.");

        String[] headers = line.split(",");
        int cols = headers.length;

        int rows = 0;

        while ((line = br.readLine()) != null){
            if (!line.trim().isEmpty()) rows++;
        }
        br.close();

        double[][] X = new double[rows][cols - 1];
        double[] y = new double[rows];

        br = new BufferedReader(new FileReader(filePath));
        br.readLine(); // Saltar encabezado

        int i = 0;

        while ((line = br.readLine()) != null){
            if (line.trim().isEmpty()) continue;

            String[] parts = line.split(",");
            if (parts.length != cols)
                throw new IOException("Fila con número incorrecto de columnas en: " + line);

            for (int j = 0; j < cols - 1; j++){
                X[i][j] = Double.parseDouble(parts[j]);
            }

            y[i] = Double.parseDouble(parts[cols - 1]);
            i++;
        }

        br.close();
        return new Data(X, y);
    }

    // Ajuste del modelo
    public void fit(double[][] X, double[] y){

        double[][] Xs = dataScaling(X);

        int n = Xs.length;
        int m = Xs[0].length;

        weights = new double[m];
        bias = 0;

        for (int epoch = 0; epoch < epochs; epoch++){

            double[] gradW = new double[m];
            double gradB = 0;

            for (int i = 0; i < n; i++){

                double y_hat = bias;
                for (int j = 0; j < m; j++){
                    y_hat += weights[j] * Xs[i][j];
                }

                double error = y_hat - y[i];

                for (int j = 0; j < m; j++){
                    gradW[j] += error * Xs[i][j];
                }

                gradB += error;
            }

            for (int j = 0; j < m; j++){
                weights[j] -= learningRate * (gradW[j] / n);
            }

            bias -= learningRate * (gradB / n);
        }
    }

    // Escalado de datos
    private double[][] dataScaling(double[][] X){

        int n = X.length;
        int m = X[0].length;

        mean = new double[m];
        stds = new double[m];

        double[][] Xs = new double[n][m];

        // Medias
        for (int j = 0; j < m; j++){
            double sum = 0;
            for (int i = 0; i < n; i++){
                sum += X[i][j];
            }
            mean[j] = sum / n;
        }

        // Desviaciones estándar (corregido a n-1 para evitar std=0 en muestras pequeñas)
        for (int j = 0; j < m; j++){
            double acc = 0;
            for (int i = 0; i < n; i++){
                acc += Math.pow(X[i][j] - mean[j], 2);
            }
            stds[j] = Math.sqrt(acc / (n - 1));
            if (stds[j] == 0) stds[j] = 1e-8; // Evitar división por 0
        }

        // Escalado
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                Xs[i][j] = (X[i][j] - mean[j]) / stds[j];
            }
        }

        return Xs;
    }

    // Aplicación de escalado a nuevos datos
    private double[][] applyScaling(double[][] X){

        int n = X.length;
        int m = X[0].length;

        double[][] X_scaled = new double[n][m];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                X_scaled[i][j] = (X[i][j] - mean[j]) / stds[j];
            }
        }

        return X_scaled;
    }

    // Predicción
    public double[] predict(double[][] X){

        double[][] Xs = applyScaling(X);

        int n = Xs.length;
        int m = Xs[0].length;

        double[] preds = new double[n];

        for (int i = 0; i < n; i++){
            double y_hat = bias;

            for (int j = 0; j < m; j++){
                y_hat += weights[j] * Xs[i][j];
            }

            preds[i] = y_hat;
        }

        return preds;
    }

    // MSE (Mean Squared Error)
    public double score(double[][] X, double[] y){

        double[] preds = predict(X);
        int n = y.length;

        double mse = 0;

        for (int i = 0; i < n; i++){
            mse += Math.pow(preds[i] - y[i], 2);
        }

        return mse / n;
    }
}
