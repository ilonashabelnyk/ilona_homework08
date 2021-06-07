package Homework08Junit.Suites;

import Homework08Junit.Tests.*;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({
        BasketTotalCompareToCheckoutTotal.class,
        HeaderElements.class,
        SearchResults.class})

public class TestSuite01 {
}