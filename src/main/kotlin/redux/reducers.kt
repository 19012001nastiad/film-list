package redux

import data.State
import data.*
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLSelectElement
import kotlin.browser.document
import kotlin.browser.window
import kotlin.js.Date
import kotlin.math.absoluteValue


fun sortReducer(state: State, action: RAction) =
    when (action) {
        is SortByYear -> {
            val newFilms = state.films.sortedByDescending { it.year }
            State(
                newFilms.toTypedArray(),
                state.genres
            )
        }
        is SortByRate -> {
            val newFilms = state.films.sortedByDescending { it.rate }
            State(
                newFilms.toTypedArray(),
                state.genres
            )
        }
        else -> state
    }

fun deleteReducer(state: State, action: RAction) =
    when (action) {
        is DeleteFromWant -> {
            State(
                state.films.toMutableList().apply {
                    state.films[action.index].wannaSee = false
                }.toTypedArray(),
                state.genres
            )
        }
        else -> state
    }


fun addReducer(state: State, action: RAction) =
    when (action) {
        is AddFilm -> {
            val modal = document.getElementById("myModal") as HTMLElement
            val name = document.getElementById("name") as HTMLInputElement
            val genreType = document.getElementById("genreType") as HTMLInputElement
            val year = document.getElementById("year") as HTMLInputElement
            val actors = document.getElementById("actors") as HTMLInputElement
            val picture = document.getElementById("picture") as HTMLInputElement
            val desc = document.getElementById("desc") as HTMLInputElement
            val newFilm = Film(
                name.value,
                genreType.value,
                year.value.toInt(),
                actors.value,
                picture.value,
                desc.value,
                false,
                false,
                0,
                ""
            )
            modal.style.display = "none"
            name.value = ""
            genreType.value = ""
            year.value = ""
            actors.value = ""
            picture.value = ""
            desc.value = ""
            State(
                state.films.plus(newFilm),
                state.genres
            )

        }
        is AddToWant -> {
            val day = Date().getDate()
            val month = Date().getMonth() + 1
            val year = Date().getFullYear()
            val now = "${day}.0${month}.${year}"
            State(
                state.films.toMutableList().apply {
                    state.films[action.index].wannaSee = true
                    state.films[action.index].date = now
                }.toTypedArray(),
                state.genres
            )
        }
        is AddToWatched -> {
            val points = document.getElementById("points") as HTMLSelectElement
            val modal = document.getElementById("myModal") as HTMLElement
            modal.style.display = "none"
            State(
                state.films.toMutableList().apply {
                    state.films[action.index].seen = true
                    state.films[action.index].rate = points.value.toInt()
                    state.films[action.index].wannaSee = false
                }.toTypedArray(),
                state.genres
            )
        }
        else -> state
    }

fun rootReducer(state: State, action: RAction) =
    when (action) {
        is AddFilm -> addReducer(state, action)
        is AddToWant -> addReducer(state, action)
        is DeleteFromWant -> deleteReducer(state, action)
        is AddToWatched -> addReducer(state, action)
        is SortByYear -> sortReducer(state, action)
        is SortByRate -> sortReducer(state, action)
        else -> state
    }