package com.hv.compose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hv.compose.ui.theme.ComposeTheme

class MyMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTheme {
                Scaffold(
                    topBar = {
                        TopArea()
                    },
                    bottomBar = {
                        BottomArea()
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    LightMagicApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

enum class LightMagicAppPage {
    Main,
    Custom,
    Creation,
}

@Composable
fun LightMagicApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = LightMagicAppPage.Main.name
    ) {
        composable(route = LightMagicAppPage.Main.name) {
            MainPage(
                onNext = {
                    navController.navigate(LightMagicAppPage.Custom.name)
                },
                modifier = modifier
            )
        }
        composable(route = LightMagicAppPage.Custom.name) {
            CustomPage(
                onNext = {
                    Toast.makeText(context, "创作页面", Toast.LENGTH_SHORT).show()
                },
                onBack = {
                    navController.navigate(LightMagicAppPage.Main.name)
                },
                modifier = modifier
            )
        }
    }
}

@Composable
fun MainPage(
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    MiddleArea(
        onNext = onNext,
        modifier = modifier
    )
}

@Composable
fun CustomPage(
    onNext: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    MiddleArea4Custom(
        onNext = onNext,
        onBack = onBack,
        modifier = modifier
    )
}

@Preview(showBackground = true, device = Devices.AUTOMOTIVE_1024p)
@Composable
fun LightMagicAppPreview() {
    LightMagicApp()
}