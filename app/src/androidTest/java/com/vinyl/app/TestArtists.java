package com.vinyl.app;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static org.hamcrest.Matchers.allOf;

import com.vinyl.app.activities.MainActivity;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestArtists {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    public String ArtistName = "Frankie Ruiz";

    @Test
    public void artistsViewTest() {
        ViewInteraction artistsBtn = onView(allOf(withId(R.id.artists_button), isDisplayed()));
        artistsBtn.perform(click());

        ViewInteraction artistsView = onView(withId(R.id.rec_view_musicians));
        artistsView.check(matches(isDisplayed()));
        artistsView.perform(scrollToPosition(0));

        ViewInteraction artistName = onView(allOf(withId(R.id.musician_name), isDescendantOfA(withId(R.id.rec_view_musicians)), withText(ArtistName)));
        artistName.check(matches(isDisplayed()));
    }

    @Test
    public void artistsDetailViewTest() {
        ViewInteraction artistsBtn = onView(allOf(withId(R.id.artists_button), isDisplayed()));
        artistsBtn.perform(click());

        ViewInteraction artistsView = onView(withId(R.id.rec_view_musicians));
        artistsView.check(matches(isDisplayed()));
        artistsView.perform(scrollToPosition(0));

        ViewInteraction artistName = onView(allOf(withId(R.id.musician_name), isDescendantOfA(withId(R.id.rec_view_musicians)), withText(ArtistName)));
        artistName.perform(click());

        ViewInteraction artistNameDetail = onView(allOf(withId(R.id.artist_name_tv), withText(ArtistName)));
        artistNameDetail.check(matches(isDisplayed()));

        ViewInteraction artistBirth = onView(allOf(withId(R.id.artist_birthdate_tv)));
        artistBirth.check(matches(isDisplayed()));

        ViewInteraction artistDescription = onView(allOf(withId(R.id.artist_description_tv)));
        artistDescription.check(matches(isDisplayed()));

    }

}

