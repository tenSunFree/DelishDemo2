package com.example.delishdemo2.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.delishdemo2.ui.theme.ComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

@AndroidEntryPoint
class MainFragment : Fragment() {

    @SuppressLint("VisibleForTests")
    @ExperimentalFoundationApi
    @ExperimentalAnimationApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ComposeTheme {
                    ProvideWindowInsets {
                        MainNavGraph { id, title ->
                            val action = MainFragmentDirections.goToRecipesDetails(id, title)
                            findNavController().navigate(action)
                        }
                    }
                }
            }
        }
    }
}
