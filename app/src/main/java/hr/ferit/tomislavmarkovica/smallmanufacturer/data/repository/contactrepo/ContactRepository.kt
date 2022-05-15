package hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.contactrepo

import androidx.lifecycle.LiveData
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Contact


interface ContactRepository {

    fun save(contact: Contact): Long

    fun delete(contact: Contact)

    fun getContactById(id: Long): Contact?

    fun getAllContacts(): LiveData<List<Contact>>
}