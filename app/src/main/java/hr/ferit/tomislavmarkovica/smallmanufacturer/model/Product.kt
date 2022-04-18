package hr.ferit.tomislavmarkovica.smallmanufacturer.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String

    // https://www.youtube.com/watch?v=adGU0A80EJ0
//    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
//    val image: byte[]
    ) {
}