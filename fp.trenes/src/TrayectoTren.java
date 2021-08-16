import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public interface TrayectoTren {
    public String getCodigoTren();
    public String getTrayecto();
    public TipoTren getTipoTren();
    public List<String> getEstaciones();
    public List<LocalTime> getHoraSalidas();
    public List<LocalTime> getHoraLlegadas();
    public LocalTime getHoraSalida();
    public LocalTime getHoraLlegada();
    public Duration getDuracion();
    public LocalTime getHoraSalida(String estacion);
    public LocalTime getHoraLlegada(String estacion);
    public void anadirEstacionIntermedia(int posicion, String estacion, LocalTime horaLlegada, LocalTime horaSalida);
    public void eliminarEstacionIntermedia(String estacion);

}
