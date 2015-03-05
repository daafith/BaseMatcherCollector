package speedcameratests;

import static collector.BaseMatcherCollector.all;
import static custombasematchers.SpeedCameraMatchers.hasCorrectedSpeedsInList;
import static custombasematchers.SpeedCameraMatchers.hasCorrectedSpeedTo;
import static custombasematchers.SpeedCameraMatchers.hasMeasuredSpeed;
import static custombasematchers.SpeedCameraMatchers.hasRevokedLicense;
import static custombasematchers.SpeedCameraMatchers.hasTakenAPicture;
import static custombasematchers.VehicleMatchers.hasBrand;
import static custombasematchers.VehicleMatchers.hasCurrentSpeed;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import speedcamera.SpeedCamera;
import speedcamera.Vehicle;

public class AssertionChainingJUnitTest {
	
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
		expectedInList.add(53);
		vehicle.passSpeedCamera(53);
		assertThat(speedCamera, all(
						hasMeasuredSpeed(53))
						.and(hasCorrectedSpeedTo(49))
						.and(hasCorrectedSpeedsInList(expectedInList))
						.and(hasTakenAPicture(false))
						.and(hasRevokedLicense(false)));
	}
	
	
	@Test
	public void onlyTheFirstOneShallFail() {
		vehicle.passSpeedCamera(53);
		assertThat(speedCamera, all(
						hasTakenAPicture(true))
						.and(hasRevokedLicense(false))
						.and(hasCorrectedSpeedTo(49)));
	}
	
	@Test
	public void onlyTheFirstTwoShallFail() {
		vehicle.passSpeedCamera(53);
		assertThat(speedCamera, all(
						hasTakenAPicture(true))
						.and(hasRevokedLicense(true))
						.and(hasCorrectedSpeedTo(49)));
	}
	
	@Test
	public void noneShallPass() {
		vehicle.passSpeedCamera(53);
		assertThat(speedCamera, all(
						hasTakenAPicture(true))
						.and(hasRevokedLicense(true))
						.and(hasCorrectedSpeedTo(0)));
	}
	
}
