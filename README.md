# BaseMatcherCollector
A generic solution for assertion-chaining in Hamrest.
### Hamcrest
A customisable [matcher](https://github.com/hamcrest) library.
```java
	assertThat(foo.isBar(), is(true));
	assertThat(bar.getFoo(), equalTo("foo"));
	
	//Output on failure: Expected: "foo"
    //						  but: was "bar"
```
### Custom BaseMatcher
The BaseMatcher can be used to modify our matchers to fit our DSL and to define  our failure output once.
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
### BaseMatcherCollector
This [class](https://github.com/daafith/BaseMatcherCollector/blob/master/src/test/java/collector/BaseMatcherCollector.java) enables us to chain matchers.
```java
	assertThat(foo, all(
				hasFoobar(true))
				.and(hasBar("foo"))
				.and(hasFoo(42)));
		// Output on failure:
		//Expected: Taking a picture should have returned <true> AND Revoking licensed should have returned <true> AND The camera should have corrected the speed to <0>
		//but:  Taking a picture should have returned <true> BUT  returned <false> Revoking licensed should have returned <true> BUT  returned <false> The camera should have corrected the speed to <0> BUT  alas, the camera corrected it to <49>
```