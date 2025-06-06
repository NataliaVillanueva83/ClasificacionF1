public class Piloto {
    private String nombre;
    private String escuderia;
    private int[] tiemposSectores;

    //constructor
    public Piloto (String nombre, String escuderia, int[] tiemposSectores) {
        this.nombre = nombre;
        this.escuderia = escuderia;
        this.tiemposSectores = tiemposSectores;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEscuderia() {
        return escuderia;
    }

    public void setEscuderia(String escuderia) {
        this.escuderia = escuderia;
    }

    public int[] getTiemposSectores() {
        return tiemposSectores;
    }

    public void setTiemposSectores(int[] tiemposSectores) {
        this.tiemposSectores = tiemposSectores;
    }
}
