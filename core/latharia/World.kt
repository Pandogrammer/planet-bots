package latharia

import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class World {

	val width = 10
	val height = 10
	val robots = mutableListOf<Robot>()
	val level = Level(width, height)
	val time = runTime()

	private fun runTime(): Observable<Long> =
			Observable.interval(0, 1, TimeUnit.SECONDS)


	fun size(): Int {
		return width * height
	}

	fun addRobot(x: Int, y: Int, time: Observable<Long>) {
		val robot = Robot(x, y, time)
		robots.add(robot)

		level.floor[x][y]?.robot = robot

		robot.movement.subscribe { m -> println(m) }
	}

}

