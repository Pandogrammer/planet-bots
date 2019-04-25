package latharia

class Level(private val width: Int, private val height: Int) {

	val floor = Array(height) {arrayOfNulls<Terrain>(width)}

	init {
		for (i in floor) {
			for(j in i.indices){
				i[j] = Terrain()
			}
		}
	}
}
