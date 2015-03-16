package custombasematchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import speedcamera.MeasuringDevice;

public class MeasuringDeviceMatcher {

	public static BaseMatcher<MeasuringDevice> isMeasuringDeviceTypeNamed(final String type) {
		return new TypeSafeMatcher<MeasuringDevice>() {
		      
		      public void describeTo(final Description description) {
		    	  description.appendText("The device type should be ")
		    	  			 .appendValue(type);
		      }
		      
		      public void describeMismatchSafely(final MeasuringDevice device, final Description mismatchDescription) {
		    	  mismatchDescription.appendText(" it is ")
		    	  					 .appendValue(device.getDeviceType());
			  }
		      
		      public boolean matchesSafely(final MeasuringDevice device) {
		    	  return type.equals(device.getDeviceType());
			  }
		};
	}
	
}
