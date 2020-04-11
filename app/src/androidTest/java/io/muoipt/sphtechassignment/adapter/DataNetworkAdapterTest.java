package io.muoipt.sphtechassignment.adapter;


import android.content.res.Resources;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.muoipt.sphtechassignment.R;
import io.muoipt.sphtechassignment.view.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class DataNetworkAdapterTest {

    private static final String ITEM_FIRST_YEAR = "2008";
    private static final String ITEM_LAST_YEAR = "2018";

    private static final int[] ITEMS_YEARS_WITH_USAGE_DECREASE = {3, 5, 7};
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    RecyclerView recyclerView;
    private RecyclerViewMatcher recyclerViewMatcher;

    @Before
    public void initActivity() {
        recyclerView = mActivityTestRule.getActivity().findViewById(R.id.rvDataNetwork);
        recyclerViewMatcher = new RecyclerViewMatcher(R.id.rvDataNetwork);
    }

    @Test
    public void verifyFirstEntry() {

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // verify if first element in recycler view is 2008
        onView(recyclerViewMatcher.atPosition(0))
                .check(matches(hasDescendant(withText(ITEM_FIRST_YEAR))));
    }

    @Test
    public void testButtons() {

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.rvDataNetwork))
                .perform(RecyclerViewActions.scrollToPosition(ITEMS_YEARS_WITH_USAGE_DECREASE[0]));
        onView(recyclerViewMatcher.atPositionOnView(ITEMS_YEARS_WITH_USAGE_DECREASE[0], R.id.iBtnDetailedSent)).perform(click());
        onView(recyclerViewMatcher.atPositionOnView(ITEMS_YEARS_WITH_USAGE_DECREASE[0], R.id.llQuarterDetailsContainer)).check(matches(isDisplayed()));

        onView(withId(R.id.rvDataNetwork))
                .perform(RecyclerViewActions.scrollToPosition(ITEMS_YEARS_WITH_USAGE_DECREASE[1]));
        onView(recyclerViewMatcher.atPositionOnView(ITEMS_YEARS_WITH_USAGE_DECREASE[1], R.id.iBtnDetailedSent)).perform(click());
        onView(recyclerViewMatcher.atPositionOnView(ITEMS_YEARS_WITH_USAGE_DECREASE[1], R.id.llQuarterDetailsContainer)).check(matches(isDisplayed()));

        onView(withId(R.id.rvDataNetwork))
                .perform(RecyclerViewActions.scrollToPosition(ITEMS_YEARS_WITH_USAGE_DECREASE[2]));
        onView(recyclerViewMatcher.atPositionOnView(ITEMS_YEARS_WITH_USAGE_DECREASE[2], R.id.iBtnDetailedSent)).perform(click());
        onView(recyclerViewMatcher.atPositionOnView(ITEMS_YEARS_WITH_USAGE_DECREASE[2], R.id.llQuarterDetailsContainer)).check(matches(isDisplayed()));

    }

    public class RecyclerViewMatcher {
        private final int recyclerViewId;

        public RecyclerViewMatcher(int recyclerViewId) {
            this.recyclerViewId = recyclerViewId;
        }

        public Matcher<View> atPosition(final int position) {
            return atPositionOnView(position, -1);
        }

        public Matcher<View> atPositionOnView(final int position, final int targetViewId) {

            return new TypeSafeMatcher<View>() {
                Resources resources = null;
                View childView;

                public void describeTo(Description description) {
                    String idDescription = Integer.toString(recyclerViewId);
                    if (this.resources != null) {
                        try {
                            idDescription = this.resources.getResourceName(recyclerViewId);
                        } catch (Resources.NotFoundException var4) {
                            idDescription = String.format("%s (resource name not found)",
                                    Integer.valueOf
                                            (recyclerViewId));
                        }
                    }

                    description.appendText("with id: " + idDescription);
                }

                public boolean matchesSafely(View view) {

                    this.resources = view.getResources();

                    if (childView == null) {
                        RecyclerView recyclerView =
                                view.getRootView().findViewById(recyclerViewId);
                        if (recyclerView != null && recyclerView.getId() == recyclerViewId) {
                            childView = recyclerView.findViewHolderForAdapterPosition(position).itemView;
                        } else {
                            return false;
                        }
                    }

                    if (targetViewId == -1) {
                        return view == childView;
                    } else {
                        View targetView = childView.findViewById(targetViewId);
                        return view == targetView;
                    }

                }
            };
        }

    }
}
