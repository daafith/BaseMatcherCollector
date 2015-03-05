package custombasematchers;

import java.util.List;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import speedcamera.SpeedCamera;

public class SpeedCameraMatchers {
	
	public static BaseMatcher<SpeedCamera> hasMeasuredSpeed(final int measuredSpeed) {
		return new TypeSafeMatcher<SpeedCamera>() {
		      
			public void describeTo(final Description description) {
				description.appendText("The camera should have corrected the speed to ").appendValue(measuredSpeed);
		    }
		      
		    public void describeMismatchSafely(final SpeedCamera camera, final Description mismatchDescription) {
		    	mismatchDescription.appendText(" alas, the camera corrected it to ").appendValue(camera.getMeasuredSpeed());
			}
		      
		    public boolean matchesSafely(final SpeedCamera camera) {
		    	return measuredSpeed == camera.getMeasuredSpeed();
			}
		};
	}
	
	public static BaseMatcher<SpeedCamera> hasCorrectedSpeedTo(final int correctedSpeed) {
		return new TypeSafeMatcher<SpeedCamera>() {
		      
		      public void describeTo(final Description description) {
		    	  description.appendText("The camera should have corrected the speed to ").appendValue(correctedSpeed);
		      }
		      
		      public void describeMismatchSafely(final SpeedCamera camera, final Description mismatchDescription) {
		    	  mismatchDescription.appendText(" alas, the camera corrected it to ").appendValue(camera.getCorrectedSpeed());
			  }
		      
		      public boolean matchesSafely(final SpeedCamera camera) {
		    	  return correctedSpeed == camera.getCorrectedSpeed();
			  }
		};
	}
	
	public static BaseMatcher<SpeedCamera> hasCorrectedSpeedsInList(final List<Integer> correctedSpeeds) {
		return new TypeSafeMatcher<SpeedCamera>() {
		      
		      public void describeTo(final Description description) {
		    	  description.appendText("The camera should have these values in its list of corrected speeds").appendValue(correctedSpeeds);
		      }
		      
		      public void describeMismatchSafely(final SpeedCamera camera, final Description mismatchDescription) {
		    	  mismatchDescription.appendText(" these values are in the list ").appendValue(camera.getAllCorrectedSpeeds());
			  }
		      
		      public boolean matchesSafely(final SpeedCamera camera) {
		    	  return camera.getAllCorrectedSpeeds().containsAll(correctedSpeeds);
			  }
		};
	}
	
	public static BaseMatcher<SpeedCamera> hasTakenAPicture(final boolean hasTakenPicture) {
		return new TypeSafeMatcher<SpeedCamera>() {
		      
		      public void describeTo(final Description description) {
		         description.appendText("Taking a picture should have returned ").appendValue(hasTakenPicture);
		      }
		      
		      public void describeMismatchSafely(final SpeedCamera item, final Description mismatchDescription) {
		         mismatchDescription.appendText(" returned ").appendValue(item.isPictureTaken());
		      }

		      public boolean matchesSafely(final SpeedCamera camera) {
		         return hasTakenPicture == camera.isPictureTaken();
		      }
		      
		};
	}
	
	public static BaseMatcher<SpeedCamera> hasRevokedLicense(final boolean hasRevokedLicense) {
		return new TypeSafeMatcher<SpeedCamera>() {
		      
		      public void describeTo(final Description description) {
		         description.appendText("Revoking license should have returned ").appendValue(hasRevokedLicense);
		      }
		      
		      public void describeMismatchSafely(final SpeedCamera item, final Description mismatchDescription) {
		         mismatchDescription.appendText(" returned ").appendValue(item.isLicenseRevoked());
		      }

		      public boolean matchesSafely(final SpeedCamera camera) {
		         return hasRevokedLicense == camera.isLicenseRevoked();
		      }
		      
		};
	}
	
}
