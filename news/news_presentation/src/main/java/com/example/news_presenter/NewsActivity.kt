package com.example.news_presenter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common_utils.Navigator
import com.example.news_domain.model.Article
import com.example.news_presenter.ui.theme.MultiModuleProjectNewTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsActivity : ComponentActivity() {

    private val newsViewModel : NewsViewModel by viewModels()

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
                        } else if (state.error?.isNotBlank() == true) {
                            Text(
                                text = state.error!!,
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

@Composable
fun NewsList(articles: List<Article>) {
    LazyColumn {
        items(articles) { article ->
            ArticleItem(article)
        }
    }
}

@Composable
fun ArticleItem(article : Article/*, onClick : (category : String) -> Unit*/) {
    Box(modifier = Modifier
        .padding(4.dp)
        /*.clickable{
            onClick(category)
        }*/
        .fillMaxSize()
        .clip(RoundedCornerShape(8.dp))
        /*.paint(
            painterResource(R.drawable.bg),
            contentScale = ContentScale.Crop
        )*/
        .border(1.dp, Color(0XFFEEEEEE))) {
        Column {
            Text(text = article.title,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(0.dp, 5.dp),
                style = MaterialTheme.typography.bodyMedium)
            Text(
                text = article.content,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(0.dp, 5.dp),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = article.author,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(0.dp, 5.dp),
                style = MaterialTheme.typography.bodyMedium
            )
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