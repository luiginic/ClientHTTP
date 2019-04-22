package personal.data;

/*
Flavius Popescu
 */

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class PersonalData implements Serializable {
    private String cnp;
    private String name;
    private String pacientCode;
    private int age;
    private String phoneNumber;
    private Doctor doctor;
    private Date dateOfBirth;

    public PersonalData(){};

    public PersonalData(String cnp,String name, String pacientCode, String phoneNumber,Doctor doctor, Date dateOfBirth){
        setCnp(cnp);
        setName(name);
        setPacientCode(pacientCode);
        setAge();
        setPhoneNumber(phoneNumber);
        setDoctor(doctor);
        setDateOfBirth(dateOfBirth);

    }


    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPacientCode() {
        return pacientCode;
    }

    public void setPacientCode(String pacientCode) {
        this.pacientCode = pacientCode;
    }


    public void setAge() {
        this.age = getAge(dateOfBirth);
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public int getAge(Date dateOfBirth) {

        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();

        this.age = 0;

        birthDate.setTime(dateOfBirth);
        if (birthDate.after(today)) {
            throw new IllegalArgumentException("Can't be born in the future");
        }

        age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

        // If birth date is greater than todays date (after 2 days adjustment of leap year) then decrement age one year
        if ( (birthDate.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR) > 3) ||
                (birthDate.get(Calendar.MONTH) > today.get(Calendar.MONTH ))){
            age--;

            // If birth date and todays date are of same month and birth day of month is greater than todays day of month then decrement age
        }else if ((birthDate.get(Calendar.MONTH) == today.get(Calendar.MONTH )) &&
                (birthDate.get(Calendar.DAY_OF_MONTH) > today.get(Calendar.DAY_OF_MONTH ))){
            age--;
        }

        return age;
    }


}
