package co.digitalheart.basemvrx.features.jokes

import android.support.v4.app.FragmentActivity
import co.digitalheart.basemvrx.core.MvRxViewModel
import co.digitalheart.basemvrx.network.DadJokeService
import com.airbnb.mvrx.Async
import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.sample.models.Joke
import com.airbnb.mvrx.sample.models.JokesResponse
import org.koin.android.ext.android.inject

private const val JOKES_PER_PAGE = 5

data class DadJokeIndexState(
    val jokes: List<Joke> = emptyList(),
    val request: Async<JokesResponse> = Uninitialized
) : MvRxState

class DadJokeIndexViewModel(
    initialState: DadJokeIndexState,
    private val dadJokeService: DadJokeService
) : MvRxViewModel<DadJokeIndexState>(initialState) {

    init {
        fetchNextPage()
    }

    fun fetchNextPage() = withState { state ->
        if (state.request is Loading) return@withState

        dadJokeService
            .search(page = state.jokes.size / JOKES_PER_PAGE + 1, limit = JOKES_PER_PAGE)
            .execute { copy(request = it, jokes = jokes + (it()?.results ?: emptyList())) }

    }

    companion object : MvRxViewModelFactory<DadJokeIndexState> {
        @JvmStatic
        override fun create(
            activity: FragmentActivity,
            state: DadJokeIndexState
        ): BaseMvRxViewModel<DadJokeIndexState> {
            val service: DadJokeService by activity.inject()
            return DadJokeIndexViewModel(state, service)
        }
    }
}
