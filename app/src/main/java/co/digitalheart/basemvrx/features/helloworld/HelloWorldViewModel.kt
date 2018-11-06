package co.digitalheart.basemvrx.features.helloworld

import co.digitalheart.basemvrx.core.MvRxViewModel
import com.airbnb.mvrx.MvRxState

data class HelloWorldState(val title: String = "Hello World") : MvRxState

class HelloWorldViewModel(initialState: HelloWorldState) : MvRxViewModel<HelloWorldState>(initialState)
