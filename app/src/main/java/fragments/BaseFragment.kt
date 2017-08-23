package fragments

import android.arch.lifecycle.LifecycleFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.calculator.calculator.databinding.FragmentBaseBinding
import kotlinx.android.synthetic.main.fragment_base.*
import viewModels.BaseViewModel

/**
 * Created by mcholewa on 21/08/2017.
 */
class BaseFragment : LifecycleFragment() {

    private val viewModel = BaseViewModel().also { it.load() }

    lateinit var binding: FragmentBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

     override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentBaseBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewModel = viewModel


        viewModel.liveData.observe(this) {
            binding.state = it
        }

        viewModel.uiActions.observe(this, { it(this.activity) })
    }

    override fun onResume() {
        super.onResume()
    }
}