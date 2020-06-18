package container

import components.FilmsSelectedProps
import components.fFilmsSelected
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

interface FilmsSelectedDispatchProps : RProps {
    var add: (Int) -> (Event) -> Unit
    var del: (Int) -> (Event) -> Unit
}

interface FilmsSelectedStateProps : RProps {
    var films: Array<Film>
}

val filmsSelectedHoc =
    rConnect<
            State,
            RAction,
            WrapperAction,
            RProps,
            FilmsSelectedStateProps,
            FilmsSelectedDispatchProps,
            FilmsSelectedProps
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
                        dispatch(DeleteFromWant(index));
                    }
                }
        }
    )


val filmsSelectedRClass =
    withDisplayName(
        "FilmsSelected",
        fFilmsSelected
    )
        .unsafeCast<RClass<FilmsSelectedProps>>()

val filmsSelectedContainer =
    filmsSelectedHoc(filmsSelectedRClass)