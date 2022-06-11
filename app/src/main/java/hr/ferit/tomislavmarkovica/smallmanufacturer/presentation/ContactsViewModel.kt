package hr.ferit.tomislavmarkovica.smallmanufacturer.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hr.ferit.tomislavmarkovica.smallmanufacturer.data.repository.contactrepo.ContactRepository
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Contact
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Product

class ContactsViewModel(
    private val contactRepository: ContactRepository
): ViewModel() {

    var contacts: LiveData<List<Contact>> = contactRepository.getAllContacts()

    fun saveContact(contact: Contact): Long {
        return contactRepository.save(contact)
    }

    fun getContactById(id: Long?): Contact? {
       return id?.let { contactRepository.getContactById(it) }
    }
}