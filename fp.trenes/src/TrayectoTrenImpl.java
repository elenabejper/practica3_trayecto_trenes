
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TrayectoTrenImpl implements TrayectoTren {

    //Atributos
    private String codigoTren;
    private String trayecto;
    private TipoTren tipoTren;
    private List<String> estaciones;
    private List<LocalTime> horaSalidas;
    private List<LocalTime> horaLlegadas;

    //Constructor
    public TrayectoTrenImpl(String codigoTren, String trayecto, TipoTren tipoTren, String estacionOrigen, String estacionFinal, LocalTime horaSalida, LocalTime horaLlegada) {
        if (codigoTren.length() != 5) throw new IllegalArgumentException("El código debe tener menos de 5 dígitos");
        this.codigoTren = codigoTren;
        this.trayecto = trayecto;
        this.tipoTren = tipoTren;
        if (horaSalida == null) throw new IllegalArgumentException("La hora de salida no puede ser nula");
        if (horaLlegada == null) throw new IllegalArgumentException("La hora de llegada no puede ser nula");
        if (!horaSalida.isBefore(horaLlegada)) throw new IllegalArgumentException("La hora de salida debe ser anterior a la hora de llegada"); // ! es la negación. También se podría poner sin negación cambiando a isAfter()
        this.estaciones = new ArrayList<>();
        estaciones.add(estacionOrigen);
        estaciones.add(estacionFinal);
        this.horaSalidas = new ArrayList<>();
        this.horaLlegadas = new ArrayList<>();
        horaSalidas.add(horaSalida);
        horaLlegadas.add(horaLlegada);
    }

    //Getters y propiedades derivadas
    public String getCodigoTren() {
        return codigoTren;
    }

    public String getTrayecto() {
        return trayecto;
    }

    public TipoTren getTipoTren() {
        return tipoTren;
    }

    public List<String> getEstaciones() {
        return estaciones;
    }

    public List<LocalTime> getHoraSalidas() {
        return horaSalidas;
    }

    public List<LocalTime> getHoraLlegadas() {
        return horaLlegadas;
    }

    public LocalTime getHoraSalida() {
        return horaSalidas.get(0);
    }

    public LocalTime getHoraLlegada() {
        return horaLlegadas.get(-1); //ALTERNATIVO: horaLlegadas.get(horaLlegadas.size()-1);
    }

    public Duration getDuracion() {
        return Duration.between(getHoraSalida(), getHoraLlegada());
    }

    public LocalTime getHoraSalida(String estacion) {
        Integer posicion = estaciones.indexOf(estacion);
        if (posicion == -1) return null;
        return horaSalidas.get(posicion);
    }

    public LocalTime getHoraLlegada(String estacion) {
        Integer posicion = estaciones.indexOf(estacion);
        if (posicion == -1) return null;
        return horaLlegadas.get(posicion);
    }

    public void anadirEstacionIntermedia(int posicion, String estacion, LocalTime horaLlegada, LocalTime horaSalida) {
        if (posicion == 0 || posicion == -1) throw new IllegalArgumentException("La posición debe de ser intermedia");
        if (!horaSalida.isAfter(horaLlegada)) throw new IllegalArgumentException("La hora de llegada debe ser posterior a la de salida");

        estaciones.add(posicion, estacion);
        horaSalidas.add(posicion, horaSalida);
        horaLlegadas.add(posicion, horaLlegada);
    }

    public void eliminarEstacionIntermedia(String estacion) {
        int posicion = estaciones.indexOf(estacion);
        if (posicion == 0 || posicion == -1) throw new IllegalArgumentException("No se puede eliminar ni la primera ni la última estación");
        if (!estaciones.contains(estacion)) throw new IllegalArgumentException("La estación no se encuentra en el trayecto");

        horaLlegadas.remove(estacion);
        horaSalidas.remove(estacion);
        estaciones.remove(estacion);
    }

    //hashCode y equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrayectoTrenImpl)) return false;

        TrayectoTrenImpl that = (TrayectoTrenImpl) o;

        if (codigoTren != null ? !codigoTren.equals (that.codigoTren) : that.codigoTren != null) return false;
        if (trayecto != null ? !trayecto.equals (that.trayecto) : that.trayecto != null) return false;
        if (tipoTren != that.tipoTren) return false;
        if (estaciones != null ? !estaciones.equals (that.estaciones) : that.estaciones != null) return false;
        if (horaSalidas != null ? !horaSalidas.equals (that.horaSalidas) : that.horaSalidas != null) return false;
        return horaLlegadas != null ? horaLlegadas.equals (that.horaLlegadas) : that.horaLlegadas == null;
    }

    @Override
    public int hashCode() {
        int result = codigoTren != null ? codigoTren.hashCode () : 0;
        result = 31 * result + (trayecto != null ? trayecto.hashCode () : 0);
        result = 31 * result + (tipoTren != null ? tipoTren.hashCode () : 0);
        result = 31 * result + (estaciones != null ? estaciones.hashCode () : 0);
        result = 31 * result + (horaSalidas != null ? horaSalidas.hashCode () : 0);
        result = 31 * result + (horaLlegadas != null ? horaLlegadas.hashCode () : 0);
        return result;
    }

    //toString
    @Override
    public String toString() {
        return "TrayectoTrenImpl{" +
                "codigoTren='" + codigoTren + '\'' +
                ", trayecto='" + trayecto + '\'' +
                ", tipoTren=" + tipoTren +
                ", estaciones=" + estaciones +
                ", horaSalidas=" + horaSalidas +
                ", horaLlegadas=" + horaLlegadas +
                '}';
    }

    //compareTo
    public int compareTo(TrayectoTrenImpl trenes) {
        int res;
        if (trenes == null) {
            throw new NullPointerException();
        }
        res= getTrayecto ().compareTo (trenes.getTrayecto ());
        if (res == 0) {
            res = getHoraSalida().compareTo (getHoraSalida());
        }
        if (res == 0) {
            res = getCodigoTren ().compareTo (getCodigoTren());
        }
        return res;
    }
}
