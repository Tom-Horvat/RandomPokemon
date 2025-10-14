package io.bitbot.bemusedbaboon.common.ui.view.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.bitbot.bemusedbaboon.common.ui.view.RootView

@Composable
fun TitledBottomSheet(
    modifier: Modifier = Modifier,
    title: String? = null,
    description: String? = null,
    onOkClick: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        title?.let {
            StandardTitle(modifier = Modifier.padding(horizontal = 24.dp), text = it)
        }
        description?.let {
            Text(
                it,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            )
            Spacer(
                modifier = Modifier
                    .height(8.dp)
                    .fillMaxWidth()
            )
        }

        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
        ) { content() }

        TextButton(
            onClick = { onOkClick() },
            modifier = Modifier.padding(horizontal = 24.dp)
        ) { Text("OK") }
        Spacer(
            modifier = Modifier
                .height(16.dp)
                .fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun TitledBottomSheetPreview() {
    RootView(bottomSheet = {
        TitledBottomSheet(
            title = "Some title",
            description = "Some long description about what this sheet will be adressing"
        )
    }, showBottomSheet = true) {}
}