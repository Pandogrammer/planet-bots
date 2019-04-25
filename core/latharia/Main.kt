package latharia

fun main(args : Array<String>) {
    val w = World()

    w.time.subscribe {
                println("item: $it")
            }

    while(true){}
}

