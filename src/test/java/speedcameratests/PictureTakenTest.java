package speedcameratests;

import static custombasematchers.SpeedCameraMatchers.hasTakenAPicture;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

import speedcamera.SpeedCamera;
import speedcamera.Vehicle;

/**
 * @author davidbaak<br />
 * The purpose of this is test is to show you what a test with a custom BaseMatcher looks like.<br />
 * Specs of Camera's picture trigger are:<br />
 * <b>when</b> correctedSpeed >= 51 <b>then</b> camera should take picture<br />
 */

public class PictureTakenTest {
	
	private Vehicle vehicle;
	private SpeedCamera speedCamera;
	
	@Before
	public void setUp() {
		vehicle = new Vehicle().setSpeed(0);
		speedCamera = new SpeedCamera();
		vehicle.addObserver(speedCamera);
	}
	
	@Test
	public void expectNoTicketOneBelowSpeedLimit() {
		vehicle.passSpeedCamera(53);
		assertThat(speedCamera, hasTakenAPicture(false));
	}
	
	@Test
	public void failThisTest() {
		vehicle.passSpeedCamera(53);
		assertThat(speedCamera, hasTakenAPicture(true));
	}

}
