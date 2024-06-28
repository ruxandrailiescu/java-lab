package eu.airplaneexpenses.classes;

import java.io.Serializable;

public class AirplaneExpenses implements CalculateExpenses, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8854588203148363482L;
	private double keroseneCons;
	private double keroseneCost;
	private double insuranceCost;
	private double crewCost;
	private String date;	// dd/mm/yyyy
	
	@Override
	public double calculateExpenses() {
		return this.getKeroseneCons() * this.getKeroseneCost() +
				this.getInsuranceCost() + this.getCrewCost();
	}

	public AirplaneExpenses(double keroseneCons, double keroseneCost, double insuranceCost, double crewCost,
			String date) {
		try {
			this.keroseneCons = keroseneCons;
			this.keroseneCost = keroseneCost;
			this.insuranceCost = insuranceCost;
			this.crewCost = crewCost;
			this.setDate(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public double getKeroseneCons() {
		return keroseneCons;
	}

	public void setKeroseneCons(double keroseneCons) {
		this.keroseneCons = keroseneCons;
	}

	public double getKeroseneCost() {
		return keroseneCost;
	}

	public void setKeroseneCost(double keroseneCost) {
		this.keroseneCost = keroseneCost;
	}

	public double getInsuranceCost() {
		return insuranceCost;
	}

	public void setInsuranceCost(double insuranceCost) {
		this.insuranceCost = insuranceCost;
	}

	public double getCrewCost() {
		return crewCost;
	}

	public void setCrewCost(double crewCost) {
		this.crewCost = crewCost;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) throws Exception {
		if (date.length() != 10 ||
				date.charAt(2) != '/' ||
				date.charAt(5) != '/') {
			throw new Exception("Invalid date format");
		}
		this.date = date;
	}

	@Override
	public String toString() {
		return String.format(" [Date = %s, Total = %.2f] ", this.getDate(), this.calculateExpenses());
	}
}
