
import java.time.LocalTime;
import java.util.List;

public class TestTrayectoTren {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        LocalTime horaSalida = LocalTime.of (07, 00);
        LocalTime horaLlegada = LocalTime.of (10, 02);
        TrayectoTrenImpl trayecto1 = new TrayectoTrenImpl("02471", "SEVILLA-MADRID", TipoTren.AV_CITY, "Sevilla", "Madrid", horaSalida, horaLlegada);
        System.out.println (trayecto1);
        LocalTime horaLLegadaP = LocalTime.of (8, 40);
        LocalTime horaSalidaP = LocalTime.of (8,41);
        trayecto1.anadirEstacionIntermedia (1, "Puertollano", horaLLegadaP, horaSalidaP);
        System.out.println(trayecto1);
        LocalTime horaLLegadaC = LocalTime.of (7, 45);
        LocalTime horaSalidaC = LocalTime.of (7,50);
        trayecto1.anadirEstacionIntermedia (1, "Córdoba", horaLLegadaC, horaSalidaC);
        System.out.println(trayecto1);
    }
}
