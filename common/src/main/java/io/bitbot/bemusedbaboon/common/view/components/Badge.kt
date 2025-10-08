package io.bitbot.bemusedbaboon.common.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Badge(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White,
    backgroundColor: Color = Color.DarkGray,
) {
    Box(
        modifier = modifier
            .defaultMinSize(24.dp, 24.dp)
            .wrapContentSize()
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = textColor
        )
    }
}

@Composable
fun GreenBadge(status: String) {
    Badge(
        text = status,
        textColor = Color.Black,
        backgroundColor = Color(0xFF7DDE92)
    )
}

@Composable
fun PurpleBadge(status: String) {
    Badge(
        text = status,
        textColor = Color.White,
        backgroundColor = Color(0xFF4E4187)
    )
}

@Composable
fun BlueBadge(status: String) {
    Badge(
        text = status,
        textColor = Color.Black,
        backgroundColor = Color(0xFF3083DC)
    )
}

@Composable
fun TealBadge(status: String) {
    Badge(
        text = status,
        textColor = Color.Black,
        backgroundColor = Color(0xFF2EBFA5)
    )
}

@Composable
@Preview
fun BadgePreview() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Badge("0")
        GreenBadge("DRAFT")
        PurpleBadge("DRAFT")
        BlueBadge("DRAFT")
        TealBadge("DRAFT")
    }

}

