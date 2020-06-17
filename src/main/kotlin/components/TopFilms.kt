package components

import data.Film
import hoc.withDisplayName
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import kotlinx.html.style
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.*
import react.dom.*
import kotlin.browser.document

interface TopFilms : RProps {
    var films: Array<Film>
    var sortByYear: (Event) -> Unit
    var sortByRate: (Event) -> Unit
}

val fTopFilms =
    functionalComponent<TopFilms> { props ->
        h2 { +"Рейтинг фильмов" }
        table {
            attrs.id = "table-film"
            tr {
                th { +"Название" }
                th {
                    +"Год выпуска"
                    button(classes = "srt-btn") {
                        +"Сортировать"
                        attrs.onClickFunction = props.sortByYear
                    }
                }
                th {
                    +"Оценка"
                    button(classes = "srt-btn") {
                        +"Сортировать"
                        attrs.onClickFunction = props.sortByRate
                    }
                }
            }
            props.films.map { film ->
                tr {
                    if (film.seen) {
                        td("bold") {
                            +"${film.name}"
                        }
                    } else {
                        td {
                            +"${film.name}"
                        }
                    }
                    td { +"${film.year}" }
                    td { +"${film.rate}" }
                }
            }
        }
    }

fun RBuilder.topFilms(
    films: Array<Film>,
    sortByYear: (Event) -> Unit,
    sortByRate: (Event) -> Unit
) = child(
    withDisplayName("TopFilms", fTopFilms)
) {
    attrs.films = films
    attrs.sortByYear = sortByYear
    attrs.sortByRate = sortByRate
}