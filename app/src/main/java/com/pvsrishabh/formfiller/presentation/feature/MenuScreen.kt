package com.pvsrishabh.formfiller.presentation.feature

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.ai.client.generativeai.type.content
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.pvsrishabh.formfiller.R

data class MenuItem(
    val routeId: String,
    @DrawableRes val iconResId: Int,
    val titleResId: Int,
    val descriptionResId: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    onItemClicked: (String) -> Unit = { }
) {
    val menuItems = listOf(
        MenuItem(
            "form_filler",
            R.drawable.form_icon,
            R.string.menu_form_title,
            R.string.menu_form_description
        ),
        MenuItem(
            "summarize",
            R.drawable.text_icon,
            R.string.menu_summarize_title,
            R.string.menu_summarize_description
        ),
        MenuItem(
            "photo_reasoning",
            R.drawable.ic_text_image,
            R.string.menu_reason_title,
            R.string.menu_reason_description
        ),
        MenuItem(
            "chat",
            R.drawable.ic_chat,
            R.string.menu_chat_title,
            R.string.menu_chat_description
        )
    )
    val context = LocalContext.current
    val currUser = Firebase.auth.currentUser
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        if (currUser == null) {
            Icon(modifier = Modifier
                .size(100.dp)
                .clip(CircleShape),
                painter = painterResource(id = R.drawable.no_image), contentDescription = null)
        } else {
            AsyncImage(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                model = ImageRequest.Builder(context).data(currUser.photoUrl).build(),
                placeholder = painterResource(id = R.drawable.no_image),
                contentDescription = "User Image"
            )
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(all = 12.dp)
        ) {
            items(menuItems) { menuItem ->
                var isExpanded by remember { mutableStateOf(false) }
                Card(
                    modifier = Modifier
                        .padding(horizontal = 5.dp, vertical = 8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 7.dp)
                            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            painter = painterResource(id = menuItem.iconResId),
                            contentDescription = null,
                            modifier = Modifier
                                .size(140.dp)
                                .padding(vertical = 15.dp)
                        )
                        Text(
                            text = stringResource(menuItem.titleResId),
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = stringResource(menuItem.descriptionResId),
                            style = MaterialTheme.typography.labelMedium,
                            maxLines = if (isExpanded) Int.MAX_VALUE else 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .clickable { isExpanded = !isExpanded }
                                .animateContentSize()
                        )
                        TextButton(
                            onClick = {
                                onItemClicked(menuItem.routeId)
                            },
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text(text = stringResource(R.string.action_try))
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MenuScreenPreview() {
    MenuScreen()
}
