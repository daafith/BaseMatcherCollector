package speedcamera;

public class LaserGun extends MeasuringDevice {
	
	public LaserGun() {
		setDeviceType(this.getClass());
	}
	
	public void malfunction() {
		System.out.println("Ain't got no batteries");
	}

}
