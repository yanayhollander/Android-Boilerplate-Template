package com.hollander.template.presentation.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.chuckerteam.chucker.api.Chucker
import com.hollander.template.BuildConfig
import com.hollander.template.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppScaffold(
    title: String,
    showBackButton: Boolean = false,
    onBackButtonClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    Scaffold(
        topBar =
        {
            CenterAlignedTopAppBar(
                title = {
                    ClickableText(
                        text = AnnotatedString(title),
                        onClick = {
                            if (BuildConfig.DEBUG) {
                                startActivity(context, Chucker.getLaunchIntent(context), null)
                            }
                        },
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                    )
                },
                navigationIcon = {
                    if (showBackButton) {
                        // Back arrow for navigating up
                        IconButton(onClick = onBackButtonClick) {
                            Icon(
                                Icons.Default.ArrowBack,
                                contentDescription = LocalContext.current.getString(R.string.back),
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    } else {
//                        // Navigation icon (usually a hamburger icon)
//                        IconButton(onClick = { /* Handle navigation icon click */ }) {
//                            Icon(Icons.Default.Menu, contentDescription = null)
//                        }
                    }
                }
            )
        }
    )
    {
        Column(modifier = Modifier.padding(it)) {
            content()
        }
    }
}

@Preview
@Composable
fun AppScaffoldPreview() {
    AppScaffold(title = "Title") {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondary)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            (1..100).map { Text(text = "Text ${it}") }
        }
    }
}

@Preview
@Composable
fun AppScaffoldWithBackArrowPreview() {
    AppScaffold(title = "Title with back arrow", showBackButton = true) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondary)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            (1..100).map { Text(text = "Text ${it}") }
        }
    }
}
