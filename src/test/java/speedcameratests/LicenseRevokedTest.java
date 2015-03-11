package speedcameratests;

import static custombasematchers.SpeedCameraMatchers.hasRevokedLicense;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

import speedcamera.SpeedCamera;
import speedcamera.Vehicle;

/**
 * @author davidbaak<br />
 * The purpose of this is test is to show you what a test with a custom BaseMatcher looks like.<br />
 * Specs of Camera's speed correction are:<br />
 * <b>when</b> correctedSpeed >= speedLimit+50 <b>then/<b> license should be revoked<br />
 */

public class LicenseRevokedTest {
	
	private Vehicle vehicle;
	private SpeedCamera speedCamera;
	
	@Before
	public void setUp() {
		vehicle = new Vehicle().setSpeed(0);
		speedCamera = new SpeedCamera();
		vehicle.addObserver(speedCamera);
	}
	
	@Test
	public void expectNoLicenseRevocationOneBelowRevocationLimit() {
		vehicle.passSpeedCamera(104);
		assertThat(speedCamera, hasRevokedLicense(false));
	}
	
	@Test
	public void failThisTest() {
		vehicle.passSpeedCamera(104);
		assertThat(speedCamera, hasRevokedLicense(true));
	}

}
