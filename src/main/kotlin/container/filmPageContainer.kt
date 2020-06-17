package container

import components.FilmPageProps
import components.fFilmPage
import data.*
import hoc.withDisplayName
import org.w3c.dom.events.Event
import react.*
import react.redux.rConnect
import redux.*

interface FilmPageDispatchProps : RProps {
    var add: (Int)->(Event) -> Unit
}

interface FilmPageStateProps: RProps {
    var films: Array<Film>
    var index: Int
}

interface FilmPageOwnProps : RProps {
    var index: Int
}

val filmPageHoc =
    rConnect<
            State,
            RAction,
            WrapperAction,
            FilmPageOwnProps,                         // Own Props
            FilmPageStateProps,
            FilmPageDispatchProps,
            FilmPageProps
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

val filmPageRClass =
    withDisplayName(
        "FilmPage",
        fFilmPage
    )
        .unsafeCast<RClass<FilmPageProps>>()

val filmPageContainer =
    filmPageHoc(filmPageRClass)