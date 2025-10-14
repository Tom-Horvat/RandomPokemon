package io.bitbot.bemusedbaboon.common.ui.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.bitbot.bemusedbaboon.common.ui.view.RootView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopUpDialog(
    title: @Composable (() -> Unit)? = null,
    onDismiss: () -> Unit,
    onOk: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    BasicAlertDialog(
        onDismissRequest = { onDismiss() }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                title?.let { it() }
                content()
                Spacer(modifier = Modifier.height(24.dp))
                onOk?.let {
                    TextButton(
                        onClick = it,
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("OK")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PopUpDialogPreview() {
    RootView {
        PopUpDialog(
            title = { StandardTitle(text = "Title") },
            onDismiss = {},
            onOk = {}
        ) {
            Text("Lorem ipsum")
        }
    }
}