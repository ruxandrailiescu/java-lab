package eu.airplaneexpenses.classes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Airplane implements Serializable, Comparable<Airplane> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1084437055593692903L;
	private int id;
	private String insuranceComp;
	private double distance;
	private List<AirplaneExpenses> expenses;
	
	public Airplane() {}
	
	public Airplane(int id, String insuranceComp, double distance) {
		this.id = id;
		this.insuranceComp = insuranceComp;
		this.distance = distance;
		this.expenses = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInsuranceComp() {
		return insuranceComp;
	}

	public void setInsuranceComp(String insuranceComp) {
		this.insuranceComp = insuranceComp;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public List<AirplaneExpenses> getExpenses() {
		return expenses;
	}
	
	public void addExpense(AirplaneExpenses expense) throws Exception {
		for (AirplaneExpenses e : this.expenses) {
			if (e.getDate().equals(expense.getDate())) {
				throw new Exception("A record of this day's expenses already exists");
			}
		}
		this.expenses.add(expense);
	}
	
	public void serialize(String filePath) {
		
		try (FileOutputStream fos = new FileOutputStream(filePath);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			
			oos.writeObject(this);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Airplane deserialize(String filePath) {
		
		Airplane airplane = new Airplane();
		
		try (FileInputStream fis = new FileInputStream(filePath);
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			
			airplane = (Airplane)ois.readObject();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return airplane;
	}

	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		
		for (AirplaneExpenses e : this.expenses) {
			
			builder.append(e.toString());
		}
		
		String result = "Airplane [Id = " + this.getId() + ", Insurance Company = " + this.insuranceComp +
				", Distance = " + this.getDistance() + "]\nExpenses: \n\t" + builder.toString();
		return result;
	}
	
	public double getTotalCost() {
		
		double tot = 0;
		for (AirplaneExpenses e : this.expenses) {
			tot += e.calculateExpenses();
		}
		return tot;
	}

	@Override
	public int compareTo(Airplane a) {
		
		if (this.getTotalCost() < a.getTotalCost()) {
			return 1;
		} else if (this.getTotalCost() > a.getTotalCost()) {
			return -1;
		} else {
			return 0;
		}
	}
}
