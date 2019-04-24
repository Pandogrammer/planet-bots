package latharia

import io.reactivex.Observable

class World(private val width: Int, private val height: Int) {

    val robots = mutableListOf<Robot>()

    val floor = Array(height) {arrayOfNulls<Terrain>(width)}

    init {
        for (i in floor) {
            for(j in i.indices){
                i[j] = Terrain()
            }
        }
    }


    fun size(): Int {
        return width * height
    }

    fun addRobot(x: Int, y: Int) {
        val robot = Robot(x, y)
        robots.add(robot)

        floor[x][y]?.robot = robot
    }

    fun moveRobots(){
        robots[0].movement
                .map { movement -> moveRobot(robots[0], movement) }
                .subscribe(System.out::println, Throwable::printStackTrace)
    }

    private fun moveRobot(robot: Robot, movement: Movement) {
        when(movement){
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

    fun changeMovement() : Throwable {
        when(direction) {
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

class Terrain(var explored : Boolean = false){
    var robot: Robot? = null
}

enum class Movement {
        LEFT, RIGHT, UP, DOWN
}
