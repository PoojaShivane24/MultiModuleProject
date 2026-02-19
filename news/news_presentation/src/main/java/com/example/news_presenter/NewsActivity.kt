package com.example.news_presenter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.common_utils.Navigator
import com.example.news_presenter.ui.theme.MultiModuleProjectNewTheme

class NewsActivity : ComponentActivity() {

    companion object {
        fun lunchNewsActivity(activity: Activity) {
            val intent = Intent(activity, NewsActivity::class.java)
            activity.startActivity(intent)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MultiModuleProjectNewTheme {
                val state by newsViewModel.newsArticle.collectAsState()
                Scaffold(topBar = {
                    TopAppBar(title = { Text(text = "News Article",
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif) } )
                }) { innerPadding ->

                    Column {
                        Greeting(
                            name = "Android news activity",
                            modifier = Modifier.padding(innerPadding)
                        )

                        if (state.isLoading) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        } else if (state.error.isNotBlank()) {
                            Text(
                                text = state.error,
                                color = Color.Red,
                            )

                        } else {
                            NewsList(
                                state.data ?: emptyList()
                            )
                        }

                    }

                }
            }
        }
    }
}

object GotoNewsActivity : Navigator {
    override fun navigate(activity: Activity) {
        NewsActivity.lunchNewsActivity(activity)
    }

}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MultiModuleProjectNewTheme {
        Greeting("Android")
    }
}