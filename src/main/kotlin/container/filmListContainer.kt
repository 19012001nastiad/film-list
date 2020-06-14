package container

import components.FilmListProps
import components.fFilmList
import data.Film
import data.Genre
import data.State
import hoc.withDisplayName
import org.w3c.dom.events.Event
import react.RClass
import react.RProps
import react.invoke
import react.redux.rConnect
import redux.AddFilm
import redux.RAction
import redux.WrapperAction

interface FilmListDispatchProps : RProps {
    var add: (Event) -> Unit
}

interface FilmListStateProps : RProps {
    var films: Array<Film>
    var genres: Array<Genre>
}

val filmListHoc =
    rConnect<
            State,
            RAction,
            WrapperAction,
            RProps,                         // Own Props
            FilmListStateProps,
            FilmListDispatchProps,
            FilmListProps
            >(
        mapStateToProps = { state, _ ->
            films = state.films
            genres = state.genres
        },
        mapDispatchToProps = { dispatch, _ ->
            add = {dispatch(AddFilm())}
        }
    )

val filmListRClass =
    withDisplayName(
        "FilmList",
        fFilmList
    )
        .unsafeCast<RClass<FilmListProps>>()

val filmListContainer =
    filmListHoc(filmListRClass)