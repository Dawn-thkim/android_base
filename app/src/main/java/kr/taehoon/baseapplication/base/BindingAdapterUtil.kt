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
    /**
     * View의 visibility 설정
     * @param view View instance
     * @param isVisible 해당 View를 표시할지 표시하지 않을 지에 대한 Flag
     * @param isGone isVisible == false 일 경우에 GONE 처리할지 INVISIBLE 처리할지에 대한 Flag
     */
    @JvmStatic
    @BindingAdapter(value = ["visible", "isGone"], requireAll = false)
    fun visible(view: View, isVisible: Boolean, isGone: Boolean = true) {
        view.visibility = if (isVisible) {
            View.VISIBLE
        } else {
            if (isGone) View.GONE else View.INVISIBLE
        }
    }
    /**
     * View의 selected 설정
     * selected 상태에 따른 StateListDrawable를 활용하기 위해 주로 사용 됨
     * @param view View instance
     * @param isSelected 해당 View를 표시할지 표시하지 않을 지에 대한 Flag
     */
    @JvmStatic
    @BindingAdapter("selected")
    fun selected(view: View, isSelected: Boolean) {
        view.isSelected = isSelected
    }

    /**
     * ViewPager2의 MutableLiveData를 Binding 하기 위해 사용
     * @param viewpager ViewPager2 instance
     * @param items ViewPager2에 Binding될 MutableLiveData
     */
    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    @BindingAdapter("setItems")
    fun setItems(viewpager: ViewPager2, items: MutableLiveData<MutableList<Any?>>?) {
        (viewpager.adapter as? BaseRecyclerAdapter<Any?, *>)?.let {
            items?.value?.let { list ->
                it.replaceAllItems(list.toMutableList())
            }
        }
    }

    /**
     * ViewPager2의 List Binding 하기 위해 사용
     * @param viewpager ViewPager2 instance
     * @param items ViewPager2에 Binding될 List
     */
    @JvmStatic
    @Suppress("UNCHECKED_CAST")
    @BindingAdapter("setItems")
    fun setItems(viewpager: ViewPager2, items: List<Any?>?) {

        viewpager.adapter?.let {
            val baseRecyclerAdapter = it as BaseRecyclerAdapter<Any?, *>

            items?.let { list ->
                baseRecyclerAdapter.replaceAllItems(list.toMutableList())
            }

        }
    }

    /**
     * RecyclerView MutableLiveData를 Binding 하기 위해 사용
     * @param recyclerView RecyclerView instance
     * @param items ViewPager2에 Binding될 MutableLiveData
     */
    @JvmStatic
    @Suppress("UNCHECKED_CAST")
    @BindingAdapter("setItems")
    fun setItems(recyclerView: RecyclerView, items: MutableLiveData<MutableList<Any?>>?) {
        recyclerView.adapter?.let {
            val baseRecyclerAdapter = it as BaseRecyclerAdapter<Any?, *>

            items?.value?.let {
                baseRecyclerAdapter.replaceAllItems(it)
            }
        }
    }

    /**
     * RecyclerView에 List를 Binding 하기 위해 사용
     * @param recyclerView RecyclerView instance
     * @param items ViewPager2에 Binding될 List
     */
    @JvmStatic
    @Suppress("UNCHECKED_CAST")
    @BindingAdapter("setItems")
    fun setItems(recyclerView: RecyclerView, items: List<Any?>?) {

        recyclerView.adapter?.let {
            val baseRecyclerAdapter = it as BaseRecyclerAdapter<Any?, *>

            items?.let {
                baseRecyclerAdapter.replaceAllItems(items as MutableList<Any?>)
            }

        }
    }

    /**
     * Date와 DataeFormat를 전달 받아, 해당 DateFromat으로 Date를 String으로 변환한 후 TextView에 Binding
     * @param textView TextView instance
     * @param date Date instance nullable
     * @param dateFormat 표시될 날짜 형
     */
    @JvmStatic
    @BindingAdapter(value = ["date", "dateFormat"], requireAll = true)
    fun convertDate(textView: TextView, date: Date?, dateFormat: String) {
        date?.let {
            val sdf = SimpleDateFormat(dateFormat,Locale.getDefault())
            textView.text = sdf.format(it)
        }
    }

    /**
     * url를 바탕으로 Image를 ImageView에 표시
     * @param imageView 표시 될 ImageView Instance
     * @param url 표시 할 URL
     */
    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(imageView: ImageView, url: String?) {
        Glide.with(imageView.context).load(url).diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .override(SIZE_ORIGINAL)
            .into(imageView)
    }

    /**
     * Resource Id를 바탕으로 Image를 ImageView에 표시
     * @param imageView 표시 될 ImageView Instance
     * @param resId 표시 할 Resource Id
     */
    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(imageView: ImageView, resId: Int) {
        Glide.with(imageView.context).load(resId).diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .override(SIZE_ORIGINAL)
            .into(imageView)
    }

    /**
     * Drawable 인스턴스를 Image View에 표시
     * @param imageView 표시 될 ImageView Instance
     * @param drawable 표시 할 Drawable Instance
     */
    @JvmStatic
    @BindingAdapter("imageDrawable")
    fun setImage(imageView: ImageView, drawable: Drawable) {
        Glide.with(imageView.context).load(drawable).diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .override(SIZE_ORIGINAL)
            .into(imageView)
    }
}
