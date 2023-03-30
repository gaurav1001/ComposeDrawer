package com.example.composedrawer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composedrawer.ui.theme.ComposeDrawerTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Content()
        }
    }
}

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun Content(){
        val scaffoldState:ScaffoldState = rememberScaffoldState()
        val scope:CoroutineScope = rememberCoroutineScope()
      Scaffold(
          scaffoldState = scaffoldState,
          topBar = {TopAppBar(scaffoldState,scope)},
          drawerContent = { NavDrawable(scaffoldState,scope)}
          ) {
          Column(modifier = Modifier.fillMaxSize(),
              verticalArrangement = Arrangement.Center,
              horizontalAlignment = Alignment.CenterHorizontally

          ) {
              Text(text = "Hello World", fontSize = 28.sp, fontWeight = FontWeight.Bold)
          }
      }
    }

    @Preview(showBackground = true)
    @Composable
    fun ContentPreview(){
        Content()
    }

    @Composable
    fun TopAppBar(scaffoldState: ScaffoldState?,scope: CoroutineScope?){
        Row(
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = {scope?.launch { scaffoldState?.drawerState?.open() }}) {
                Icon(imageVector = Icons.Default.Menu,
                    contentDescription = "menu",
                    )
            }
            Text(text = "LOGO",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
                )
        }
    }

@Composable
fun NavDrawable(scaffoldState: ScaffoldState ,scope: CoroutineScope){
   List(1){
      Text(text = "Home", modifier = Modifier.padding(20.dp,10.dp))
      Text(text = "About",modifier = Modifier.padding(20.dp,10.dp)) 
      Text(text = "Contact", modifier = Modifier.padding(20.dp,10.dp)) 
   }

    IconButton(onClick = { scope.launch { scaffoldState.drawerState.close() } }) {
        Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "close")
    }

}

