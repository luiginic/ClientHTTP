package personal.data;

/*
Flavius Popescu
 */
import java.io.Serializable;

public class Doctor extends PersonalData implements Serializable {
    private String cabinetAddress;

    public void setCabinetAddress(String cabinetAddress) {
        this.cabinetAddress = cabinetAddress;
    }
}
