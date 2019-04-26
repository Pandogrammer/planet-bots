package latharia

import org.junit.Assert
import org.junit.Before
import org.junit.Test

/*
# cosas que habria que testear
+ creacion
+ movimiento
+ cambio de direccion
+ exploracion
+ recoleccion
+ campo de vision
 */
class RobotTest{

    private lateinit var world : World

    @Before
    fun init(){
        givenAWorld()
    }

    @Test
    fun `world creation`(){
        Assert.assertEquals(100, world.size())
    }

    @Test
    fun `robot insertion`(){
        givenARobot(5, 5)

        Assert.assertEquals(1, world.robots.size)
    }

    @Test
    fun `robot position`(){
        givenARobot(5, 5)

        Assert.assertEquals(world.robots[0], world.level.floor[5][5]?.robot)
    }


    private fun givenARobot(x: Int, y: Int) {
        world.addRobot(x, y, world.time)
    }

    private fun givenAWorld() {
        world = World()
    }


}
