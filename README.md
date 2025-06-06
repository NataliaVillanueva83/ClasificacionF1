
2. Explique el funcionamiento del código desarrollado.
El código simula una sesión de clasificación de F1 usando programación con threads. Cada piloto corre simultáneamente en su propio thread, recreando el escenario 
real donde varios pilotos pueden estar en pista al mismo tiempo.
Esta la clase piloto, donde se guardan todos los datos de cada piloto. En el array tiemposSectores: cada elemento representa el tiempo que tarda el piloto en completar un sector (son tres sectores).
Luego esta la clase pilotoThread que contiene el tiempoInicio: que es el tiempo común para todos los pilotos, permite sincronizar las mediciones de tiempo; y el tiempoTotal que se va acumulando a medida que se completa cada uno de los sectores.
mainClasificación:  simula la vuelta de clasificacion. 
