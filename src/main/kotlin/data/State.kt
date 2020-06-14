package data

class State(
    val films: Array<Film>,
    val genres: Array<Genre>
)


fun initialState() =
    State(
        filmList(),
        genreList()
    )


