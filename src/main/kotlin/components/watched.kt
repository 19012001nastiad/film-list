package components

import data.Film
import hoc.withDisplayName
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.*
import react.dom.*
import kotlin.browser.document

interface WatchedFilmsProps : RProps {
    var films: Array<Film>
}

val fWatchedFilms =
    functionalComponent<WatchedFilmsProps> {
        h2 { +"Просмотренные фильмы" }
        table {
            attrs.id = "table-film"
            tr {
                th { +"Название" }
                th { +"Год выпуска" }
                th { +"Оценка" }
            }
            it.films.mapIndexed { _, film ->
                if (film.seen) {
                    tr {
                        td { +"${film.name}" }
                        td { +"${film.year}" }
                        td { +"${film.rate}" }
                    }
                }
            }
        }
    }

fun RBuilder.watchedFilms(
    films: Array<Film>
) = child(
    withDisplayName("WatchedFilms", fWatchedFilms)
) {
    attrs.films = films
}