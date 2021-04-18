package com.example.delishdemo2.live

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun LiveScreen() {
    Scaffold(backgroundColor = Color(0xFFFAFAFA)) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (text) = createRefs()
            Text(text = "LIVE",
                color = Color.Black,
                modifier = Modifier
                    .constrainAs(text) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })
        }
    }
}