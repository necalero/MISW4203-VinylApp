package com.vinyl.app;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.vinyl.app.activities.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestCollectors {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    public String CollectorName = "Manolo Bellon";

    @Test
    public void collectorViewTest() {
        ViewInteraction collectorsBtn = onView(allOf(withId(R.id.collectors_button), isDisplayed()));
        collectorsBtn.perform(click());

        ViewInteraction collectorsView = onView(withId(R.id.rec_view_collectors));
        collectorsView.check(matches(isDisplayed()));
        collectorsView.perform(scrollToPosition(0));

        ViewInteraction collectorName = onView(allOf(withId(R.id.collector_name), isDescendantOfA(withId(R.id.rec_view_collectors)), withText(CollectorName)));
        collectorName.check(matches(isDisplayed()));
    }

}

