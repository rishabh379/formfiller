package com.pvsrishabh.formfiller.presentation.sign_in

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pvsrishabh.formfiller.R
import com.pvsrishabh.formfiller.presentation.common.BackgroundDesign

@Composable
fun SignInScreen(
    state: SignInState,
    onSignInClick: () -> Unit,
    onTextClick: () -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent) // Set the background color to transparent
    ) {
        BackgroundDesign() // Add the background design
        SignInScreenContent(state = state, onSignInClick = onSignInClick, onTextClick = onTextClick)
    }
}

@Composable
fun SignInScreenContent(
    state: SignInState,
    onSignInClick: () -> Unit,
    onTextClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 120.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.wic_logo),
            contentDescription = null,
            modifier = Modifier.size(370.dp)
        )

        Text(
            modifier = Modifier.padding(top = 55.dp),
            text = "Don't have an account?",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyMedium
        )

        Button(
            modifier = Modifier.height(68.dp).padding(vertical = 10.dp),
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.display_small)),
            onClick = onSignInClick,
            shape = RoundedCornerShape(
                topStart = 25.dp,
                bottomEnd = 25.dp,
                topEnd = 10.dp,
                bottomStart = 10.dp
            )
        ) {
            Text(
                text = "Continue with",
                style = MaterialTheme.typography.bodyMedium
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_google),
                contentDescription = "Google Icon",
                modifier = Modifier.size(50.dp),
                tint = Color.Unspecified
            )
        }


        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "or",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium
            )

            TextButton(
                modifier = Modifier.height(34.dp),
                onClick = onTextClick
            ) {
                Text(
                    text = "As a Guest",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontStyle = FontStyle(R.font.michroma),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }

}