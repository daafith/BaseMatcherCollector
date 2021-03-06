package jdk7.speedcameratests;

import static custombasematchers.SpeedCameraMatchers.hasCorrectedSpeedTo;
import static custombasematchers.SpeedCameraMatchers.hasCorrectedSpeedsInList;
import static custombasematchers.SpeedCameraMatchers.hasMeasuredSpeed;
import static custombasematchers.SpeedCameraMatchers.hasRevokedLicense;
import static custombasematchers.SpeedCameraMatchers.hasTakenAPicture;
import static custombasematchers.VehicleMatchers.*;
import static jdk7.BaseMatcherCollector.collect;
import static custombasematchers.MeasuringDeviceMatcher.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import speedcamera.SpeedCamera;
import speedcamera.Vehicle;

/**
 * These are a few examples of safe assertion chaining in JUnit
 */
public class SoftAssertionsJUnitTest {
	
	private Vehicle vehicle;
	private SpeedCamera speedCamera;
	private List<Integer> expectedInList = new ArrayList<Integer>();
	
	@Before
	public void setUp() {
		vehicle = new Vehicle().setSpeed(0);
		speedCamera = new SpeedCamera();
		vehicle.addObserver(speedCamera);
	}
	
	@Test
	public void oneRingToRuleThemAll() {
		expectedInList.add(49);
		vehicle.passSpeedCamera(53);
		assertThat(speedCamera, collect(
						hasMeasuredSpeed(53))
						.and(hasCorrectedSpeedTo(49))
						.and(hasCorrectedSpeedsInList(expectedInList))
						.and(hasTakenAPicture(false))
						.and(hasRevokedLicense(false))
						.and(isMeasuringDeviceTypeNamed("SpeedCamera")));
	}
	
	@Test
	public void onlyTheFirstOneShallFail() {
		vehicle.passSpeedCamera(53);
		assertThat(speedCamera, collect(
						hasTakenAPicture(true))
						.and(hasRevokedLicense(false))
						.and(hasCorrectedSpeedTo(49)));
	}
	
	@Test
	public void onlyTheFirstTwoShallFail() {
		vehicle.passSpeedCamera(53);
		assertThat(speedCamera, collect(
						hasTakenAPicture(true))
						.and(hasRevokedLicense(true))
						.and(hasCorrectedSpeedTo(49)));
	}
	
	@Test
	public void noneShallPass() {
		vehicle.passSpeedCamera(53);
		assertThat(speedCamera, collect(
						hasTakenAPicture(true))
						.and(hasMeasuredSpeed(99))
						.and(hasRevokedLicense(true))
						.and(hasCorrectedSpeedTo(0)));
	}
	
	@Test
	public void vehicleExample() {
		vehicle.setBrand("Lotus");
		vehicle.passSpeedCamera(53);
		assertThat(vehicle, collect(
						hasBrand("Lexus"))
						.and(hasCurrentSpeed(199)));
						
	}
}
