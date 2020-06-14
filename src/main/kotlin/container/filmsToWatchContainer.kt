package container

import components.FilmsToWatchProps
import components.fFilmsToWatch
import data.Film
import data.State
import hoc.withDisplayName
import org.w3c.dom.events.Event
import react.RClass
import react.RProps
import react.invoke
import react.redux.rConnect
import redux.AddToWatched
import redux.DeleteFromWant
import redux.RAction
import redux.WrapperAction

interface FilmsToWatchDispatchProps : RProps {
    var add: (Int) -> (Event) -> Unit
    var del: (Int) -> (Event) -> Unit
}

interface FilmsToWatchStateProps : RProps {
    var films: Array<Film>
}

val filmsToWatchHoc =
    rConnect<
            State,
            RAction,
            WrapperAction,
            RProps,                         // Own Props
            FilmsToWatchStateProps,
            FilmsToWatchDispatchProps,
            FilmsToWatchProps
            >(
        mapStateToProps = { state, _ ->
            films = state.films
        },
        mapDispatchToProps = { dispatch, _ ->
            add =
                { index ->
                    {
                        dispatch(AddToWatched(index))
                    }
                }
            del =
                { index ->
                    {
                        dispatch(DeleteFromWant(index))
                    }
                }
        }
    )


val filmsToWatchRClass =
    withDisplayName(
        "FilmsToWatch",
        fFilmsToWatch
    )
        .unsafeCast<RClass<FilmsToWatchProps>>()

val filmsToWatchContainer =
    filmsToWatchHoc(filmsToWatchRClass)