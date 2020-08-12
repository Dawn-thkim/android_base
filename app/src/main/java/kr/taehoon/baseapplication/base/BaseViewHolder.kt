package kr.taehoon.baseapplication.base

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

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