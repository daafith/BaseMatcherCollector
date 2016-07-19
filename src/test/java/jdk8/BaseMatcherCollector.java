package jdk8;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import custombasematchers.SpeedCameraMatchers;
import jdk8.speedcameratests.SoftAssertionsJUnitTest;
import jdk8.speedcameratests.SoftAssertionsTestNgTest;

/**
 * 
 * @author David Baak, Copyright March 2016 <br />
 * This BaseMatcherCollector enables you to chain custom {@link BaseMatcher} of type <T> with {@code chain} and {@code and}.<br />
 * An example of a custom {@link BaseMatcher} is: {@link SpeedCameraMatchers}<br />
 * An example of chaining in Junit is: {@link SoftAssertionsJUnitTest}<br />
 * An example of chaining in TestNg is: {@link SoftAssertionsTestNgTest}<br />
 * @param <T>
 */
public class BaseMatcherCollector<T> extends BaseMatcher<T> {
	
	private final List<BaseMatcher<? super T>> matchers = new ArrayList<BaseMatcher<? super T>>();
	private final List<BaseMatcher<? super T>> mismatches = new ArrayList<BaseMatcher<? super T>>();
	
	private BaseMatcherCollector(final BaseMatcher<? super T> matcher) {
		matchers.add(matcher);
	}

	public boolean matches(final Object itemToMatch) {
		matchers
			.stream()
			.filter(matcher->!matcher.matches(itemToMatch))
			.forEach(matcher->mismatches.add(matcher));
		return mismatches.isEmpty();
	}

	public void describeTo(final Description description) {
	      description.appendList("\n", "\n" + "AND" + " ", "", matchers);
	}
	
	@Override
	public void describeMismatch(final Object item, final Description description) {
		mismatches.forEach(mismatch->mismatch
				.describeMismatch(item, description.appendText("\n")
				.appendDescriptionOf(mismatch).appendText(" BUT ")));
	}
	
	/**
	 * Adds your nth assertion to the chain.
	 * @param matcher 
	 * @return the existing instance of BaseMatcherCollector
	 */
	public BaseMatcherCollector<T> and(final BaseMatcher<? super T> matcher) {
		matchers.add(matcher);
		return this;
	}
	
	/**
	 * Initiates your assertion chain.
	 * @param matcher
	 * @return an instance of BaseMatcherCollector Type {@code<T>} <br />
	 */
	public static <T> BaseMatcherCollector<T> collect(final BaseMatcher<? super T> matcher) {
	      return new BaseMatcherCollector<T>(matcher);
	}

}
