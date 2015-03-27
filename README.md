# BaseMatcherCollector
A generic solution for safe assertion chaining in Hamcrest. This [class](https://github.com/daafith/BaseMatcherCollector/blob/master/src/test/java/collector/BaseMatcherCollector.java) enables us to safely chain our BaseMatchers of type <T>. 
The collector returns the results **after all** matchers have been executed.
```java
	assertThat(foo, chain(
				hasFooBar(true))
				.and(hasBar("foo"))
				.and(hasFoo(42)));
		// Output on failure:
		// Expected: 
		// I wanted hasFooBar to return <true> 
		// AND Foo should have been "foo" 
		// AND hasFoo should return <42>
		//	   but: 
		// I wanted hasFooBar to return <true> BUT it returned <false>
		// Foo should have been "foo" BUT instead it was "bar"
		// hasFoo should return <42> BUT it returned <14>
```
Of course you can tailor the output if you so desire. Then edit `describeTo` and/or `describeMismatch`.
### Custom BaseMatcher
The BaseMatcher can be customised so that your matchers fit your context and it helps you define failure output once per BaseMatcher.
```java
	public class FooMatcher {
	
		public static BaseMatcher<Foo> hasBar(final String bar) {
			return new TypeSafeMatcher<Foo>() {
				  
				public void describeTo(final Description description) {
					description.appendText("Foo should have been ").appendValue(bar);
				}
				  
				public void describeMismatchSafely(final Foo foo, final Description mismatchDescription) {
					mismatchDescription.appendText(" instead it was ").appendValue(foo.getBar());
				}
				  
				public boolean matchesSafely(final Foo foo) {
					return bar.equals(foo.getBar());
				}
			};
		}
	}
```
The assertThat now looks like this.
```java
	assertThat(foo, hasBar("foo"));
	
		//Output on failure: Expected: Foo should have been "foo"
		//					 	  but:  instead it was "bar"
```
