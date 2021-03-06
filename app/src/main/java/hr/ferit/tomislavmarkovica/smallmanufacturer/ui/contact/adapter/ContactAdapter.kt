package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.contact.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.ferit.tomislavmarkovica.smallmanufacturer.R
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Contact

class ContactAdapter : RecyclerView.Adapter<ContactViewHolder>() {

    private val contacts = mutableListOf<Contact>()

    var listener: ContactEventListener? = null

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

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.bind(contact)

        listener?.let {
            holder.itemView.setOnClickListener {
                listener?.onContactClick(contact.id)
            }
        }
    }

    override fun getItemCount(): Int = contacts.count()
}