# BaseMatcherCollector
A generic solution for assertion-chaining in Hamrest.
### Hamcrest
A customisable [matcher](https://github.com/hamcrest) library.
```java
	assertThat(foo.isBar(), is(true));
	assertThat(bar.getFoo(), equalTo("foo"));
```
### Custom BaseMatcher
The BaseMatcher can be used to modify our matchers to fit our DSL
```java
	public class FooMatcher {
	
		public static BaseMatcher<Foo> hasBar(final int bar) {
			return new TypeSafeMatcher<Foo>() {
				  
				public void describeTo(final Description description) {
					description.appendText("Foo should have been ").appendValue(bar);
				}
				  
				public void describeMismatchSafely(final Foo foo, final Description mismatchDescription) {
					mismatchDescription.appendText(" instead it was ").appendValue(foo.getBar());
				}
				  
				public boolean matchesSafely(final Foo foo) {
					return bar == foo.getBar();
				}
			};
		}
	}
```
