package kr.taehoon.baseapplication

import android.content.Context
import kr.taehoon.baseapplication.base.BaseRecyclerAdapter
import kr.taehoon.baseapplication.base.BaseViewHolder
import kr.taehoon.baseapplication.databinding.ItemMainBinding

class MainRecyclerAdapter(override val context: Context) :
    BaseRecyclerAdapter<String, ItemMainBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.item_main


    override fun onCreateCoreViewHolder(binding: ItemMainBinding, viewType: Int): BaseViewHolder<String> {
        return MainViewHolder(binding)
    }

    inner class MainViewHolder(override val binding: ItemMainBinding) : BaseViewHolder<String>(binding.root) {
        override fun bind(itemData: String?, position: Int) {
            super.bind(itemData, position)
            itemData?.let {
                binding.test = it
            }
        }
    }
}