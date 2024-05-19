package com.pvsrishabh.formfiller.presentation.feature.form_filler

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.pvsrishabh.formfiller.R

@Composable
fun FormFillerScreen(
    state: FormFillerUiState,
    onValueChange: (String) -> Unit,
    onGoClick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent) // Set the background color to transparent
    ) {
        FormFillerScreenContent(state = state, onValueChange = onValueChange, onGoClick = { onGoClick(it) })
    }
}

@Composable
fun FormFillerScreenContent(
    state: FormFillerUiState,
    onValueChange: (String) -> Unit,
    onGoClick: (String) -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        ElevatedCard(
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.large
        ) {
            OutlinedTextField(
                value = state.text,
                singleLine = true,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                onValueChange = {
                    onValueChange(it)
                },
                placeholder = {
                    Text(text = "Enter the url")
                },
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone =
                    {
                        if (isValidUrl(state.text)) {
                            onGoClick(state.text)
                        } else {
                            Toast.makeText(
                                context,
                                "Please Enter the correct link",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextButton(
                modifier = Modifier
                    .padding(end = 16.dp, bottom = 16.dp)
                    .align(Alignment.End),
                onClick = {
                if (isValidUrl(state.text)) {
                    onGoClick(state.text)
                } else {
                    Toast.makeText(context, "Please Enter the correct link", Toast.LENGTH_SHORT)
                        .show()
                }
            }) {
                Text(text = stringResource(id = R.string.action_go))
            }
        }
    }
}

private fun isValidUrl(url: String): Boolean {
    return url.startsWith("https://forms.gle/") || url.startsWith("https://docs.google.com/forms/d/e/")
}