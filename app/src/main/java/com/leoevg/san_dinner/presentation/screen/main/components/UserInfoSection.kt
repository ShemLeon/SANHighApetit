package com.leoevg.san_dinner.presentation.screen.main.components

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.border
import androidx.compose.foundation.background

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
            .padding(bottom = 5.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            BasicTextField(
                value = firstName,
                onValueChange = onNameChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .padding(horizontal = 12.dp, vertical = 10.dp),
                singleLine = true,
                decorationBox = { innerTextField ->
                    if (firstName.isEmpty()) {
                        Text(namePlaceholder, color = Color.Gray)
                    }
                    innerTextField()
                }
            )

            BasicTextField(
                value = workerID,
                onValueChange = onWorkerIDChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .padding(horizontal = 12.dp, vertical = 10.dp),
                singleLine = true,
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
        firstName = "",
        workerID = "",
        onNameChange = {},
        onWorkerIDChange = {}
    )
}