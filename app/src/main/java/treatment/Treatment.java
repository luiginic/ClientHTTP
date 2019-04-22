package treatment;

import java.util.ArrayList;

/*
    Popescu Flavius
 */

public class Treatment {
	private ArrayList<Medication> med = new ArrayList<Medication>();
	private ArrayList<Recommendation> rec = new ArrayList<Recommendation>();
	
	//constructors
	public Treatment() {}
	
	public Treatment(ArrayList<Medication> med, ArrayList<Recommendation> rec) {
		super();
		this.med = med;
		this.rec = rec;
	}
	
	//setters & getters
	public ArrayList<Medication> getMed() {
		return med;
	}
	public void setMed(ArrayList<Medication> med) {
		this.med = med;
	}
	public ArrayList<Recommendation> getRec() {
		return rec;
	}
	public void setRec(ArrayList<Recommendation> rec) {
		this.rec = rec;
	}
	

	public void addMed(Medication med)
	{
		this.med.add(med);
	}
	
	public void addRec(Recommendation rec)
	{
		this.rec.add(rec);
	}
}
