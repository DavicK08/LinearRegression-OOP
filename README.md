# Integrantes.
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

Este proyecto demuestra cómo implementar un modelo de regresión lineal múltiple desde cero, usando únicamente Java y aplicando conceptos de POO, matemáticas y procesamiento de datos. En lo personal, creemos que fue el mejor proyecto de la materia en este semestre, se nos hizo muy útil e interesante. Por último, agradecerle al profesor por este semestre, y disculparnos por no haber podido realizar el vídeo.

