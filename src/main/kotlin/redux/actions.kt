package redux

class AddFilm(): RAction
class AddToWant(val index:Int): RAction
class AddToWatched(val index:Int): RAction
class DeleteFromWant(val index:Int): RAction
class SortByYear():RAction
class SortByRate():RAction