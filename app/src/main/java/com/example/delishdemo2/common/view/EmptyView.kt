package com.example.delishdemo2.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.delishdemo2.R

@Composable
fun EmptyView(
    modifier: Modifier = Modifier,
    titleText: String? = "",
    descText: String? = ""
) {
    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (image, title, desc) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.recipe_empty),
            contentDescription = "empty",
            modifier = Modifier.constrainAs(image) {
                linkTo(
                    start = parent.start,
                    end = parent.end,
                    top = parent.top,
                    bottom = parent.bottom
                )
            }
        )
        Text(
            text = titleText ?: "",
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .constrainAs(title) {
                    linkTo(
                        start = parent.start,
                        end = parent.end,
                    )
                    top.linkTo(image.bottom)
                }
        )
        Text(
            text = descText ?: "",
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .constrainAs(desc) {
                    linkTo(
                        start = parent.start,
                        end = parent.end,
                    )
                    top.linkTo(title.bottom)
                }
        )
    }
}
