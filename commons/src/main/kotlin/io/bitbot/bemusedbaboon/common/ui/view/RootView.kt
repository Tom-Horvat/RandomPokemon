@file:OptIn(ExperimentalMaterial3Api::class)

package io.bitbot.bemusedbaboon.common.ui.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.bitbot.bemusedbaboon.common.ui.view.components.PopUpDialog
import io.bitbot.bemusedbaboon.common.ui.view.components.TitledBottomSheet
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootView(
    navDrawer: @Composable () -> Unit = {},
    drawerValue: DrawerValue = DrawerValue.Closed,
    topBar: @Composable (TopAppBarScrollBehavior) -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    bottomSheet: @Composable () -> Unit = {},
    showBottomSheet: Boolean = false,
    onBottomSheetDismissed: () -> Unit = {},
    popUpDialog: @Composable (() -> Unit)? = null,

    content: @Composable (PaddingValues) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = drawerValue)
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    BackHandler(enabled = drawerState.isOpen) {
        scope.launch {
            drawerState.close()
        }
    }

    LaunchedEffect(key1 = showBottomSheet) {
        scope.launch {
            if (showBottomSheet) sheetState.show() else sheetState.hide()
        }
    }

    ModalNavigationDrawer(
        modifier = Modifier.fillMaxSize(),
        drawerContent = { navDrawer() },
        drawerState = drawerState,
    ) {

        Box(modifier = Modifier.fillMaxSize()) {
            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = { topBar(scrollBehavior) },
                bottomBar = { bottomBar() },
            ) { padding ->
                Box(modifier = Modifier.fillMaxSize()) { content(padding) }

                if (sheetState.isVisible)
                    ModalBottomSheet(
                        onDismissRequest = { onBottomSheetDismissed() },
                        sheetState = sheetState
                    ) { bottomSheet() }

                popUpDialog?.let { it() }
            }
        }
    }
}

@Preview
@Composable
private fun RootViewContent() {
    RootView {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Lorem Ipsum",
                modifier = Modifier
                    .align(Alignment.Center),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun RootViewTopBarContent() {
    RootView(
        topBar = {
            TopAppBar(
                title = { Text("TopBar title") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Filled.Menu,
                            contentDescription = null,
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Filled.Add,
                            contentDescription = null,
                        )
                    }
                },
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Lorem Ipsum",
                modifier = Modifier
                    .align(Alignment.Center),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun RootViewDrawerTopBarContent() {
    RootView(
        navDrawer = {
            ModalDrawerSheet {
                Text("Drawer title", modifier = Modifier.padding(16.dp))
                HorizontalDivider()
                NavigationDrawerItem(
                    label = { Text(text = "Drawer Item") },
                    selected = false,
                    onClick = { }
                )
            }
        },
        drawerValue = DrawerValue.Open,
        topBar = {
            TopAppBar(
                title = { Text("TopBar title") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Filled.Menu,
                            contentDescription = null,
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Filled.Add,
                            contentDescription = null,
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Lorem Ipsum",
                modifier = Modifier
                    .align(Alignment.Center),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun RootViewTopBarBottomSheetContent() {
    RootView(
        bottomSheet = {
            TitledBottomSheet(
                title = "Bottom sheet",
                description = "Some long description about this sheet."
            )
        },
        showBottomSheet = true,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Lorem Ipsum",
                modifier = Modifier
                    .align(Alignment.Center),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun RootViewBottomBarContent() {
    RootView(
        topBar = {
            TopAppBar(
                title = { Text("TopBar title") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Filled.Menu,
                            contentDescription = null,
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Filled.Add,
                            contentDescription = null,
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.DarkGray)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(
                    onClick = {},
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.AccountBox,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                IconButton(
                    onClick = {},
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.AccountBox,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                IconButton(
                    onClick = {},
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.AccountBox,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Lorem Ipsum",
                modifier = Modifier
                    .align(Alignment.Center),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
fun RootViewWithDialog() {
    RootView(
        popUpDialog = {
            PopUpDialog(
                title = "Some title",
                onDismiss = {},
                onOk = {},
                content = { Text("Some text") }
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Lorem Ipsum",
                modifier = Modifier
                    .align(Alignment.Center),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
