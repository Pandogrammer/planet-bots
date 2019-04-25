package latharia

import io.reactivex.Observable
import io.reactivex.schedulers.Timed
import java.util.concurrent.TimeUnit

class World {

	val width = 10
	val height = 10
	val robots = mutableListOf<Robot>()
	val level = Level(width, height)
	val time = runTime()

	private fun runTime(): Observable<Long> = Observable
			.interval(0, 1, TimeUnit.SECONDS)


	fun size(): Int {
		return width * height
	}

	fun addRobot(x: Int, y: Int) {
		val robot = Robot(x, y)
		robots.add(robot)

		level.floor[x][y]?.robot = robot
	}

	fun moveRobots() {
		robots[0].movement
				.map { movement -> moveRobot(robots[0], movement) }
				.subscribe(System.out::println, Throwable::printStackTrace)
	}

	private fun moveRobot(robot: Robot, movement: Movement) {
		when (movement) {
			Movement.LEFT -> robot.x--
		}
	}
}

class Robot(var x: Int, var y: Int) {
	val movement = Observable.create<Movement> { emitter ->
		emitter.onNext(move())
		emitter.onError(changeMovement())
	}

	private var direction = Movement.LEFT

	fun changeMovement(): Throwable {
		when (direction) {
			Movement.LEFT -> direction = Movement.DOWN
		}

		println(direction)
		return Exception("Couldn't move, changing movement")
	}

	private fun move(): Movement {
		println(direction)
		return direction
	}
}

class Terrain(var explored: Boolean = false) {
	var robot: Robot? = null
}

enum class Movement {
	LEFT, RIGHT, UP, DOWN
}
