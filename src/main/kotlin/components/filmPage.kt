package components

import data.Film
import react.*
import hoc.withDisplayName
import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import react.dom.*


interface FilmFullProps : RProps {
    var index: Int
    var films: Array<Film>
    var add: (Int) -> (Event) -> (Unit)
}

val fFilmFull =
    functionalComponent<FilmFullProps> {
        div("card") {
            img(classes = "pic") {
                attrs.src = "${it.films[it.index].picture}"
                attrs.alt = "Ошибка"
            }
            div("container") {
                h4 { +"${it.films[it.index].name} (${it.films[it.index].year})" }
                p { +"Актерский состав: ${it.films[it.index].actors}" }
                p { +"Описание: ${it.films[it.index].desc}" }
                if (it.films[it.index].seen) {
                    p { +"Просмотрено" }
                    p { +"Оценка: ${it.films[it.index].rate}/10" }
                } else if (!it.films[it.index].wannaSee) {
                    button(classes = "submit-btn") {
                        +"Желаю посмотреть"
                        attrs.onClickFunction = it.add(it.index)
                    }
                } else if (it.films[it.index].wannaSee) {
                    p { +"Добавлено к просмотру" }
                }
            }
        }
    }

fun RBuilder.fullFilms(
    index: Int,
    films: Array<Film>,
    add: (Int) -> (Event) -> (Unit)
) = child(
    withDisplayName("FilmFull", fFilmFull)
) {
    attrs.index = index
    attrs.films = films
    attrs.add = add
}