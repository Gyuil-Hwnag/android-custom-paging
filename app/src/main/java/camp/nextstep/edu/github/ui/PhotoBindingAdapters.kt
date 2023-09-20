package camp.nextstep.edu.github.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("showImage")
fun bindShowImage(imageView: ImageView, url: String?) {
    url?.let {
        Glide.with(imageView.context)
            .load(url)
            .centerCrop()
            .into(imageView)
    }
}
