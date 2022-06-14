package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.contact.creation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import hr.ferit.tomislavmarkovica.smallmanufacturer.R
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.FragmentCreateContactBinding
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Contact
import hr.ferit.tomislavmarkovica.smallmanufacturer.presentation.ContactsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateContactFragment : Fragment() {

    private lateinit var binding: FragmentCreateContactBinding
    private val viewModel: ContactsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateContactBinding.inflate(
            inflater,
            container,
            false
        )
        binding.buttonSaveContact.setOnClickListener { saveContact() }
        return binding.root
    }

    private fun getContactFromInput(): Contact {
        val firstName = binding.editTextFirstName.text.toString()
        val lastName = binding.editTextLastName.text.toString()
        val email = binding.editTextEmail.text.toString()
        val address = binding.editTextAddress.text.toString()
        val phoneNumber = binding.editTextPhoneNumber.text.toString()
        return Contact(
            id = 0,
            firstName = firstName,
            lastName = lastName,
            email = email,
            phoneNumber = phoneNumber,
            address = address
        )
    }

    private fun saveContact() {
        val contact = getContactFromInput()
        if (contact.lastName == "" || contact.phoneNumber == "") {
            Toast.makeText(context, "Last name and number are required", Toast.LENGTH_SHORT).show()
            return
        }
        viewModel.saveContact(contact)
        Toast.makeText(context, "New contact added", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(binding.root)
            .navigate(R.id.action_createContactFragment_to_holderFragment)
    }
}