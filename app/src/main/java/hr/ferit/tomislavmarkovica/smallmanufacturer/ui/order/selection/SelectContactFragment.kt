package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.order.selection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.FragmentSelectContactBinding
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Contact
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.ContactsViewModel
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.SharedCotactAndProductViewModel
import hr.ferit.tomislavmarkovica.smallmanufacturer.ui.contact.adapter.ContactEventListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelectContactFragment: Fragment(), ContactEventListener {

    private lateinit var binding: FragmentSelectContactBinding
    private val viewModel: ContactsViewModel by viewModel()

    private val sharedCotactAndProductViewModel: SharedCotactAndProductViewModel by activityViewModels()

    private lateinit var adapter: ContactSelectionAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectContactBinding.inflate(
            inflater,
            container,
            false
        )
        bindView()
        setupRecyclerView()
        binding.buttonConfirm.setOnClickListener { confirmSelectedContact() }
        return binding.root
    }

    private fun updateData() {
        viewModel.contacts.value?.let { adapter.setContacts(it) }
    }

    private fun bindView() {
        viewModel.contacts.observe(viewLifecycleOwner) {
            if (it != null && it.isNotEmpty()) {
                updateData()
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = ContactSelectionAdapter()
//        adapter.listener = this
        binding.recyclerView.adapter = adapter
    }

    override fun onContactClick(id: Long?) {
        //Log.d("TAG", "contactId = " + id.toString())
    }

    private fun confirmSelectedContact() {
        val contact: Contact = adapter.getSelectedContact() ?: return
        Toast.makeText(context, "Contact selected", Toast.LENGTH_SHORT).show()
        sharedCotactAndProductViewModel.setSelectedContact(contact)
        findNavController().navigateUp()
    }
}