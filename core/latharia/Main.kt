package latharia

fun main(args : Array<String>) {
    val w = World()

    w.time.subscribe {
                println("Time: $it")
            }

    w.addRobot(5, 1, w.time)

    while(true){}
}

