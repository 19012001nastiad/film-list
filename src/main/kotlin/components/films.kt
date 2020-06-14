package components

import container.filmListContainer
import data.Film
import react.*
import org.w3c.dom.events.Event
import hoc.withDisplayName
import kotlinx.html.id
import react.dom.*
import react.router.dom.navLink
import react.router.dom.route
import react.router.dom.switch
import kotlin.js.Date

interface FilmProps : RProps {
    var films: Film
    var index: Int
}

val fFilms =
    functionalComponent<FilmProps> {
        tr {
            td {  navLink("/films/${it.index}") {+it.films.name } }
            td { +it.films.genre }
            td { +"${it.films.year}" }
        }
    }

fun RBuilder.film(
    films: Film,
    number: Int
) = child(
    withDisplayName("Film", fFilms)
) {
    attrs.films = films
    attrs.index = number
}