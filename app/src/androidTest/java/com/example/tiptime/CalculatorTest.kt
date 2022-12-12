package com.example.tiptime

import androidx.test.espresso.assertion.ViewAssertions.matches
import org.hamcrest.Matchers.containsString
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class CalculatorTest {
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun calculate_20_percent_tip() {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("50.00"))
            .perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.btn_calculate))
            .perform(click())

        onView(withId(R.id.tips_amount)).check(matches(withText(containsString("10,00"))))
    }

    @Test
    fun calculate_15_percent_tip() {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("50.00"))
            .perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.tips_option_15_percent)).perform(click())

        onView(withId(R.id.btn_calculate)).perform(click())
        onView(withId(R.id.tips_amount)).check(matches(withText(containsString("8,00"))))

        onView(withId(R.id.switch_round_tips)).perform(click())

        onView(withId(R.id.btn_calculate)).perform(click())
        onView(withId(R.id.tips_amount)).check(matches(withText(containsString("7,50"))))

    }

    @Test
    fun calculate_10_percent_tip() {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("93.00"))
            .perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.tips_option_10_percent)).perform(click())
        onView(withId(R.id.switch_round_tips)).perform(click())
        onView(withId(R.id.btn_calculate)).perform(click())
        onView(withId(R.id.tips_amount)).check(matches(withText(containsString("9,30"))))

        onView(withId(R.id.switch_round_tips)).perform(click())

        onView(withId(R.id.btn_calculate)).perform(click())
        onView(withId(R.id.tips_amount)).check(matches(withText(containsString("10,00"))))

    }
}