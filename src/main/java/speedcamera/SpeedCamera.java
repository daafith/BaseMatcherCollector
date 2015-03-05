package speedcamera;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class SpeedCamera implements Observer {

	private int measuredSpeed;
	private int correctedSpeed;
	private final static int speedLimit = 50;
	private final static int licenseRevocationLimit = speedLimit + 50;
	private boolean isPictureTaken = false;
	private boolean isLicenseRevoked = false;
	private List<Integer> correctedSpeedList = new ArrayList<Integer>();
	
	public SpeedCamera() {
		correctedSpeed = 0;
		measuredSpeed = 0;
	}
	
	public int getMeasuredSpeed() {
		return measuredSpeed;
	}
	
	public int getCorrectedSpeed() {
		return correctedSpeed;
	}
	
	public List<Integer> getAllCorrectedSpeeds() {
		return correctedSpeedList;
	}
	
	public boolean isPictureTaken() {
		return isPictureTaken;
	}
	
	public boolean isLicenseRevoked() {
		return isLicenseRevoked;
	}
	
	public void update(Observable obs, Object speed) {
		if (speed instanceof Integer) {
			setMeasuredSpeed((Integer) speed);
			setCorrectedSpeed();
			takeThePicture();
		}
	}
	

	private void setMeasuredSpeed(int measuredSpeed) {
		this.measuredSpeed = measuredSpeed;
	}
	
	private void setCorrectedSpeed() {
		correctedSpeed = measuredSpeed;
		addCorrectedSpeedToList(correctedSpeed);
		if (correctedSpeed >=30) {
			if (correctedSpeed <= 50) {
				correctedSpeed -= 3;
			} else if (correctedSpeed <= 100) {
				correctedSpeed -= 4;
			} else {
				correctedSpeed -= 5;
			}
		}
	}
	
	private void addCorrectedSpeedToList(int correctedSpeed) {
		correctedSpeedList.add(correctedSpeed);
	}
	
	private void takeThePicture() {
		if (correctedSpeed > speedLimit) {
			pictureMoment();
			isPictureTaken = true;
			sendPictureToJusticeDepartment();
			if (correctedSpeed >= licenseRevocationLimit) {
				sendPictureToLicensingAuthority();
				isLicenseRevoked = true;
			}
		}
	}

	private void pictureMoment() {
		//No implementation needed for the scope of this example		
	}

	private void sendPictureToJusticeDepartment() {
		//No implementation needed for the scope of this example
	}
	
	private void sendPictureToLicensingAuthority() {
		//No implementation needed for the scope of this example
	}

}
