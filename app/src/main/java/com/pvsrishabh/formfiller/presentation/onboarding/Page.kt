package com.pvsrishabh.formfiller.presentation.onboarding

import androidx.annotation.DrawableRes
import com.pvsrishabh.formfiller.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Get Started",
        description = "Learn how our app simplifies form completion for users, providing a hassle-free experience.",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Quick Form Entry",
        description = "Speed up data entry with our intuitive interface, enabling users to effortlessly input information into forms.",
        image = R.drawable.onboarding2
    ),Page(
        title = "AI-Powered Form Assistance",
        description = "Experience the power of AI technology as our app seamlessly integrates with chatbots to automate form filling, boosting accuracy and efficiency.",
        image = R.drawable.onboarding3
    )
)