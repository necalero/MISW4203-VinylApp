package com.vinyl.app;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
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
public class TestAlbums {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    public String AlbumName = "Poeta del pueblo";

    @Test
    public void albumViewTest() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction albumView = onView(withId(R.id.linear_selector));
        albumView.check(matches(isDisplayed()));

        ViewInteraction albumName = onView(allOf(withId(R.id.album_name_tv), withText(AlbumName)));
        albumName.check(matches(isDisplayed()));
    }

    @Test
    public void albumDetailViewTest() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction albumView = onView(withId(R.id.linear_selector));
        albumView.check(matches(isDisplayed()));

        ViewInteraction albumName = onView(allOf(withId(R.id.album_name_tv), withText(AlbumName)));
        albumName.check(matches(isDisplayed()));
        albumName.perform(click());

        ViewInteraction albumNameDetail = onView(allOf(withId(R.id.albumName), withText(AlbumName)));
        albumNameDetail.check(matches(isDisplayed()));

        ViewInteraction albumDescription = onView(allOf(withId(R.id.albumDescription)));
        albumDescription.check(matches(isDisplayed()));

        ViewInteraction albumCover = onView(allOf(withId(R.id.albumCover)));
        albumCover.check(matches(isDisplayed()));

    }

}

