package hr.ferit.tomislavmarkovica.smallmanufacturer.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "features")
data class Feature(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "feature")
    val feature: String
)