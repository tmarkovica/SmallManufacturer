package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.order.selection

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import hr.ferit.tomislavmarkovica.smallmanufacturer.R
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Contact
import hr.ferit.tomislavmarkovica.smallmanufacturer.ui.contact.adapter.ContactViewHolder

class ContactSelectionAdapter : RecyclerView.Adapter<ContactViewHolder>() {

    private val contacts = mutableListOf<Contact>()

    private var selectedItemViewHolder: ContactViewHolder? = null
    private var selectedContact: Contact? = null

    fun setContacts(contacts: List<Contact>) {
        this.contacts.clear()
        this.contacts.addAll(contacts)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.bind(contact)

        holder.itemView.setOnClickListener {
            highlightContact(holder)
            selectedContact = contact
        }
    }

    override fun getItemCount(): Int = contacts.count()

    @RequiresApi(Build.VERSION_CODES.M)
    private fun highlightContact(holder: ContactViewHolder) {
        selectedItemViewHolder?.defaultItemColor()
        holder.highlightItem()
        selectedItemViewHolder = holder
    }

    fun getSelectedContact(): Contact? {
        return selectedContact
    }
}