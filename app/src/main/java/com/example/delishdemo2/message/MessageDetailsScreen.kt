package com.example.delishdemo2.message

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun MessageDetailsScreen(
    args: MessageDetailsFragmentArgs
) {
    Scaffold(backgroundColor = Color(0xFFFAFAFA)) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (idText, titleText) = createRefs()
            createVerticalChain(
                idText, titleText,
                chainStyle = ChainStyle.Packed
            )
            Text(text = "ID︰${args.id}", modifier = Modifier
                .constrainAs(idText) {
                    top.linkTo(parent.top)
                    bottom.linkTo(titleText.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            Text(text = "Title︰${args.title}", modifier = Modifier
                .padding(16.dp)
                .constrainAs(titleText) {
                    top.linkTo(idText.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
        }
    }
}