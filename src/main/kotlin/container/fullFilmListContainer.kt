package container

import components.FFilmListProps
import components.fFFilmList
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

interface FFilmListDispatchProps : RProps {
    var add: (Event) -> Unit
}

interface FFilmListStateProps : RProps {
    var films: Array<Film>

}

val ffilmListHoc =
    rConnect<
            State,
            RAction,
            WrapperAction,
            RProps,
            FFilmListStateProps,
            FFilmListDispatchProps,
            FFilmListProps
            >(
        mapStateToProps = { state, _ ->
            films = state.films
            },
        mapDispatchToProps = { dispatch, _ ->
            add = {dispatch(AddFilm())}
        }
    )

val ffilmListRClass =
    withDisplayName(
        "FullFilmList",
        fFFilmList
    )
        .unsafeCast<RClass<FFilmListProps>>()

val ffilmListContainer =
    ffilmListHoc(ffilmListRClass)