package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.order.creation

import android.util.Log
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.forEach
import androidx.recyclerview.widget.RecyclerView
import hr.ferit.tomislavmarkovica.smallmanufacturer.R
import hr.ferit.tomislavmarkovica.smallmanufacturer.databinding.ItemFeatureCheckboxBinding
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Feature
import hr.ferit.tomislavmarkovica.smallmanufacturer.ui.product.creation.FeatureEventListener

class FeatureSelectAdapter : RecyclerView.Adapter<FeatureSelectAdapter.FeatureSelectViewHolder>() {

    private val features = mutableListOf<Feature>()

    var checkBoxStateArray = SparseBooleanArray()

    var listener: FeatureEventListener? = null

    fun setFeatures(features: List<Feature>) {
        this.features.clear()
        this.features.addAll(features)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureSelectViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_feature_checkbox, parent, false)
        return FeatureSelectViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeatureSelectViewHolder, position: Int) {
        val feature = features[position]
        holder.bind(feature)
        Log.d("TAG", holder.toString())
    }

    override fun getItemCount(): Int = features.count()

    fun getCheckedFeaturesId(): List<Long> {
        val featureIds = mutableListOf<Long>()
        checkBoxStateArray.forEach { index, checkedState ->
            if (checkedState)
                featureIds.add(features[index].id)
        }
        return featureIds
    }

    inner class FeatureSelectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(feature: Feature) {
            val binding = ItemFeatureCheckboxBinding.bind(itemView)
            binding.checkBoxFeature.text = feature.feature

            binding.checkBoxFeature.setOnClickListener {
                if (!checkBoxStateArray.get(adapterPosition, false)) {
                    binding.checkBoxFeature.isChecked = true

                    checkBoxStateArray.put(adapterPosition, true)
                } else {
                    binding.checkBoxFeature.isChecked = false

                    checkBoxStateArray.put(adapterPosition, false)
                }
            }
        }
    }
}