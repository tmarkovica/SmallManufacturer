package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.contact.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import hr.ferit.tomislavmarkovica.smallmanufacturer.R
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.FragmentContactsBinding
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.ContactsViewModel
import hr.ferit.tomislavmarkovica.smallmanufacturer.ui.contact.adapter.ContactAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactsFragment: Fragment() {

    private lateinit var binding: FragmentContactsBinding
    private val viewModel: ContactsViewModel by viewModel()

    private lateinit var adapter: ContactAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactsBinding.inflate(
            inflater,
            container,
            false
        )
        bindView()
        setupRecyclerView()

        binding.floatingActionButtonAddContact.setOnClickListener { showCreateContactFragment() }
        return binding.root
    }

    private fun showCreateContactFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_holderFragment_to_createContactFragment)
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
        binding.recyclerViewContacts.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = ContactAdapter()
        binding.recyclerViewContacts.adapter = adapter
    }
}