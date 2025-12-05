package com.leoevg.san_dinner.presentation.screen.main.components

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leoevg.san_dinner.R
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
    val focusManager = LocalFocusManager.current
    
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
            .padding(bottom = 5.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Name Field
            var isNameFocused by remember { mutableStateOf(false) }
            BasicTextField(
                value = firstName,
                onValueChange = onNameChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .padding(horizontal = 12.dp, vertical = 10.dp)
                    .onFocusChanged { isNameFocused = it.isFocused },
                singleLine = true,
                textStyle = TextStyle(
                    color = if (isNameFocused) Color.Black else Color.Gray
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                ),
                decorationBox = { innerTextField ->
                    if (firstName.isEmpty()) {
                        Text(namePlaceholder, color = Color.Gray)
                    }
                    innerTextField()
                }
            )

            // Worker ID Field
            var isIdFocused by remember { mutableStateOf(false) }
            BasicTextField(
                value = workerID,
                onValueChange = onWorkerIDChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .padding(horizontal = 12.dp, vertical = 10.dp)
                    .onFocusChanged { isIdFocused = it.isFocused },
                singleLine = true,
                textStyle = TextStyle(
                    color = if (isIdFocused) Color.Black else Color.Gray
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                ),
                decorationBox = { innerTextField ->
                    if (workerID.isEmpty()) {
                        Text(workerIdPlaceholder, color = Color.Gray)
                    }
                    innerTextField()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserInfoSectionPreview() {
    UserInfoSection(
        firstName = "Leonid",
        workerID = "2458",
        onNameChange = {},
        onWorkerIDChange = {}
    )
}
