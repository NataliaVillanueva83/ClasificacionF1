public class PilotoThread extends Thread{
    private String nombrePiloto;
    private Piloto piloto;
    private long tiempoInicio;
    private double tiempoTotal;

    //constructor
    public PilotoThread(String nombrePiloto, Piloto piloto, long tiempoInicio) {
        this.nombrePiloto = nombrePiloto;
        this.piloto = piloto;
        this.tiempoInicio = tiempoInicio;
        this.tiempoTotal = 0;
    }
    //getters y setters
    public String getNombrePiloto() {
        return nombrePiloto;
    }

    public void setNombrePiloto(String nombrePiloto) {
        this.nombrePiloto = nombrePiloto;
    }

    public Piloto getPiloto() {
        return piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    public double getTiempoTotal() {
        return tiempoTotal;
    }

    public void setTiempoTotal(double tiempoTotal) {
        this.tiempoTotal = tiempoTotal;
    }

    public long getTiempoInicio() {
        return tiempoInicio;
    }

    public void setTiempoInicio(long tiempoInicio) {
        this.tiempoInicio = tiempoInicio;
    }
 @Override
    public void run() {
        System.out.println("El piloto " + this.piloto.getNombre() + " (" + this.piloto.getEscuderia() +
                ") INICIA su vuelta de clasificación - Tiempo: " +
                (System.currentTimeMillis() - this.tiempoInicio) / 1000.0 + "s");

        try {
            for (int i = 0; i < this.piloto.getTiemposSectores().length; i++) {
                // Simular excepción en el segundo sector del piloto Max Verstappen
                if (this.piloto.getNombre().equals("Max Verstappen") && i == 1) {
                    throw new RuntimeException("Fallo en el sistema de frenos");
                }

                this.simularSector(this.piloto.getTiemposSectores()[i]);
                this.tiempoTotal += this.piloto.getTiemposSectores()[i] / 10.0;

                System.out.println(this.piloto.getNombre() +
                        " completa SECTOR " + (i + 1) +
                        " - Tiempo sector: " + (this.piloto.getTiemposSectores()[i] / 10.0) + "s" +
                        " - Tiempo acumulado: " + String.format("%.1f", this.tiempoTotal) + "s" +
                        " - Tiempo real: " + (System.currentTimeMillis() - this.tiempoInicio) / 1000.0 + "s");
            }

            System.out.println(this.piloto.getNombre() + " (" + this.piloto.getEscuderia() +
                    ") Completa su vuelta - TIEMPO TOTAL: " + String.format("%.3f", this.tiempoTotal) + "s" +
                    " - Tiempo real transcurrido: " + (System.currentTimeMillis() - this.tiempoInicio) / 1000.0 + "s");

        } catch (RuntimeException e) {
            System.out.println("🚨 " + this.piloto.getNombre() + " ha abandonado la clasificación: " + e.getMessage());
        }
    }


    
        private void simularSector(int decimas) {
            try {
                Thread.sleep((decimas * 100) / 10);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                System.out.println( this.piloto.getNombre() + " ha sido interrumpido durante el sector. No puede completar" +
                    "la vuelta de clasificación");
        }
    }
}
