package latharia

import io.reactivex.Observable

class Robot(var x: Int, var y: Int, time: Observable<Long>) {

	val movement: Observable<Movement> = time.map { move() }

	private var direction = Movement.LEFT

	fun changeMovement() {
		when (direction) {
			Movement.LEFT -> direction = Movement.DOWN
			Movement.DOWN -> direction = Movement.RIGHT
			Movement.RIGHT -> direction = Movement.UP
			Movement.UP -> direction = Movement.LEFT
		}
	}

	private fun move(): Movement {
		return direction
	}
}

enum class Movement {
	LEFT, RIGHT, UP, DOWN
}