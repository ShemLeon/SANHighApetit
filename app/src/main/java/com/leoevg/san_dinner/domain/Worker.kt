package com.leoevg.san_dinner.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "worker")
class Worker {
    @PrimaryKey
    var name: String? = null
    var workerID: Int? = null

}