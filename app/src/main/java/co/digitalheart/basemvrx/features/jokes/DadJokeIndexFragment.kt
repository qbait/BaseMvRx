package co.digitalheart.basemvrx.features.jokes

import android.os.Bundle
import android.util.Log
import android.view.View
import co.digitalheart.basemvrx.core.BaseFragment
import co.digitalheart.basemvrx.core.simpleController
import co.digitalheart.basemvrx.views.basicRow
import co.digitalheart.basemvrx.views.loadingRow
import co.digitalheart.basemvrx.views.marquee
import com.airbnb.mvrx.fragmentViewModel

private const val TAG = "DadJokeIndexFragment"

class DadJokeIndexFragment : BaseFragment() {

    private val viewModel: DadJokeIndexViewModel by fragmentViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.asyncSubscribe(DadJokeIndexState::request, onFail = { error ->
            Log.w(TAG, "Jokes request failed", error)
        })
    }

    override fun epoxyController() = simpleController(viewModel) { state ->
        marquee {
            id("marquee")
            title("Dad Jokes")
        }

        state.jokes.forEach { joke ->
            basicRow {
                id(joke.id)
                title(joke.joke)
            }
        }

        loadingRow {
            // Changing the ID will force it to rebind when new data is loaded even if it is
            // still on screen which will ensure that we trigger loading again.
            id("loading${state.jokes.size}")
            onBind { _, _, _ -> viewModel.fetchNextPage() }
        }
    }
}