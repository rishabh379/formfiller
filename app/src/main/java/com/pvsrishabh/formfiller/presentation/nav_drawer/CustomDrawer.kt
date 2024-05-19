package com.pvsrishabh.formfiller.presentation.nav_drawer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text

@Composable
fun CustomDrawer(
    items: List<MenuItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 16.sp),
    onCloseClick: () -> Unit,
    isLoading: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(fraction = 0.6f)
            .padding(top = 15.dp, start = 10.dp)
        ,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = onCloseClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back Arrow Icon",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            Text(
                text = "Answers",
                modifier = Modifier.padding(vertical = 16.dp),
                style = itemTextStyle.copy(fontWeight = FontWeight.Bold)
            )
        }
        if (isLoading) {
            // Show loading indicator
            Column(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(40.dp))
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Loading answers...",
                    modifier = Modifier.padding(16.dp),
                    style = itemTextStyle
                )
            }
        } else if (items.isEmpty()) {
            // Show empty state
            Text(
                text = "Not available",
                modifier = Modifier.padding(16.dp),
                style = itemTextStyle
            )
        } else {
            LazyColumn(modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 10.dp)) {
                itemsIndexed(items) { index, item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = 15.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${index + 1}. ",
                                style = itemTextStyle,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = item.text,
                                modifier = Modifier.weight(1f),
                                style = itemTextStyle,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}