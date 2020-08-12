package kr.taehoon.baseapplication.base

import android.content.Context
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

/**
 * @param <Item> RecyclerView에 표시될 List의 Parameterized Type
 * @param <VD> ViewHolder의 ViewDataBinding Type
 */
abstract class BaseRecyclerAdapter<ITEM : Any?, VD : ViewDataBinding> :
    RecyclerView.Adapter<BaseViewHolder<ITEM>>() {

    abstract val layoutResourceId: Int
    var mData: MutableList<ITEM> = mutableListOf()

    /**
     * @param binding ViewDataBinding Instance
     * @param viewType ViewType
     */
    abstract fun onCreateCoreViewHolder(binding: VD, viewType: Int): BaseViewHolder<ITEM>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ITEM> {

        return LayoutInflater.from(parent.context).let {
            DataBindingUtil.inflate<VD>(it, layoutResourceId, parent, false)
        }.run {
            (parent.context as? LifecycleOwner)?.let {
                lifecycleOwner = it
            }
            onCreateCoreViewHolder(this, viewType)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ITEM>, position: Int) {
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

/**
 * @param <T> ViewHolder의 bind Item Type
 * @param containerView ViewHolder View Instance
 */
abstract class BaseViewHolder<T:Any?>(override val containerView: View) :
    RecyclerView.ViewHolder(containerView),
    LayoutContainer {
    abstract val binding: ViewDataBinding
    //기존 open 함수는 공용 기능이 있을 경우, 구현
    abstract fun bind(itemData: T?, position: Int)
}

