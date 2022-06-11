package hr.ferit.tomislavmarkovica.smallmanufacturer.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Contact
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Product

class SharedViewModel: ViewModel() {

    private var _product = MutableLiveData<Product?>(null)
    val product: LiveData<Product?> = _product

    private val _contact = MutableLiveData<Contact?>(null)
    val contact: LiveData<Contact?> = _contact

    fun setSelectedProduct(p: Product?) {
        _product.value = p
    }

    fun setSelectedContact(c: Contact?) {
        _contact.value = c
    }

    fun areProductAndContactSelected(): Boolean {
        if (contact.value == null || product.value == null) return false
        else return true
    }
}