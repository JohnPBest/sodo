package com.best.sodo

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.best.sodo.ui.theme.SoDoTheme
import java.util.UUID

data class SoDo(
    var title: String = "Title",
    var message: String = "Message"
) {
    val id: String = UUID.randomUUID().toString()
}

@Preview(showBackground = true)
@Composable
fun PreviewSoDoList() {
    SoDoTheme {
        val sodos = listOf(
            SoDo("Breakfast", "Eat breakfast."),
            SoDo("Lunch", "Eat lunch.")
        )
        SoDoList(sodos = sodos)
    }
}

@Composable
fun SoDoList(sodos: List<SoDo>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(sodos) {
            SoDo(sodo = it)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSoDo() {
    SoDoTheme {
        SoDo(SoDo())
    }
}

@Composable
fun SoDo(sodo: SoDo) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        Column {
            Text(
                text = sodo.title,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(8.dp)
            )

            Text(
                text = sodo.message,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(8.dp)
            )

            Text(
                text = "id: ${sodo.id}",
                fontSize = 8.sp,
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSoDoForm() {
    SoDoTheme {
        SoDoForm(onAddButtonClick = { Log.i("SoDoForm", "Adding new SoDo") })
    }
}

@Composable
fun SoDoForm(
    onAddButtonClick: (SoDo) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = "New SoDo",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(12.dp)
            )

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(text = "Title") }
            )

            OutlinedTextField(
                value = message,
                onValueChange = { message = it },
                label = { Text(text = "Message") }
            )

            Spacer(modifier = Modifier.padding(8.dp))

            Button(
                onClick = {
                    val sodo = SoDo(title, message)
                    title = ""
                    message = ""
                    onAddButtonClick(sodo)
                }
            ) {
                Text(text = "Add")
            }
        }
    }
}