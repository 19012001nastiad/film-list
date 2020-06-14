package components

import data.Film
import data.Genre
import react.*
import org.w3c.dom.events.Event
import hoc.withDisplayName
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLSelectElement
import react.dom.*
import kotlin.browser.document

interface FilmListProps : RProps {
    var films: Array<Film>
    var genres: Array<Genre>
    var add: (Event) -> Unit
}

val fFilmList =
    functionalComponent<FilmListProps> {
        val (genre, setGenre) = useState("Драма")
        h2 { +"Список фильмов" }
        button(classes = "desc-btn") {
            +"Добавить фильм"
            attrs.onClickFunction = {
                val modal = document.getElementById("myModal") as HTMLElement
                modal.style.display = "block"
            }
        }
        select {
            for (element in it.genres) {
                attrs.id = "genre"
                option {
                    +element.genre
                }
                attrs.onChangeFunction = {
                    val genres = document.getElementById("genre") as HTMLSelectElement
                    setGenre(genres.value)
                }
            }
        }
        table {
            attrs.id = "table-film"
            tr {
                th { +"Название" }
                th { +"Жанр" }
                th { +"Год выпуска" }
            }
            it.films.mapIndexed { index, film ->
                if (film.genre == genre) {
                    film(film,index)
                }
            }
        }
        div("modal") {
            attrs.id = "myModal"
            div("modal-content") {
                h1("modal-h1") { +"Добавить фильм" }
                ul {
                    li {
                        +"Название"
                        input(InputType.text) {
                            attrs.id = "name"
                        }
                    }
                    li {
                        +"Жанр"
                        input(InputType.text) {
                            attrs.id = "genreType"
                        }
                    }
                    li {
                        +"Год"
                        input(InputType.text) {
                            attrs.id = "year"
                        }
                    }
                    li {
                        +"Актерский состав:"
                        input(InputType.text) {
                            attrs.id = "actors"
                        }
                        li {
                            +"Обложка (ссылка на картинку)"
                            input(InputType.text) {
                                attrs.id = "picture"
                            }
                        }
                        li {
                            +"Описание"
                            input(InputType.text) {
                                attrs.id = "desc"
                            }
                        }
                    }
                    button(classes = "submit-btn") {
                        +"Добавить"
                        attrs.onClickFunction = it.add
                    }
                    button(classes = "submit-btn") {
                        +"Закрыть"
                        attrs.onClickFunction = {
                            val modal = document.getElementById("myModal") as HTMLElement
                            val name = document.getElementById("name") as HTMLInputElement
                            val genreType = document.getElementById("genreType") as HTMLInputElement
                            val year = document.getElementById("year") as HTMLInputElement
                            val actors = document.getElementById("actors") as HTMLInputElement
                            val picture = document.getElementById("picture") as HTMLInputElement
                            val desc = document.getElementById("desc") as HTMLInputElement
                            modal.style.display = "none"
                            name.value=""
                            genreType.value=""
                            year.value=""
                            actors.value=""
                            picture.value=""
                            desc.value=""
                        }
                    }
                }
            }
        }
    }

fun RBuilder.listFilms(
    films: Array<Film>,
    genres: Array<Genre>,
    add: (Event) -> Unit
) = child(
    withDisplayName("FilmList", fFilmList)
) {
    attrs.films = films
    attrs.genres = genres
    attrs.add = add
}