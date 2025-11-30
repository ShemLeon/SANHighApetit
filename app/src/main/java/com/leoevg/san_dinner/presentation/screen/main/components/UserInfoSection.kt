package com.leoevg.san_dinner.presentation.screen.main.components

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leoevg.san_dinner.R
import com.leoevg.san_dinner.ui.theme.Purple40

@Composable
fun UserInfoSection(
    firstName: String,
    workerID: String,
    onNameChange: (String) -> Unit,
    onWorkerIDChange: (String) -> Unit
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = firstName,
                onValueChange = onNameChange,
                placeholder = { Text(stringResource(R.string.name)) },
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
                placeholder = { Text(stringResource(R.string.workerID)) },
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