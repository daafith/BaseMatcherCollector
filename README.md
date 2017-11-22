# BaseMatcherCollector

## UPDATE
This solution is unnecessary. Use the [ErrorCollector](http://junit.org/junit4/javadoc/4.12/org/junit/rules/ErrorCollector.html) and you'll have soft assertions.


A generic solution for soft assertions in Hamcrest. This [class](https://github.com/daafith/BaseMatcherCollector/blob/master/src/test/java/jdk8/BaseMatcherCollector.java) enables us to safely chain our BaseMatchers of type `? super <T>`. 
The collector returns the results **after all** matchers have been executed.
```java
	assertThat(foo, collect(
				hasFooBar(true))
				.and(hasBar("foo"))
				.and(hasFoo(42)));
		// Output on failure:
		// Expected: 
		// I wanted hasFooBar to return <true> 
		// AND hasBar should have been "foo" 
		// AND hasFoo should return <42>
		//	   but: 
		// I wanted hasFooBar to return <true> BUT it returned <false>
		// hasBar should have been "foo" BUT instead it was "bar"
		// hasFoo should return <42> BUT it returned <14>
```
Of course you can tailor the output if you so desire. Then edit `describeTo` and/or `describeMismatch`.
