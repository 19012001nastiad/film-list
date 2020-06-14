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

interface FilmsToWatchProps : RProps {
    var films: Array<Film>
    var add: (Int) -> (Event) -> (Unit)
    var del: (Int) -> (Event) -> (Unit)
}

val fFilmsToWatch =
    functionalComponent<FilmsToWatchProps> {
        h2 { +"Список фильмов к просмотру" }
        table {
            attrs.id = "table-film"
            tr {
                th { +"Название" }
                th { +"Год выпуска" }
                th { +"Дата добавления" }
                th { +"Действия" }
            }
            it.films.mapIndexed { index, film ->
                if (film.wannaSee) {
                    tr {
                        td { +"${film.name}" }
                        td { +"${film.year}" }
                        td { +"${film.date}" }
                        td {
                            button(classes = "submit-btn") {
                                +"Просмотрено"
                                attrs.onClickFunction = {
                                    val modal = document.getElementById("myModal") as HTMLElement
                                    modal.style.display = "block"
                                }
                            }
                            button(classes = "submit-btn") {
                                +"Удалить"
                                attrs.onClickFunction = it.del(index)

                            }
                        }
                    }
                    div("modal") {
                        attrs.id = "myModal"
                        div("modal-content") {
                            h1("modal-h1") { +"Оцените фильм" }
                            select {
                                for (i in 1..10) {
                                    attrs.id = "points"
                                    option {
                                        +"$i"
                                    }
                                }
                            }
                            button(classes = "submit-btn") {
                                +"Оценить"
                                attrs.onClickFunction = it.add(index)
                            }
                            button(classes = "submit-btn") {
                                +"Закрыть"
                                attrs.onClickFunction = {
                                    val modal = document.getElementById("myModal") as HTMLElement
                                    modal.style.display = "none"
                                }
                            }
                        }
                    }
                }
            }

        }
    }

fun RBuilder.filmsToWatch(
    films: Array<Film>,
    add: (Int) -> (Event) -> (Unit),
    del: (Int) -> (Event) -> (Unit)
) = child(
    withDisplayName("FilmsToWatch", fFilmsToWatch)
) {
    attrs.films = films
    attrs.add = add
    attrs.del = del
}