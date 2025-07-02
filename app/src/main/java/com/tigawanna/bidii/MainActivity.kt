package com.tigawanna.bidii

import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tigawanna.bidii.ui.theme.BidiiTheme
import androidx.compose.material3.Button
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.launch

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.glance.appwidget.updateAll

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BidiiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SetTimeButton(
                        modifier = Modifier.padding(innerPadding),
                        context = applicationContext
                    )
                }
            }
        }
    }
}

@Composable
fun SetTimeButton(modifier: Modifier = Modifier, context: Context) {
    val scope = rememberCoroutineScope()
    val hours = "90:00"

    Button(
        onClick = {
            scope.launch {
                try {
                    // 1. Update application state (DataStore)
                    context.dataStore.edit { settings ->
                        settings[BidiiWidgetConstants.WAKATIME_HOURS_KEY] = hours
                    }

                    // 2. Update widgets (two documented approaches)

                    // Approach A: Simple update all (preferred for most cases)
                    BidiiHoursWidget().updateAll(context)

                    /* Approach B: More granular control if needed
                    val manager = GlanceAppWidgetManager(context)
                    manager.getGlanceIds(BidiiHoursWidget::class.java).forEach { id ->
                        BidiiHoursWidget().update(context, id)
                    }
                    */
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        },
        modifier = modifier.padding(16.dp)
    ) {
        Text(text = "Set Time to $hours")
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
            .fillMaxSize()
            .padding(56.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BidiiTheme {
        Greeting("Android")
    }
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = BidiiWidgetConstants.DATASTORE_NAME)