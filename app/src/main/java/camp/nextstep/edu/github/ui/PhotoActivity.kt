package camp.nextstep.edu.github.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.github.R
import camp.nextstep.edu.github.databinding.ActivityPhotoBinding
import camp.nextstep.edu.github.util.addOnPagingListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhotoBinding
    private val viewModel: PhotoViewModel by viewModels()
    private val photoAdapter by lazy { PhotoAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        initAdapter()
        observeRepositories()
        observeError()

        viewModel.getPhotos()
    }

    private fun initDataBinding() {
        binding = ActivityPhotoBinding.inflate(layoutInflater).apply {
            viewmodel = viewModel
            lifecycleOwner = this@PhotoActivity
        }
        setContentView(binding.root)
    }

    private fun initAdapter() {
        binding.recyclerView.adapter = photoAdapter
        binding.recyclerView.addOnPagingListener(
            arrivedBottom = { viewModel.nextPage() }
        )
    }

    private fun observeRepositories() {
        viewModel.photos.observe(this) {
            photoAdapter.submitList(it.content)
        }
    }

    private fun observeError() {
        viewModel.errorEvent.observe(this) {
            Toast.makeText(this, getString(R.string.unknown_error), Toast.LENGTH_SHORT).show()
        }
    }
}
