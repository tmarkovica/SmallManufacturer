package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.contact.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import hr.ferit.tomislavmarkovica.smallmanufacturer.R
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Contact

class ContactAdapter: RecyclerView.Adapter<ContactViewHolder>() {

    private val contacts = mutableListOf<Contact>()

    var listener: ContactEventListener? = null

    private var selectedItemViewHolder: ContactViewHolder? = null
    private var selectedContact: Contact? = null

    fun setTasks(contacts: List<Contact>) {
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

        if (listener == null) return

        listener?.let {
            holder.itemView.setOnClickListener {

                highlightContact(holder)
                selectedContact = contact

                listener?.onContactClick(contact.id)
            }
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