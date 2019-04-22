package treatment;

/*
    Popescu Flavius
 */

import java.util.ArrayList;

public class Recommendation {
	
	private String recommendation;
	
	//constructors
	public Recommendation() {}
	
	public Recommendation(String recommendation) {
		super();
		this.recommendation = recommendation;
	}

	//getters & setters
	public String getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}
}
