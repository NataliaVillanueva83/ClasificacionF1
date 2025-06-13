
2. Explique el funcionamiento del código desarrollado.
El código simula una sesión de clasificación de F1 usando programación con threads e interrupciones . Cada piloto corre simultáneamente en su propio thread, recreando el escenario  real donde varios pilotos pueden estar en pista al mismo tiempo.
Esta la clase piloto, donde se guardan todos los datos de cada piloto. En el array tiemposSectores: cada elemento representa el tiempo que tarda el piloto en completar un sector (son tres sectores).
Luego esta la clase pilotoThread que contiene el tiempoInicio: que es el tiempo común para todos los pilotos, permite sincronizar las mediciones de tiempo; y el tiempoTotal que se va acumulando a medida que se completa cada uno de los sectores.
PilotoThread: Extiende Thread para que cada piloto tenga su propio hilo de ejecución.
mainClasificación: Inicia la simulación y crea las instancias de los pilotos. Maneja la concurrencia y espera que todos los hilos terminen.
En run(): Se imprime el inicio de la vuelta. Se simula el tiempo de cada sector. Se acumula el tiempo total y se imprime el progreso. Al final, se imprime el tiempo total de la vuelta.
