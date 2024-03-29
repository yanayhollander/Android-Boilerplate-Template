package com.hollander.template.presentation.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hollander.template.ui.theme.AndroidTemplateTheme

@Composable
fun ErrorComposable(message: String) {
    Text(
        text = "Error\n${message}",
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .testTag("ErrorComposable"),
    )
}

@Preview(showBackground = true)
@Composable
fun ErrorComposablePreview() {
    AndroidTemplateTheme {
        ErrorComposable("Illegal error")
    }
}