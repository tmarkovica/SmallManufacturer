package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.product.featureadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import hr.ferit.tomislavmarkovica.smallmanufacturer.R
import hr.ferit.tomislavmarkovica.smallmanufacturer.model.Feature

class FeatureAdapter : Adapter<FeatureViewHolder>() {

    private val features = mutableListOf<Feature>()
//    var listener: FeatureEventListener? = null

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
//        listener?.let {
//            holder.itemView.setOnClickListener { listener?.onFeatureClick(feature) }
//        }
    }

    override fun getItemCount(): Int = features.count()
}