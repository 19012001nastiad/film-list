package container

import components.FilmFullProps
import components.fFilmFull
import data.*
import hoc.withDisplayName
import org.w3c.dom.events.Event
import react.*
import react.redux.rConnect
import redux.*

interface FilmFullDispatchProps : RProps {
    var add: (Int)->(Event) -> Unit
}

interface FilmFullStateProps: RProps {
    var films: Array<Film>
    var index: Int
}

interface FilmFullOwnProps : RProps {
    var index: Int
}

val filmFullHoc =
    rConnect<
            State,
            RAction,
            WrapperAction,
            FilmFullOwnProps,                         // Own Props
            FilmFullStateProps,
            FilmFullDispatchProps,
            FilmFullProps
            >(
        mapStateToProps = { state, _ ->
            films = state.films
        },
        mapDispatchToProps = { dispatch, _ ->
            add =
                { index ->
                    {
                        dispatch(AddToWant(index))
                    }
                }
        }
    )

val filmFullRClass =
    withDisplayName(
        "FilmFull",
        fFilmFull
    )
        .unsafeCast<RClass<FilmFullProps>>()

val filmFullContainer =
    filmFullHoc(filmFullRClass)