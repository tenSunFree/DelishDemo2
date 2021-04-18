package com.example.delishdemo2.message

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.delishdemo2.R
import com.example.delishdemo2.common.EmptyView
import com.example.delishdemo2.common.LoadingView

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun MessageScreen(
    viewModel: MessageViewModel,
    onDetails: (Int, String) -> Unit
) {
    val viewState by viewModel.viewState.collectAsState()
    val hasError by viewModel.hasError.collectAsState()
    val loading by viewModel.loading.collectAsState()
    LoadingView(loading) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            AnimatedVisibility(visible = !hasError) {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFFAFAFA))
                ) {
                    val (image, column) = createRefs()
                    val modifier = Modifier
                    Image(
                        painter = painterResource(R.drawable.icon_top_bar),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = null,
                        modifier = modifier
                            .aspectRatio(1440 / 588F)
                            .constrainAs(image) {
                                top.linkTo(parent.top)
                                linkTo(start = parent.start, end = parent.end)
                            }
                    )
                    LazyColumn(modifier = Modifier
                        .constrainAs(column) {
                            top.linkTo(image.bottom)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            height = Dimension.fillToConstraints
                        }) {
                        items(viewState.randomRecipes) { recipe ->
                            InspirationItem(recipe, onDetails = onDetails)
                        }
                        item { Spacer(modifier = Modifier.height(71.dp)) }
                    }
                }
            }
            AnimatedVisibility(visible = hasError) {
                EmptyView(
                    titleText = stringResource(id = R.string.ops),
                    descText = stringResource(id = R.string.something_went_wrong)
                )
            }
        }
    }
}

@Composable
fun InspirationEmptyContent() {
    Surface(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth()
            .height(150.dp),
        color = MaterialTheme.colors.background,
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.place_holder),
            contentScale = ContentScale.Crop,
            contentDescription = "empty view"
        )
    }
}
