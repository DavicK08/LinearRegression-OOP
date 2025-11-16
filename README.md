# Integrantes:
Jhon David Santamaria Cossio.
Miguel Martinez Gallego.

# Práctica 3 – Regresión Lineal en Java (POO)

Este proyecto implementa un modelo de **regresión lineal múltiple** programado completamente desde cero en Java.  
No usa librerías externas: todo está implementado manualmente (lectura de CSV, escalado, gradiente descendente, predicciones, MSE, etc).

---

##  Estructura del proyecto

```
Regresion-Lineal-Java/
│
├── README.md
│
├── data/
│   ├── student_exam_scores_train.csv
│   ├── student_exam_scores_test.csv
│   ├── Ice_cream_selling_data_train.csv
│   ├── Ice_cream_selling_data_test.csv
│
└── src/
    ├── Main.java
    └── LinearRegression.java
```

---

##  ¿Qué hace el proyecto?

### 1. Lee los CSV (train y test)
El archivo CSV debe tener:
- Varias columnas numéricas
- La **última columna es la variable objetivo y**

### 2. Escala los datos
Se aplica estandarización:

```
z = (x - mean) / std
```

### 3. Entrena un modelo usando **gradiente descendente**
La clase `LinearRegression` calcula:
- Pesos (`weights`)
- Bias (`bias`)
- Actualización de gradientes durante `epochs` épocas

### 4. Predice valores nuevos
Usando la fórmula:

```
y_hat = bias + Σ (wᵢ * xᵢ)
```

### 5. Calcula el MSE
Error cuadrático medio:

```
MSE = (1/n) Σ (y_pred - y_real)²
```

---

## ¿Cómo funciona cada parte del código?

A continuación explicamos de forma clara y sencilla qué hace cada función importante del proyecto y cómo trabaja internamente el modelo.

---

### 1. `loadCSV(String filePath)`
Esta función se encarga de leer un archivo CSV desde la ruta que se le pasa.  
Su funcionamiento es:

1. Abre el archivo usando un `BufferedReader`.
2. Lee la primera línea (encabezado) para saber cuántas columnas tiene.
3. Recorre el resto de líneas para contar cuántas filas de datos hay.
4. Crea:
   - una matriz `X` con todas las columnas excepto la última,
   - un vector `y` con la última columna (la salida).
5. Convierte cada valor desde texto a `double` y llena X e y.

---

### 2. `fit(double[][] X, double[] y)`
Aquí ocurre el entrenamiento usando **gradiente descendente**.

1. Primero escala los datos con `dataScaling()` para que todas las columnas tengan la misma escala.
2. Inicializa todos los pesos en 0 y el bias en 0.
3. Repite durante `epochs`:
   - Calcula la predicción para cada fila.
   - Calcula el error entre la predicción y el valor real.
   - Calcula los gradientes de cada peso.
   - Actualiza los pesos y el bias:

```
w = w - learningRate * gradiente
b = b - learningRate * gradiente
```

La función ajusta poco a poco los pesos hasta que el error disminuye.

---

### 3. `dataScaling(double[][] X)`
Escala todos los datos aplicando **estandarización (Z-score)**:

```
x_scaled = (x - media) / std
```

Para cada columna:
- Calcula su media
- Calcula su desviación estándar
- Reemplaza cada valor por su versión escalada

También guarda las medias y desviaciones en los atributos `mean[]` y `stds[]`.

Esto es esencial para que el entrenamiento funcione correctamente.

---

### 4. `applyScaling(double[][] X)`
Escala los datos nuevos (como el test) **usando las mismas medias y desviaciones del entrenamiento**.

Esto garantiza que el modelo reciba datos con la misma escala que usó al entrenarse.

---

### 5. `predict(double[][] X)`
Sirve para hacer predicciones.

1. Escala la matriz de entrada usando `applyScaling()`.
2. Aplica la fórmula del modelo:

```
y_hat = bias + w1*x1 + w2*x2 + ...
```

3. Devuelve un arreglo con todas las predicciones.

Es la función que usa el modelo ya entrenado para predecir nuevas salidas.

---

### 6. `score(double[][] X, double[] y)`
Calcula el **error cuadrático medio (MSE)**:

```
MSE = (1/n) * Σ (y_pred - y_real)²
```

1. Predice los valores usando `predict()`.
2. Compara cada predicción con el valor real.
3. Suma los errores al cuadrado y divide entre la cantidad de datos.

Un MSE bajo significa que el modelo está funcionando bien.


## ¿Cómo ejecutar?

Dentro de la carpeta raíz:

```
javac src/*.java
java src.Main
```

O desde un IDE asegurando que el working directory sea:

```
Regresion-Lineal-Java/
```

---

## Resultado esperado

El programa imprime:
- Todas las predicciones del modelo
- El valor real del test
- El MSE final

Ejemplo:

```
Predicciones del modelo:
0. Predicción = 31.92, Valor real = 30.1
1. Predicción = 34.61, Valor real = 32.0
...

MSE = 4.82
```

---

## ¿Qué se evaluó?

- Lectura de CSV desde cero  
- Implementación del gradiente descendente  
- Escalado de datos  
- Uso de clases y objetos  
- Predicción y evaluación del modelo  
- Estructura limpia del código  

---

## Conclusión

Este proyecto demuestra cómo implementar un modelo de regresión lineal múltiple desde cero, usando únicamente Java y aplicando conceptos de POO, matemáticas y procesamiento de datos. En lo personal, consideramos que fue el mejor proyecto de la materia en este semestre, se nos hizo muy útil e interesante. Por último, agradecerle al profesor por este semestre, y disculparnos por no haber podido realizar el vídeo.

