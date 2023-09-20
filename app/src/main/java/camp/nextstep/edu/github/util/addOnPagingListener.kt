package camp.nextstep.edu.github.util

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addOnPagingListener(
    arrivedBottom: () -> Unit
) {
    val recyclerView = this
    recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (scrollPercent(recyclerView) >= 100) {
                arrivedBottom()
                return
            }
        }
    })
}

fun scrollPercent(recyclerView: RecyclerView): Double {
    return (recyclerView.computeVerticalScrollOffset() * 1.0 / (recyclerView.computeVerticalScrollRange() - recyclerView.computeVerticalScrollExtent())) * 100.0
}

const val PAGE_SIZE = 20
const val INIT_PAGE = 0
