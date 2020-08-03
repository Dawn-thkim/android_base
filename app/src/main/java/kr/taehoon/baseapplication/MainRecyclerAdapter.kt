package kr.taehoon.baseapplication

import android.content.Context
import kr.taehoon.baseapplication.base.BaseRecyclerAdapter
import kr.taehoon.baseapplication.base.BaseViewHolder
import kr.taehoon.baseapplication.databinding.ItemMainBinding

class MainRecyclerAdapter(override val context: Context) :
    BaseRecyclerAdapter<String, ItemMainBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.item_main


    override fun onCreateCoreViewHolder(binding: ItemMainBinding, viewType: Int): BaseViewHolder {
        return MainViewHolder(binding)
    }

    inner class MainViewHolder(override val binding: ItemMainBinding) : BaseViewHolder(binding.root) {
        override fun bind(itemData: Any?, position: Int) {
            super.bind(itemData, position)
            (itemData as? String)?.let {
                binding.test = it
            }
        }
    }
}