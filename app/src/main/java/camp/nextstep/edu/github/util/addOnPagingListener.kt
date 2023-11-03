package camp.nextstep.edu.github.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addOnPagingListener(
    arrivedBottom: () -> Unit
) {
    val recyclerView = this
    recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = (recyclerView.layoutManager as LinearLayoutManager)
            val lastVisibleITem = layoutManager.findFirstVisibleItemPosition()
            if (lastVisibleITem == (layoutManager.itemCount)) {
                arrivedBottom()
                return
            }

            @Deprecated("Deprecated된 메서드 사용")
            if (scrollPercent(recyclerView) >= 100) {
                arrivedBottom()
                return
            }
        }
    })
}

@Deprecated("대체: FirstVisibleItemPosition()\n이유: 10000개의 리스트를 볼경우 9000이후 부터 무한으로 호출",
    ReplaceWith("(recyclerView.computeVerticalScrollOffset() * 1.0 / (recyclerView.computeVerticalScrollRange() - recyclerView.computeVerticalScrollExtent())) * 100.0")
)
fun scrollPercent(recyclerView: RecyclerView): Double {
    return (recyclerView.computeVerticalScrollOffset() * 1.0 / (recyclerView.computeVerticalScrollRange() - recyclerView.computeVerticalScrollExtent())) * 100.0
}

const val PAGE_SIZE = 20
const val INIT_PAGE = 0
private const val NEXT_PAGE_TRIGGER = PAGE_SIZE / 2
