package idv.tim.test.model;

import java.util.ArrayList;
import java.util.List;

public class CarsAndTruck {
	
	private List<Car> cars;
	private Truck truck;
	
	public CarsAndTruck() {
		
	}
	public CarsAndTruck(List<Car> cars, Truck truck) {
		super();
		this.cars = cars;
		this.truck = truck;
	}
	public List<Car> getCars() {
		return cars;
	}
	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	public Truck getTruck() {
		return truck;
	}
	public void setTruck(Truck truck) {
		this.truck = truck;
	}
	
	

}
