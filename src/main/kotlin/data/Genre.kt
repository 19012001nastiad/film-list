package data

data class Genre(
    val genre: String
)

fun genreList(): Array<Genre> {
    return arrayOf(
        Genre("Драма"),
        Genre("Мультфильм"),
        Genre("Триллер"),
        Genre("Комедия"),
        Genre("Ужасы"),
        Genre("Боевик")
    )
}