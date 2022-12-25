package com.project.oplevapp.ui.view.profilePage;

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import com.project.oplevapp.ui.theme.OplevAppTheme
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.dp
import com.project.oplevapp.ui.Utility.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.RectangleShape
import com.project.oplevapp.R

class ProfileSettingsActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContent {
                        OplevAppTheme() {
                                ProfilePage()
                        }
                }
        }
}

@Composable
private fun ProfileIcon(modifier: Modifier = Modifier) {
        Surface(modifier = modifier.padding(8.dp), shape = CircleShape, elevation = 2.dp) {
                Image(painter = painterResource(id = R.drawable.profile_image),
                        contentDescription = stringResource(id = R.string.profile_image)
                )
        }
}

@Composable
fun ProfilePage() {
        var isInPage: Boolean by remember {
                mutableStateOf(false)
        }
        Surface(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()) {
                Card(modifier = Modifier.padding(70.dp), elevation = 20.dp, shape = MaterialTheme.shapes.medium) {
                        Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
                                ProfileIcon(modifier = Modifier.size(160.dp))
                                Text("Hans Hansen", style = MaterialTheme.typography.h4)
                                Text(text = "Jeg glæder mig!", style = MaterialTheme.typography.h5,)
                                Button(onClick = { isInPage = !isInPage }) {
                                        Text(text = "Min Profil")
                                }
                                if (!isInPage) {
                                } else {
                                        ProfileBox(showUserProfileInfo())
                                }
                        }
                }
        }
}

@Composable
fun ProfileBox(settings: List<ProfileSetting>) {
        Box {
                Surface() {
                        Column {
                                LazyColumn {
                                        items(settings) { setting ->
                                                ProfileBoxLayout(setting)
                                        }
                                }
                        }
                }
        }

}

@Composable
fun ProfileBoxLayout(setting: ProfileSetting) {
        Card(modifier = Modifier
                .fillMaxWidth()
                .padding(50.dp),
                shape = RectangleShape, elevation = 10.dp) {
                Row(modifier = Modifier.padding(start = 20.dp),
                        verticalAlignment = Alignment.CenterVertically) {
                        Column(horizontalAlignment = Alignment.Start) {
                                Text(text = setting.name)
                                Text(text = setting.value)
                        }
                }
        }
}

fun showUserProfileInfo(): List<ProfileSetting> =
        listOf(ProfileSetting("Hans Hansen", "Hans@Hansen.dk"))


@Preview(showBackground = true)
@Composable
fun DefaultPreview1() {
        OplevAppTheme() {
                ProfilePage()
        }
}