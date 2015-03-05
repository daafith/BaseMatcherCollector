package speedcamera;

import java.util.Observable;

public class Vehicle extends Observable {
	
	private int speed;
	private String brand;
	
	public Vehicle() {}
	
	public Vehicle setBrand(String brand) {
		this.brand = brand;
		return this;
	}

	public Vehicle passSpeedCamera(int speed) {
		setSpeed(speed);
		setChanged();
		notifyObservers(speed);
		return this;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public Vehicle setSpeed(int speed) {
		if (speed < 0) {
			throw new IllegalArgumentException(speed + " is not a valid speed");
		}
		this.speed = speed;
		return this;
	}
	



}
