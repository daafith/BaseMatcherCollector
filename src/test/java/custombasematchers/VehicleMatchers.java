package custombasematchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import speedcamera.Vehicle;

public class VehicleMatchers {
	
	public static BaseMatcher<Vehicle> hasCurrentSpeed(final int vehicleSpeed) {
		return new TypeSafeMatcher<Vehicle>() {
		      
		      public void describeTo(final Description description) {
		    	  description.appendText("The vehicle's current speed should be ")
		    	  			 .appendValue(vehicleSpeed);
		      }
		      
		      public void describeMismatchSafely(final Vehicle vehicle, final Description mismatchDescription) {
		    	  mismatchDescription.appendText(" it claims to have a speed of ")
		    	  					 .appendValue(vehicle.getSpeed());
			  }
		      
		      public boolean matchesSafely(final Vehicle vehicle) {
		    	  return vehicleSpeed == vehicle.getSpeed();
			  }
		};
	}
	
	public static BaseMatcher<Vehicle> hasBrand(final String vehicleBrand) {
		return new TypeSafeMatcher<Vehicle>() {
		      
		      public void describeTo(final Description description) {
		    	  description.appendText("The vehicle's brand should be ")
		    	  			 .appendValue(vehicleBrand);
		      }
		      
		      public void describeMismatchSafely(final Vehicle vehicle, final Description mismatchDescription) {
		    	  mismatchDescription.appendText(" it is ")
		    	  					 .appendValue(vehicle.getBrand());
			  }
		      
		      public boolean matchesSafely(final Vehicle vehicle) {
		    	  return vehicleBrand.equals(vehicle.getBrand());
			  }
		};
	}

}
