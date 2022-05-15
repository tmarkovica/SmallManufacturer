package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.orders.creation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.FragmentCreateOrderBinding

class CreateOrderFragment: Fragment() {

    private lateinit var binding: FragmentCreateOrderBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateOrderBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }
}