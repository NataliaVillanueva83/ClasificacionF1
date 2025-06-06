public class mainClasificacion {
    public static void main(String[] args) {
        System.out.println("INICIA LA CLASIFICACIÓN");
        long tiempoReferencia = System.currentTimeMillis();

        Piloto piastri = new Piloto("Oscar Piastri", "McLaren", new int[]{252, 318, 290});
        Piloto verstappen = new Piloto("Max Verstappen", "Red Bull", new int[]{245, 315, 275});
        Piloto leclerc = new Piloto("Charles Leclerc", "Ferrari", new int[]{248, 325, 285});

        PilotoThread threadVerstappen = new PilotoThread("VER", verstappen, tiempoReferencia);
        PilotoThread threadLeclerc = new PilotoThread("LEC", leclerc, tiempoReferencia);
        PilotoThread threadPiastri = new PilotoThread("PIA", piastri, tiempoReferencia);

        threadVerstappen.start();
        threadLeclerc.start();
        threadPiastri.start();

        try {

            threadVerstappen.join();
            threadLeclerc.join();
            threadPiastri.join();

        } catch (InterruptedException e) {
            System.out.println("Clasificación interrumpida");
        }
    }



}
