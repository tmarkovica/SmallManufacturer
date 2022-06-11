package hr.ferit.tomislavmarkovica.smallmanufacturer.ui.product.listing

interface ProductEventListener {
    fun onProductClick(id: Long?)
    fun onProductLongPress(id: Long?)
}