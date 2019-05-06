package data_collection;

public class PacientDailyInfo {
    private Integer water;
    private Integer weight;
    private Integer pulse;
    private Integer temperature;
    private Integer steps;
    private String today;
    private Integer patient_id;

    public PacientDailyInfo(Integer water, Integer weight, Integer pulse, Integer temperature, Integer steps, String today, Integer pacientId) {
        this.water = water;
        this.weight = weight;
        this.pulse = pulse;
        this.temperature = temperature;
        this.steps = steps;
        this.today = today;
        this.patient_id = pacientId;
    }

    @Override
    public String toString() {
        return water.toString()+ ',' + weight.toString()+',' + pulse.toString() +','+ temperature.toString() + ',' + steps.toString();
    }
}
