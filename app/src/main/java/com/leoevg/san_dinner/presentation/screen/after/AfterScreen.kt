package com.leoevg.san_dinner.presentation.screen.after

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.leoevg.san_dinner.R
import com.leoevg.san_dinner.presentation.navigation.Screen
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun AfterScreen(
    onNavigateTo: (Screen) -> Unit = {},
    viewModel: AfterScreenViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    
    // Create localized context - use state.language as key
    val localizedContext = remember(state.language) {
        val locale = when (state.language) {
            "RU" -> Locale("ru", "RU")
            "HE" -> Locale("he", "IL")
            "EN" -> Locale("en", "US")
            else -> Locale.getDefault()
        }
        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        context.createConfigurationContext(config)
    }

    OrderSuccessScreen(
        orderData = state.orderData, 
        localizedContext = localizedContext,
        language = state.language
    )
}

@Composable
fun OrderSuccessScreen(
    orderData: OrderData,
    localizedContext: android.content.Context,
    language: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFF3E8FF),
                        Color.White,
                        Color(0xFFEFF6FF)
                    )
                )
            )
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Карточка с данными заказа
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                color = Color.White,
                shadowElevation = 8.dp
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    // Заголовок
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = localizedContext.getString(R.string.order_accepted),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF581C87)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = localizedContext.getString(R.string.order_details),
                            fontSize = 14.sp,
                            color = Color(0xFF6B7280)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Разделитель
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(
                                Brush.horizontalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color(0xFFE5E7EB),
                                        Color.Transparent
                                    )
                                )
                            )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Данные заказа
                    
                    // Имя и фамилия
                    OrderDetailText(
                        label = localizedContext.getString(R.string.order_name),
                        value = "${orderData.firstName} ${orderData.lastName}".trim()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // ID работника
                    OrderDetailText(
                        label = localizedContext.getString(R.string.order_worker_id),
                        value = orderData.employeeId
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Разделитель
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color(0xFFF3F4F6))
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Основное меню
                    OrderDetailText(
                        label = localizedContext.getString(R.string.order_main_dish),
                        value = orderData.mainDish
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Добавка
                    OrderDetailText(
                        label = localizedContext.getString(R.string.order_side_dish),
                        value = orderData.sideDish
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Время заказа
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF9FAFB))
                            .padding(vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${localizedContext.getString(R.string.order_placed_on)} ${getCurrentDateTime(language)}",
                            fontSize = 12.sp,
                            color = Color(0xFF9CA3AF)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Зеленая галочка
            Surface(
                modifier = Modifier.size(80.dp),
                shape = CircleShape,
                color = Color(0xFF22C55E),
                shadowElevation = 8.dp
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Success",
                        tint = Color.White,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun OrderDetailText(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween, // Better alignment for RTL/LTR
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            color = Color(0xFF6B7280),
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = value,
            fontSize = 16.sp,
            color = Color(0xFF111827),
            fontWeight = FontWeight.Normal,
            modifier = Modifier.weight(1.5f),
            textAlign = androidx.compose.ui.text.style.TextAlign.End
        )
    }
}

// Функция для получения текущей даты и времени
fun getCurrentDateTime(language: String = "RU"): String {
    val locale = when (language) {
        "RU" -> Locale("ru", "RU")
        "HE" -> Locale("he", "IL")
        "EN" -> Locale("en", "US")
        else -> Locale.getDefault()
    }
    val dateFormat = SimpleDateFormat("d MMMM, HH:mm", locale)
    return dateFormat.format(Date())
}

// Пример использования
@Preview(showBackground = true)
@Composable
fun OrderSuccessScreenPreview() {
    val context = LocalContext.current
    val sampleOrder = OrderData(
        firstName = "Иван",
        lastName = "Петров",
        employeeId = "EMP-12345",
        mainDish = "Борщ украинский с сметаной",
        sideDish = "Стейк из говядины с овощами"
    )
    
    OrderSuccessScreen(
        orderData = sampleOrder,
        localizedContext = context,
        language = "RU"
    )
}
