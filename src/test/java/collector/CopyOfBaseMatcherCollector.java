package collector;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.pcollections.PVector;
import org.pcollections.TreePVector;

import speedcameratests.CopyOfAssertionChainingJUnitTest;
import custombasematchers.SpeedCameraMatchers;

/**
 * 
 * @author David Baak, Copyright March 2015 <br />
 * This BaseMatcherCollector enables you to chain custom {@link BaseMatcher} of type <T> with {@code chain} and {@code and}.<br />
 * An example of a custom {@link BaseMatcher} is: {@link SpeedCameraMatchers}<br />
 * An example of chaining in Junit is: {@link CopyOfAssertionChainingJUnitTest}<br />
 * @param <T>
 */
public final class CopyOfBaseMatcherCollector<T> extends BaseMatcher<T> {
	
	private PVector<BaseMatcher<? super T>> matchers = TreePVector.empty();
	private PVector<BaseMatcher<? super T>> mismatches = TreePVector.empty();
	
	private CopyOfBaseMatcherCollector(final BaseMatcher<? super T> matcher) {
		matchers = matchers.plus(matcher);
	}

	public boolean matches(Object itemToMatch) {
		for (final BaseMatcher<? super T> matcher : matchers) {
			if (!matcher.matches(itemToMatch)) {
				mismatches = mismatches.plus(matcher); 
			}
	    }
		return mismatches.isEmpty();
	}

	public void describeTo(Description description) {
	      description.appendList("\n", "\n" + "AND" + " ", "", matchers);
	}
	
	public void describeMismatch(final Object item, final Description description) {
		for (final BaseMatcher<? super T> mismatch : mismatches) {
			description.appendText("\n")
					   .appendDescriptionOf(mismatch).appendText(" BUT ");
			mismatch.describeMismatch(item, description);
		}
	}
	
	/**
	 * Adds your nth assertion to the chain.
	 * @param matcher
	 * @return the existing instance of BaseMatcherCollector
	 */
	public CopyOfBaseMatcherCollector<T> and(final BaseMatcher<? super T> matcher) {
		matchers = matchers.plus(matcher);
		return this;
	}
	
	/**
	 * Initiates your assertion chain.
	 * @param matcher
	 * @return an instance of BaseMatcherCollector Type {@code<T>} <br />
	 */
	public static <T> CopyOfBaseMatcherCollector<T> chain(final BaseMatcher<? super T> matcher) {
	      return new CopyOfBaseMatcherCollector<T>(matcher);
	}

}
