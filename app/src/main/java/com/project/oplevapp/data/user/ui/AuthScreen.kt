package com.project.oplevapp.data.user.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.project.oplevapp.data.user.ResultState
import com.project.oplevapp.data.user.User
import com.project.oplevapp.data.user.utils.showMsg
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
//Hjælp til opsætning fra https://www.youtube.com/watch?v=G1sD6SGx1Ts
@Composable
fun AuthScreen1(
    viewModel:AuthViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email1 by remember { mutableStateOf("") }
    var password1 by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var isDialog by remember { mutableStateOf(false) }

    if (isDialog)
        CommonDialog()

    LazyColumn(
        modifier = Modifier.padding(20.dp)
    ) {

        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Register")
                Spacer(modifier = Modifier.height(10.dp))
                TextField(value = email, onValueChange = {
                    email = it
                },
                    placeholder = { Text("Enter Email") }
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(value = password, onValueChange = {
                    password = it
                },
                    placeholder = { Text("Enter Password") }
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = {
                    scope.launch(Dispatchers.Main) {
                        viewModel.createUser(
                            User(
                                email,
                                password
                            )
                        ).collect {
                            isDialog = when (it) {
                                is ResultState.Success -> {
                                    context.showMsg(it.data)
                                    false
                                }
                                is ResultState.Failure -> {
                                    context.showMsg(it.msg.toString())
                                    false
                                }
                                ResultState.Loading -> {
                                    true
                                }
                            }
                        }
                    }
                }) {
                    Text(text = "Register")
                }
            }
        }
        item {
            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Login")
                Spacer(modifier = Modifier.height(10.dp))
                TextField(value = email1, onValueChange = {
                    email1 = it
                },
                    placeholder = {Text("Enter Email")}
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(value = password1, onValueChange = {
                    password1 = it
                },
                    placeholder = {Text("Enter Password")}
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = {
                    scope.launch(Dispatchers.Main){
                        viewModel.loginUser(
                            User(
                                email1,
                                password1
                            )
                        ).collect{
                            isDialog = when(it){
                                is ResultState.Success -> {
                                    context.showMsg(it.data)
                                    false
                                }
                                is ResultState.Failure->{
                                    context.showMsg(it.msg.toString())
                                    false
                                }
                                ResultState.Loading->{
                                    true
                                }
                            }
                        }
                    }
                }) {
                    Text(text = "Login")
                }
            }
        }


        @Composable
        fun AuthScreen(
            viewModel: AuthViewModel = hiltViewModel(),
        ) {
            var email by remember {
                mutableStateOf("")
            }
            var password by remember {
                mutableStateOf("")
            }
            val scope = rememberCoroutineScope()
            val context = LocalContext.current
            var isDialog by remember {
                mutableStateOf(false)
            }

            if (isDialog) {
                CommonDialog()
            }


            LazyColumn {

                item {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Opret konto")
                        Spacer(modifier = Modifier.height(10.dp))
                        TextField(value = email, onValueChange = {
                            email = it
                        },
                            placeholder = { Text(text = "Indtast email") }
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        TextField(value = password, onValueChange = {
                            password = it
                        },
                            placeholder = { Text(text = "Indtast kodeord") }
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(onClick = {
                            scope.launch(Dispatchers.Main) {
                                viewModel.createUser(
                                    User(
                                        email, password
                                    )
                                ).collect {
                                    isDialog = when (it) {
                                        is ResultState.Success -> {
                                            context.showMsg(it.data)
                                            false

                                        }
                                        is ResultState.Failure -> {
                                            context.showMsg(it.msg.toString())
                                            false
                                        }
                                        is ResultState.Loading -> {
                                            true
                                        }
                                    }
                                }
                            }
                        }) {

                        }

                    }
                }
            }
        }
    }
}

@Composable
fun CommonDialog() {

    Dialog(onDismissRequest = { }) {
        CircularProgressIndicator()
    }

}

