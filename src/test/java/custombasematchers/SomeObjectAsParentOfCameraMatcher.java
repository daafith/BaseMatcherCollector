package custombasematchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import speedcamera.SomeObjectAsParentOfCamera;

public class SomeObjectAsParentOfCameraMatcher {

	public static BaseMatcher<SomeObjectAsParentOfCamera> hasFoo(final String foo) {
		return new TypeSafeMatcher<SomeObjectAsParentOfCamera>() {
		      
		      public void describeTo(final Description description) {
		    	  description.appendText("The SomeObj getFoo should return ").appendValue(foo);
		      }
		      
		      public void describeMismatchSafely(final SomeObjectAsParentOfCamera obj, final Description mismatchDescription) {
		    	  mismatchDescription.appendText(" it is ").appendValue(obj.getFoo());
			  }
		      
		      public boolean matchesSafely(final SomeObjectAsParentOfCamera obj) {
		    	  return foo.equals(obj.getFoo());
			  }
		};
	}
	
}
