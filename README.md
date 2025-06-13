
2. Explique el funcionamiento del código desarrollado.
El código simula una sesión de clasificación de F1 usando programación con threads e interrupciones . Cada piloto corre simultáneamente en su propio thread, recreando el escenario  real donde varios pilotos pueden estar en pista al mismo tiempo.
Esta la clase piloto, donde se guardan todos los datos de cada piloto. En el array tiemposSectores: cada elemento representa el tiempo que tarda el piloto en completar un sector (son tres sectores).
Luego esta la clase pilotoThread que contiene el tiempoInicio: que es el tiempo común para todos los pilotos, permite sincronizar las mediciones de tiempo; y el tiempoTotal que se va acumulando a medida que se completa cada uno de los sectores.
PilotoThread: Extiende Thread para que cada piloto tenga su propio hilo de ejecución.
mainClasificación: Inicia la simulación y crea las instancias de los pilotos. Maneja la concurrencia y espera que todos los hilos terminen.
En run(): Se imprime el inicio de la vuelta. Se simula el tiempo de cada sector. Se acumula el tiempo total y se imprime el progreso. Al final, se imprime el tiempo total de la vuelta.



4. Analiza y evalúa la implementación del código realizado en el punto 1
implementado en otro lenguaje diferente.

===================
THREADS EN PYTHON
===================

import threading
import time

class Piloto:
    def __init__(self, nombre, escuderia, tiempos_sectores):
        self.nombre = nombre
        self.escuderia = escuderia
        self.tiempos_sectores = tiempos_sectores

class PilotoThread(threading.Thread):
    def __init__(self, piloto, tiempo_inicio):
        super().__init__()
        self.piloto = piloto
        self.tiempo_inicio = tiempo_inicio
        self.tiempo_total = 0.0

    def run(self):
        print(f"El piloto {self.piloto.nombre} ({self.piloto.escuderia}) INICIA su vuelta de clasificación - Tiempo: {time.time() - self.tiempo_inicio:.2f}s")

        for i, tiempo in enumerate(self.piloto.tiempos_sectores):
            try:
                # Simulamos una falla en el sector 2 de Max Verstappen
                if self.piloto.nombre == "Max Verstappen" and i == 1:
                    raise Exception("Fallo en el sistema de frenos")

                self.simular_sector(tiempo)
                self.tiempo_total += tiempo / 10.0

                print(f"{self.piloto.nombre} completa SECTOR {i + 1} - Tiempo sector: {tiempo / 10.0:.1f}s"
                      f" - Tiempo acumulado: {self.tiempo_total:.1f}s"
                      f" - Tiempo real: {time.time() - self.tiempo_inicio:.2f}s")
            except Exception as e:
                print(f"🚨 {self.piloto.nombre} ha abandonado la clasificación: {e}")
                return

        print(f"{self.piloto.nombre} ({self.piloto.escuderia}) Completa su vuelta - TIEMPO TOTAL: {self.tiempo_total:.3f}s"
              f" - Tiempo real transcurrido: {time.time() - self.tiempo_inicio:.2f}s")

    def simular_sector(self, decimas):
        time.sleep((decimas * 0.1))  # décimas a segundos

# Código principal
if __name__ == "__main__":
    print("INICIA LA CLASIFICACIÓN")
    tiempo_inicio = time.time()

    piastri = Piloto("Oscar Piastri", "McLaren", [252, 318, 290])
    verstappen = Piloto("Max Verstappen", "Red Bull", [245, 315, 275])
    leclerc = Piloto("Charles Leclerc", "Ferrari", [248, 325, 285])

    hilo_piastri = PilotoThread(piastri, tiempo_inicio)
    hilo_verstappen = PilotoThread(verstappen, tiempo_inicio)
    hilo_leclerc = PilotoThread(leclerc, tiempo_inicio)

    hilo_piastri.start()
    hilo_verstappen.start()
    hilo_leclerc.start()

    hilo_piastri.join()
    hilo_verstappen.join()
    hilo_leclerc.join()



5. Lo ves muy diferentes?. Cuál te resulto más fácil de entender?.
   Sí, son diferentes. Me es más fácil java, por una cuestión de que estoy estudiando dicho lenguaje, pero a simple vista se puede observar que la sintaxis en pytthon es mucho más sencilla. En las clases no hay una declaración de los atributos por fuera del constructor, sino que directamente se declara en el constructor con la palabara reservada self.
6. Los lenguajes que utilizaste implementan thread realmente?. Como lo hacen?.
   Java implementa hilos del sistema operativo a través de la Java Virtual Machine , que se apoya en las bibliotecas nativas del sistema operativo, como pthreadsen Linux o CreateThreaden Windows. Java permite el paralelismo real : distintos hilos pueden ejecutarse realmente al mismo tiempo en múltiples núcleos del procesador.
   Python implementa hilos a nivel de lenguaje mediante la clase threading.Thread, que también se mapea a hilos del sistema operativo. Pero no ejecuta código en paralelo, no con código Python puro (CPython) . La causa el GIL (Global Interpreter Lock) , un candado que impide que más de un hilo de Python interprete bytecode al mismo tiempo , incluso en CPUs con múltiples núcleos. Por eso, los threads en Python no aprovechan múltiples núcleos para código CPU-bound , pero sí sirven para tareas I/O-bound (esperas de red, archivos, etc.). Alternativas posibles: 

multiprocessing: crea procesos separados sin GIL (paralelismo real).

asyncio: usa concurrencia basada en eventos, sin hilos.
