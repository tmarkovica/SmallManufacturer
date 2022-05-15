package hr.ferit.tomislavmarkovica.smallmanufacturer.model

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.room.converters.PhotoConverter

@Entity(tableName = "products")
@TypeConverters(PhotoConverter::class)
data class Product(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "photo")
    val photo: Bitmap

    )