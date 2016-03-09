package jdk8.speedcameratests;

import static custombasematchers.SpeedCameraMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import speedcamera.SpeedCamera;
import speedcamera.Vehicle;

/**
 * @author davidbaak<br />
 * The purpose of this is test is to show you what a test with a custom BaseMatcher looks like.<br />
 * Specs of Camera's speed correction are:<br />
 * <ul>
 * <li>30 >= (correction=3) <= 50</li>
 * <li>51 <= (correction=4) <= 100</li>
 * <li>101 <= (correction=5)</li>
 * </ul>
 */

public class SpeedCorrectionTest {
	
	private Vehicle vehicle;
	private SpeedCamera speedCamera;
	private List<Integer> expectedInList = new ArrayList<Integer>();
	
	@Before
	public void setUp() {
		vehicle = new Vehicle().setBrand("Skoda").setSpeed(0);
		speedCamera = new SpeedCamera();
		vehicle.addObserver(speedCamera);
	}
	
	@Test
	public void expectNoCorrectionWhenOneBelowMeausureTrigger() {
		vehicle.passSpeedCamera(29);
		assertThat(speedCamera, hasCorrectedSpeedTo(29));
	}
	
	@Test
	public void checkListOfCorrectedSpeeds() {
		expectedInList.add(49);
		expectedInList.add(79);
		vehicle
			.passSpeedCamera(53)
			.passSpeedCamera(100)
			.passSpeedCamera(83);
		assertThat(speedCamera, hasCorrectedSpeedsInList(expectedInList));
	}
	
	@Test
	public void failThisTest() {
		vehicle.passSpeedCamera(29);
		assertThat(speedCamera, hasCorrectedSpeedTo(1));
	}
	
	@Test
	public void failListOfCorrectedSpeeds() {
		expectedInList.add(6);
		expectedInList.add(83);
		vehicle
			.passSpeedCamera(53)
			.passSpeedCamera(100)
			.passSpeedCamera(83);
		assertThat(speedCamera, hasCorrectedSpeedsInList(expectedInList));
	}
	
}
