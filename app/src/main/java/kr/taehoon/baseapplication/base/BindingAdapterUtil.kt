package kr.taehoon.baseapplication.base

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import java.text.SimpleDateFormat
import java.util.*


object BindingAdapterUtil {
    @JvmStatic
    @BindingAdapter("imageDrawable")
    fun imageDrawable(imageView: ImageView, drawable: Drawable) {
        imageView.setImageDrawable(drawable)
    }

    @JvmStatic
    @BindingAdapter("imageDrawableId")
    fun imageDrawableId(imageView: ImageView, drawableId: Int) {
        imageView.setImageResource(drawableId)
    }

    @JvmStatic
    @BindingAdapter(value = ["visible", "isGone"], requireAll = false)
    fun visible(view: View, isVisible: Boolean, isGone: Boolean = true) {
        view.visibility = if (isVisible) View.VISIBLE else if (isGone) View.GONE else View.INVISIBLE
    }

    @JvmStatic
    @BindingAdapter("selected")
    fun selected(view: View, isSelected: Boolean) {
        view.isSelected = isSelected
    }

    @JvmStatic
    @BindingAdapter("setItems")
    fun setItems(viewpager: ViewPager2, items: MutableLiveData<MutableList<Any?>>?) {
        (viewpager.adapter  as? BaseRecyclerAdapter<Any?, *>)?.let {
            items?.value?.let {list->
                    it.replaceAllItems(list.toMutableList())
                }
        }
    }

    @JvmStatic
    @BindingAdapter("setItems")
    fun setItems(viewpager: ViewPager2, items: List<Any?>?) {

        viewpager.adapter?.let {
            val baseRecyclerAdapter = it as BaseRecyclerAdapter<Any?,*>

            items?.let {list->
                baseRecyclerAdapter.replaceAllItems(list.toMutableList())
            }

        }
    }

    @JvmStatic
    @BindingAdapter("setItems")
    fun setItems(recyclerView: RecyclerView, items: MutableLiveData<MutableList<Any?>>?) {
        recyclerView.adapter?.let {
            val baseRecyclerAdapter = it as BaseRecyclerAdapter<Any?,*>

            items?.value?.let {
                    baseRecyclerAdapter.replaceAllItems(it)
                }
        }
    }

    @JvmStatic
    @BindingAdapter("setItems")
    fun setItems(recyclerView: RecyclerView, items: List<Any?>?) {

        recyclerView.adapter?.let {
            val baseRecyclerAdapter = it as BaseRecyclerAdapter<Any?,*>

            items?.let {
                baseRecyclerAdapter.replaceAllItems(items as MutableList<Any?>)
            }

        }
    }

    @JvmStatic
    @BindingAdapter(value = ["date", "dateFormat"], requireAll = true)
    fun convertDate(textView: TextView, date: Date?, dateFormat: String) {
        date?.let {
            val sdf = SimpleDateFormat(dateFormat)
            textView.text = sdf.format(it)
        }
    }

    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(imageView: ImageView, url: String?) {
        Glide.with(imageView.context).load(url).diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .override(SIZE_ORIGINAL)
            .centerCrop()
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(imageView: ImageView, resId: Int) {
        Glide.with(imageView.context).load(resId).diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(imageView)
    }

}
