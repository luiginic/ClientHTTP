package appointment;

/*
    Prodan Nicoale
 */
import java.util.Date;

import personal.data.PersonalData;

public class Appointment {
	
	PersonalData person;
	Date day;

	public PersonalData getPerson() {
		return person;
	}

	public void setPerson(PersonalData person) {
		this.person = person;
	}

	public Appointment(PersonalData person) {
		super();
		day = new Date();

		this.person = person;
	}
	

}
