package com.pvsrishabh.formfiller.presentation.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pvsrishabh.formfiller.R
import com.pvsrishabh.formfiller.presentation.Dimens.MediumPadding1
import com.pvsrishabh.formfiller.presentation.Dimens.MediumPadding2
import com.pvsrishabh.formfiller.ui.theme.FormFillerTheme
import com.pvsrishabh.formfiller.presentation.onboarding.Page
import com.pvsrishabh.formfiller.presentation.onboarding.pages

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page
) {
    Column(modifier = modifier) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            painter = painterResource(id = page.image),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        Text(
            text = page.title,
            modifier = Modifier.padding(horizontal = MediumPadding2),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.display_small),
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = page.description,
            modifier = Modifier.padding(horizontal = MediumPadding2),
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.text_medium),
        )
    }
}

@Preview(showBackground = true)
@Preview(showSystemUi = true)
@Composable
fun OnBoardingPagePreview(){
    FormFillerTheme {
        OnBoardingPage(page = pages[0])
    }
}