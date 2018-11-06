package co.digitalheart.basemvrx.features.helloworld

import com.airbnb.mvrx.fragmentViewModel
import co.digitalheart.basemvrx.core.BaseFragment
import co.digitalheart.basemvrx.core.simpleController
import com.airbnb.mvrx.sample.views.marquee

class HelloWorldEpoxyFragment : BaseFragment() {
    private val viewModel: HelloWorldViewModel by fragmentViewModel()

    override fun epoxyController() = simpleController(viewModel) { state ->
        marquee {
            id("marquee")
            title(state.title)
        }
    }
}
