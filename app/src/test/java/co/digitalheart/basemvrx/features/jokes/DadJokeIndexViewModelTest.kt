package co.digitalheart.basemvrx.features.jokes

import co.digitalheart.basemvrx.models.Joke
import co.digitalheart.basemvrx.models.JokesResponse
import co.digitalheart.basemvrx.network.DadJokeService
import co.digitalheart.basemvrx.util.RxSchedulersOverrideRule
import com.airbnb.mvrx.withState
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DadJokeIndexViewModelTest {

    @get:Rule
    val rxSchedulersOverrideRule = RxSchedulersOverrideRule()

    private lateinit var viewModel: DadJokeIndexViewModel
    private lateinit var jokeResponse: JokesResponse
    private val service: DadJokeService = mock()


    @Before
    fun setupJokesList() {
        val jokes = listOf(Joke("a", "A"), Joke("b", "B"), Joke("c", "C"))
        jokeResponse = JokesResponse(0, jokes)
    }

    @Test
    fun fetchNextPage() {
        whenever(service.search(page = 1, limit = 5)).thenReturn(Observable.just(jokeResponse))

        viewModel = DadJokeIndexViewModel(DadJokeIndexState(), service)

        viewModel.fetchNextPage()
        withState(viewModel) { assertEquals(3, it.jokes.size) }
    }

}