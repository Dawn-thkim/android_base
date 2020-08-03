package kr.taehoon.baseapplication.base

import android.content.Context
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer


abstract class BaseRecyclerAdapter<ITEM : Any?, VD : ViewDataBinding> :
    RecyclerView.Adapter<BaseViewHolder>() {

    abstract val layoutResourceId: Int
    abstract val context: Context
    var mData: MutableList<ITEM> = mutableListOf()

    abstract fun onCreateCoreViewHolder(binding: VD, viewType: Int): BaseViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        return LayoutInflater.from(parent.context).let {
            DataBindingUtil.inflate<VD>(it, layoutResourceId, parent, false)
        }.run {
            (parent.context as? LifecycleOwner)?.let {
                lifecycleOwner = it
            }
            onCreateCoreViewHolder(this, viewType)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (mData.size > position)
            holder.bind(mData[position], position)
        else
            holder.bind(null, position)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    fun replaceAllItems(items: MutableList<ITEM>?) {
        mData = items ?: mutableListOf()
        notifyDataSetChanged()
    }
}


abstract class BaseViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView),
    LayoutContainer {
    abstract val binding: ViewDataBinding
    open fun bind(itemData: Any?, position: Int) {
        //공용 작성
    }
}

