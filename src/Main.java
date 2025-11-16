import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        // Cargar datos de entrenamiento
        LinearRegression.Data train = LinearRegression.loadCSV("student_exam_scores_train.csv");

        // Crear y entrenar modelo
        LinearRegression model = new LinearRegression(0.001, 10000);
        model.fit(train.X, train.y);

        // Cargar datos de testeo
        LinearRegression.Data test = LinearRegression.loadCSV("student_exam_scores_test.csv");

        // Predicciones
        double[] yPred = model.predict(test.X);

        // Imprimir todas las predicciones
        System.out.println("Predicciones del modelo:");
        for (int i = 0; i < yPred.length; i++) {
            System.out.println(i + ". Predicción = " + yPred[i] + ", Valor real = " + test.y[i]);
        }

        // Cálculo del MSE
        double mse = model.score(test.X, test.y);
        System.out.println("\nMSE = " + mse);
    }
}
