package hr.ferit.tomislavmarkovica.smallmanufacturer.product.featureadapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.ItemFeatureBinding
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Feature

class FeatureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(feature: Feature) {
        val binding = ItemFeatureBinding.bind(itemView)
        binding.textViewFeature.text = feature.feature
    }
}