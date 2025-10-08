package io.bitbot.bemusedbaboon.common.view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chair
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ContestTypePicker(
    modifier: Modifier = Modifier,
    selected: Int = 0,
    onTypeSelected: (Int) -> Unit = {}
) {
    SingleChoiceSegmentedButtonRow(
        modifier = modifier.fillMaxWidth(),
    ) {
        val contestTypes = listOf(
            Pair("COUCH", Icons.Default.Chair),
            Pair("LOCAL", Icons.Default.Groups),
            Pair("OPEN", Icons.Default.Mail)
        )

        contestTypes.forEachIndexed { index, pair ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = contestTypes.size
                ),
                onClick = { onTypeSelected(index) },
                selected = index == selected,
                icon = { Icon(pair.second, pair.first) },
                colors = SegmentedButtonDefaults.colors(
                    activeBorderColor = Color.Transparent,
                    activeContentColor = MaterialTheme.colorScheme.onPrimary,
                    activeContainerColor = MaterialTheme.colorScheme.primary,
                    inactiveBorderColor = Color.Transparent,
                    inactiveContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    inactiveContainerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) { Text(pair.first) }
        }
    }
}

@Preview
@Composable
fun ContestTypePickerPreview() {
    ContestTypePicker()
}