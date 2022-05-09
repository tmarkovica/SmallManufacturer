package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.productcreation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import hr.ferit.tomislavmarkovica.smallmanufacturer.R
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.ItemFeatureBinding
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.ItemProductBinding
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Feature
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Product

class FeatureAdapter : Adapter<FeatureViewHolder>() {

    private val features = mutableListOf<Feature>()
    var listener: FeatureEventListener? = null

    fun setFeatures(features: List<Feature>) {
        this.features.clear()
        this.features.addAll(features)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_feature, parent, false)
        return FeatureViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeatureViewHolder, position: Int) {
        val feature = features[position]
        holder.bind(feature)
        listener?.let {
            holder.itemView.setOnClickListener { listener?.onFeatureClick(feature) }
        }
    }

    override fun getItemCount(): Int = features.count()
}

class FeatureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(feature: Feature) {
        val binding = ItemFeatureBinding.bind(itemView)
        binding.textViewFeature.text = feature.feature
    }
}