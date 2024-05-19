package com.pvsrishabh.formfiller.presentation.form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pvsrishabh.formfiller.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    modifier: Modifier = Modifier,
    url: String,
    tabCount: Int = 5,
    onClick: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Black,
            navigationIconContentColor = Color.White,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        ),
        navigationIcon = {
            IconButton(onClick = { onClick() }) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = com.pvsrishabh.formfiller.R.drawable.ic_home),
                    contentDescription = "Home"
                )
            }
        },
        title = {
            Row(
                modifier = Modifier
                    .height(38.dp)
                    .background(color = Color.DarkGray, shape = RoundedCornerShape(20.dp)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = "Search",
                    tint = Color.White,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(18.dp)
                )
                Text(
                    text = url.removePrefix("https://"),
                    style = TextStyle(fontSize = 16.sp, color = Color.White),
                    maxLines = 1,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        },
        actions = {
            IconButton(onClick = { /* Action for add button */ }) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = "Add",
                )
            }
            IconButton(
                onClick = {}) {
                Box(
                    modifier = Modifier.size(28.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .offset(y = 0.dp) // Offset to position the text above the icon
                            ,
                        text = tabCount.toString()
                    )
                    Icon(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(20.dp), // Adjust the size as needed
                        painter = painterResource(id = R.drawable.ic_tab),
                        contentDescription = "Tab Count"
                    )
                }
            }
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_more),
                    contentDescription = "More Options"
                )
            }
        }
    )
}