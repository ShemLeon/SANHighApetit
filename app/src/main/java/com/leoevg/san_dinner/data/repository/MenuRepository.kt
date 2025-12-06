package com.leoevg.san_dinner.data.repository

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.leoevg.san_dinner.data.util.NODE_DAYS
import com.leoevg.san_dinner.data.util.NODE_LANG
import com.leoevg.san_dinner.data.util.NODE_MENU
import com.leoevg.san_dinner.data.util.NODE_PICTURE
import com.leoevg.san_dinner.data.util.NODE_TYPE
import com.leoevg.san_dinner.data.util.NODE_VEGAN
import com.leoevg.san_dinner.data.util.getDataOnce
import com.leoevg.san_dinner.domain.model.OrderItem
import javax.inject.Inject

class MenuRepository @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase
) {
    suspend fun getMenu(): List<OrderItem> {
        return try {
            val snapshot = firebaseDatabase.getReference(NODE_MENU).getDataOnce()
            val menuList = mutableListOf<OrderItem>()

            for (child in snapshot.children) {
                try {
                    val id = child.key ?: continue
                    
                    // Safely get values, defaulting if missing or wrong type
                    val type = child.child(NODE_TYPE).getValue(String::class.java) ?: ""
                    val picture = child.child(NODE_PICTURE).getValue(String::class.java) ?: ""
                    val vegan = child.child(NODE_VEGAN).getValue(Boolean::class.java) ?: false

                    val daysList = mutableListOf<String>()
                    child.child(NODE_DAYS).children.forEach { daySnapshot ->
                        daySnapshot.getValue(String::class.java)?.let { daysList.add(it) }
                    }

                    val langMap = mutableMapOf<String, String>()
                    child.child(NODE_LANG).children.forEach { langSnapshot ->
                        val langKey = langSnapshot.key ?: return@forEach
                        val langValue = langSnapshot.getValue(String::class.java) ?: ""
                        langMap[langKey] = langValue
                    }

                    menuList.add(
                        OrderItem(
                            id = id,
                            days = daysList,
                            lang = langMap,
                            picture = picture,
                            type = type,
                            vegan = vegan
                        )
                    )
                } catch (e: Exception) {
                    Log.e("MenuRepository", "Error parsing item: ${child.key}", e)
                    // Continue to next item instead of crashing or returning empty list
                    continue 
                }
            }
            menuList
        } catch (e: Exception) {
            e.printStackTrace()
            // Return empty list only if the entire fetch fails (e.g. no internet)
            emptyList()
        }
    }
}
