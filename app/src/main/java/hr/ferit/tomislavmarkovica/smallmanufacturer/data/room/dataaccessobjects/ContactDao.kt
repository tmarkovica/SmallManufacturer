package hr.ferit.tomislavmarkovica.smallmanufacturer.data.room.dataaccessobjects

import androidx.lifecycle.LiveData
import androidx.room.*
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Contact

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(contact: Contact): Long

    @Delete
    fun delete(contact: Contact)

    @Query("SELECT * FROM contacts WHERE id =:id")
    fun getContactById(id: Long): Contact?

    @Query("SELECT * FROM contacts")
    fun getAllContacts(): LiveData<List<Contact>>
}