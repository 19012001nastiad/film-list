package container

import components.TopFilms
import components.fTopFilms
import data.Film
import data.State
import hoc.withDisplayName
import org.w3c.dom.events.Event
import react.RClass
import react.RProps
import react.invoke
import react.redux.rConnect
import redux.RAction
import redux.SortByRate
import redux.SortByYear
import redux.WrapperAction

interface TopFilmDispatchProps : RProps {
    var sortByYear: (Event) -> Unit
    var sortByRate: (Event) -> Unit
}

interface TopFilmStateProps: RProps {
    var films: Array<Film>
}

val fTopHoc =
    rConnect<
            State,
            RAction,
            WrapperAction,
            RProps,
            TopFilmStateProps,
            TopFilmDispatchProps,
            TopFilms
            >(
        mapStateToProps = { state, _ ->
            films = state.films
        },
        mapDispatchToProps = { dispatch, _ ->
            sortByYear = { dispatch(SortByYear()) }
            sortByRate = { dispatch(SortByRate()) }

        }
    )

val topFilmsRClass =
    withDisplayName(
        "TopFilms",
        fTopFilms
    )
        .unsafeCast<RClass<TopFilms>>()

val topFilmsContainer =
    fTopHoc(topFilmsRClass)