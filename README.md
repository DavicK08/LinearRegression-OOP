Integrantes
-Jhon David Santamaria Cossio
-Miguel Martinez Gallego
# LinearRegression-OOP
# Implementación de un modelo de Regresión Lineal Múltiple en Java

## Descripción del proyecto
Este repositorio contiene la implementación completa de un modelo de regresión lineal múltiple en Java, desarrollado para la Práctica 3 del curso Programming Languages and Paradigms.  
El proyecto se construyó sin usar librerías externas, siguiendo los principios de programación orientada a objetos.

El programa incluye:
- Carga de datos desde archivos CSV.
- Escalamiento de datos (normalización por media y desviación estándar).
- Entrenamiento mediante descenso del gradiente.
- Predicción de valores.
- Cálculo del error usando MSE.
- Pruebas con datasets de entrenamiento y prueba.

---

## Estructura del repositorio

src/
└── LinearRegression.java

data/
├── train_X.csv
├── train_y.csv
├── test_X.csv
└── test_y.csv

---

## Instrucciones para ejecutar

1. Clonar o descargar el repositorio.
2. Colocar los archivos CSV dentro de la carpeta `data/`.
3. Compilar el programa:

javac src/LinearRegression.java

4. Ejecutar:


---

## Explicación general del código

El modelo se compone de:

### 1. Atributos principales
- `weights`: coeficientes del modelo.
- `bias`: término independiente.
- `means` y `stds`: valores para el escalamiento.
- `learningRate` y `epochs`: hiperparámetros del entrenamiento.

### 2. Métodos implementados

#### Carga de CSV
Se implementaron dos lectores:
- `loadCSV_X`: lee matrices X.
- `loadCSV_y`: lee vectores y.

#### Escalamiento de datos
- `dataScaling`: calcula media y desviación estándar y transforma la matriz.
- `applyScaling`: usa los valores ya calculados para escalar nuevos datos.

#### Entrenamiento
El método `fit()` implementa descenso del gradiente para minimizar el MSE.

#### Predicción
`predict()` aplica los pesos y el sesgo para generar estimaciones.

#### Cálculo del error
`score()` devuelve el error cuadrático medio sobre un conjunto de datos.

---

## Resultados de prueba
Los resultados mostrados al ejecutar el programa incluyen:
- Pesos finales.
- Sesgo final.
- Predicciones sobre el conjunto de prueba.
- Error MSE.

---

## Problemas encontrados y soluciones

1. Escalamiento incorrecto de datos  
   Solución: se aplicó normalización por media y desviación estándar.

2. Desajuste dimensional al leer archivos  
   Solución: se verificó número de columnas y filas antes de procesar.

3. Baja velocidad de entrenamiento  
   Solución: se agregó control de épocas y tasa de aprendizaje.

---

## Conclusiones

1. Es posible implementar un modelo de regresión lineal múltiple solo con Java y sin librerías externas.  
2. El escalamiento de datos es fundamental para el buen desempeño del descenso del gradiente.  
3. La estructura orientada a objetos permite extender y reutilizar fácilmente el modelo.

---
