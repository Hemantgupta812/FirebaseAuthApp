package com.example.firebaseauthapp.pages

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.firebaseauthapp.viewmodel.AuthState
import com.example.firebaseauthapp.viewmodel.AuthViewModel

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel
) {

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Error -> Toast.makeText(context,(authState.value as AuthState.Error).message,
                Toast.LENGTH_SHORT).show()
            AuthState.Loading -> Unit
            AuthState.UnAuthenticated ->navController.navigate("login")
            null -> Unit
            AuthState.Authenticated -> Unit
        }

    }
    Column (
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Text(text = "Welcome To Home Page", fontSize = 24.sp)
        
        TextButton(onClick = { authViewModel.signOut() }) {
            Text(text = " Sign Out ")
            
        }

    }

}