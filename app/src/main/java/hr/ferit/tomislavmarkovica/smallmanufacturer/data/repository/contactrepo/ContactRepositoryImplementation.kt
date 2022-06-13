package hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.contactrepo

import androidx.lifecycle.LiveData
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.room.dataaccessobjects.ContactDao
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Contact

class ContactRepositoryImplementation(
    private val contactDao: ContactDao
) : ContactRepository {

    override fun save(contact: Contact): Long = contactDao.save(contact)

    override fun delete(contact: Contact) = contactDao.delete(contact)

    override fun getContactById(id: Long): Contact? = contactDao.getContactById(id)
    override fun getAllContacts(): LiveData<List<Contact>> = contactDao.getAllContacts()
}