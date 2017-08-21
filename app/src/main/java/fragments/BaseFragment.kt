package fragments

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
/**
 * Created by mcholewa on 21/08/2017.
 */
class BaseFragment : Fragment() {

    private val viewModel = viewModels.BaseViewModel().also { it.load() }

    lateinit var binding: FragmentDashboardBinding

    private val navigationController by lazy { component.navigationController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

     override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewModel = viewModel


        viewModel.liveData.observe(this) {
            adapter.replace(it.listData())
        }

        binding.contact.setOnClickListener {
            navigationController.navigateToContacts(this.activity)
        }

        viewModel.uiActions.observe(this, { it(this.activity) })
    }

    override fun onResume() {
        super.onResume()
    }
}