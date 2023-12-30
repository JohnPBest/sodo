package com.best.sodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.best.sodo.ui.theme.SoDoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SoDoTheme {
                Surface(
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.fillMaxSize()
                ) {
                    val vm by viewModels<SoDoViewModel>()
                    Home(vm)
                }
            }
        }
    }
}

@Composable
fun Home(vm: SoDoViewModel) {
    val sodos by vm.sodos.collectAsState()

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        SoDoList(sodos = sodos)

        SoDoForm(onAddButtonClick = { vm.add(it) })
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    SoDoTheme {
        Surface(
            color = MaterialTheme.colorScheme.secondary,
        ) {
            Home(SoDoViewModel())
        }
    }
}