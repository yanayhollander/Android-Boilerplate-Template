package com.hollander.template.presentation.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    Scaffold(
        topBar =
        {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
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
