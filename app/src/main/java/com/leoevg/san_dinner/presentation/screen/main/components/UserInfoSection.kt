package com.leoevg.san_dinner.presentation.screen.main.components

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leoevg.san_dinner.R
import com.leoevg.san_dinner.ui.theme.Purple40
import java.util.Locale

@Composable
fun UserInfoSection(
    firstName: String,
    workerID: String,
    onNameChange: (String) -> Unit,
    onWorkerIDChange: (String) -> Unit,
    language: String = "RU"
) {
    val context = LocalContext.current
    @SuppressLint("ConfigurationScreenWidthDp")
    val localizedContext = remember(language) {
        val locale = when (language) {
            "RU" -> Locale("ru", "RU")
            "HE" -> Locale("he", "IL")
            "EN" -> Locale("en", "US")
            else -> Locale.getDefault()
        }
        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        context.createConfigurationContext(config)
    }
    val namePlaceholder = localizedContext.getString(R.string.name)
    val workerIdPlaceholder = localizedContext.getString(R.string.workerID)

    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = firstName,
                onValueChange = onNameChange,
                placeholder = { Text(namePlaceholder) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = Purple40
                )
            )

            OutlinedTextField(
                value = workerID,
                onValueChange = onWorkerIDChange,
                placeholder = { Text(workerIdPlaceholder) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = Purple40
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserInfoSectionPreview() {
    UserInfoSection(
        firstName = "",
        workerID = "",
        onNameChange = {},
        onWorkerIDChange = {}
    )
}