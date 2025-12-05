package com.leoevg.san_dinner.presentation.screen.main.components

import android.content.res.Configuration
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leoevg.san_dinner.R
import com.leoevg.san_dinner.ui.theme.Purple40
import com.leoevg.san_dinner.ui.theme.Purple80
import com.leoevg.san_dinner.presentation.screen.main.components.DishCard
import java.util.Locale

@Composable
fun CourseSection(
    title: String,
    isChosen: Boolean = false,
    chosenColorBg: Color = Purple80,
    chosenColorText: Color = Purple40,
    language: String = "RU",
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
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
    val selectedText = localizedContext.getString(R.string.selected)

    Column(
        modifier = Modifier.padding(bottom = 5.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF581C87)
            )

            if (isChosen) {
                Surface(
                    shape = RoundedCornerShape(50),
                    color = chosenColorBg
                ) {
                    Text(
                        text = selectedText,
                        modifier = Modifier.padding(horizontal = 5.dp, vertical = 4.dp),
                        fontSize = 12.sp,
                        color = chosenColorText,
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            content = { content() }
        )
    }
}

@Preview(showBackground = true, name = "Course Section Preview")
@Composable
fun CourseSectionPreview() {
    Column(modifier = Modifier.padding(2.dp)) {
        CourseSection(
            title = "Main course",
            isChosen = true,
            language = "EN"
        ) {
            DishCard(
                title = "Soup",
                imageUrl = null,
                isSelected = true,
                onSelect = {},
                modifier = Modifier.width(140.dp)
            )
            DishCard(
                title = "Salad with a very long name that wraps to the next line",
                imageUrl = null,
                isSelected = false,
                onSelect = {},
                modifier = Modifier.width(140.dp)
            )
        }
        CourseSection(
            title = "Side dish",
            isChosen = false,
            language = "EN"
        ) {
            DishCard(
                title = "French Fries",
                imageUrl = null,
                isSelected = false,
                onSelect = {},
                modifier = Modifier.width(140.dp)
            )
        }
    }
}
