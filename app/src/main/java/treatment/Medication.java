package treatment;

/*
    Popescu Flavius
 */

public class Medication {
	private String medicationName;
	private int dosagePerDay;
	private String recommendedHour;
	
	//constructors
	public Medication() {}
	
	public Medication(String medicationName, int dosagePerDay, String recommendedHour) {
		super();
		this.medicationName = medicationName;
		this.dosagePerDay = dosagePerDay;
		this.recommendedHour = recommendedHour;
	}
	
	//getters & setters
	public String getMedicationName() {
		return medicationName;
	}
	public void setMedicationName(String medicationName) {
		this.medicationName = medicationName;
	}
	public int getDosagePerDay() {
		return dosagePerDay;
	}
	public void setDosagePerDay(int dosagePerDay) {
		this.dosagePerDay = dosagePerDay;
	}
	public String getRecommendedHour() {
		return recommendedHour;
	}
	public void setRecommendedHour(String recommendedHour) {
		this.recommendedHour = recommendedHour;
	}
	
}
