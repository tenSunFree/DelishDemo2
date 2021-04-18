package com.example.delishdemo2.main

import androidx.annotation.StringRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.height
import androidx.compose.material.Scaffold
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.delishdemo2.*
import com.example.delishdemo2.home.HomeScreen
import com.example.delishdemo2.kSong.KSongScreen
import com.example.delishdemo2.live.LiveScreen
import com.example.delishdemo2.message.MessageScreen
import com.example.delishdemo2.message.MessageViewModel
import com.example.delishdemo2.mine.MineScreen
import dev.chrisbanes.accompanist.insets.navigationBarsPadding

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun MainScreen(
    viewModel: MessageViewModel,
    onDetails: (Int, String) -> Unit
) {
    val (selectedTab, setSelectedTab) = remember { mutableStateOf(MainTabs.Home) }
    val tabs = MainTabs.values()
    Scaffold(
        backgroundColor = MaterialTheme.colors.primarySurface,
        bottomBar = {
            BottomNavigation(
                modifier = Modifier.height(55.dp),
                backgroundColor = Color(0xFFFFFFFF)
            ) {
                tabs.forEach { tab ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                imageVector = tab.icon,
                                contentDescription = null
                            )
                        },
                        label = { Text(text = stringResource(id = tab.title)) },
                        selected = tab == selectedTab,
                        onClick = { setSelectedTab(tab) },
                        alwaysShowLabel = false,
                        selectedContentColor = Color(0xFFFC1462),
                        unselectedContentColor = Color(0xFFCDD0D3),
                        modifier = Modifier.navigationBarsPadding()
                    )
                }
            }
        }
    ) {
        when (selectedTab) {
            MainTabs.Home -> HomeScreen()
            MainTabs.Live -> LiveScreen()
            MainTabs.KSong -> KSongScreen()
            MainTabs.Message -> MessageScreen(viewModel, onDetails)
            MainTabs.Mine -> MineScreen()
        }
    }
}

enum class MainTabs(
    @StringRes val title: Int,
    val icon: ImageVector
) {
    Home(R.string.tab_home, Icons.Filled.Home),
    Live(R.string.tab_live, Icons.Filled.LiveTv),
    KSong(R.string.tab_k_song, Icons.Filled.Mic),
    Message(R.string.tab_message, Icons.Filled.Message),
    Mine(R.string.tab_mine, Icons.Filled.Person)
}
