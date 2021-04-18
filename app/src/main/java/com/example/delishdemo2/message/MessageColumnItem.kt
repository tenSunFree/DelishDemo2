package com.example.delishdemo2.message

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.delishdemo2.common.local.model.RecipesItem
import com.example.delishdemo2.ui.theme.compositedOnSurface
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun InspirationItem(
    recipe: RecipesItem,
    modifier: Modifier = Modifier,
    onDetails: (Int, String) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .clickable {
                onDetails(recipe.id ?: 0, recipe.title ?: "")
            }
    ) {
        val (image, title) = createRefs()
        Surface(
            modifier = modifier
                .width(60.dp)
                .height(60.dp)
                .constrainAs(image) {},
            color = MaterialTheme.colors.background,
            elevation = 8.dp,
            shape = RoundedCornerShape(8.dp)
        ) {
            CoilImage(
                data = recipe.image ?: "", contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                loading = {
                    Spacer(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                MaterialTheme.colors.compositedOnSurface(alpha = 0.2f)
                            )
                    )
                }
            )
        }
        Text(
            text = recipe.title ?: "",
            color = Color.Black,
            maxLines = 1,
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier
                .padding(start = 16.dp)
                .constrainAs(title) {
                    width = Dimension.fillToConstraints
                    linkTo(
                        start = image.end,
                        end = parent.end,
                        top = parent.top,
                        bottom = parent.bottom,
                    )
                }
        )
    }
}