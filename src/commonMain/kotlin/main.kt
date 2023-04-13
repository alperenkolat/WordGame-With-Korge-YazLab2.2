import com.soywiz.kds.*
import com.soywiz.klock.*
import com.soywiz.korge.*
import com.soywiz.korge.scene.*
import com.soywiz.korge.tween.*
import com.soywiz.korge.view.*
import com.soywiz.korim.color.*
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.*
import com.soywiz.korma.geom.*
import com.soywiz.korma.interpolation.*
import com.soywiz.korge.Korge
import com.soywiz.korge.box2d.*
import com.soywiz.korge.input.onClick
import com.soywiz.korge.ui.*
import com.soywiz.korge.view.position
import com.soywiz.korge.view.rotation
import com.soywiz.korge.view.solidRect
import com.soywiz.korgw.GameWindow
import com.soywiz.korim.color.Colors
import com.soywiz.korma.geom.Angle
import com.soywiz.korma.geom.degrees
import com.soywiz.korma.random.*
import org.jbox2d.dynamics.BodyType
import org.jbox2d.dynamics.joints.*
import kotlin.random.Random


suspend fun main() = Korge(width = 512, height =1280, bgcolor = Colors["#2b2b2b"]) {
    val sceneContainer = sceneContainer()

    sceneContainer.changeTo({ MyScene() })
}

class MyScene : Scene() {
    override suspend fun SContainer.sceneMain() {
        val random1 = Random(0L)



        solidRect(512, 50, Colors.WHITE).position(0, 1050).registerBodyWithFixture(
            type = BodyType.STATIC,
            friction = 0.99
        )
        for (j in 1..3)
            for (i in 1..8){

                uiButton(width=50.0, 50.0,text = "B") {
                    position(500-i*50, 450).rotation(0.degrees)
                    onPress { println("TAPPED ON 1") }
                }.also {  it.colorMul =   random1 [random1[Colors.CYAN, Colors.WHITE],random1[Colors.YELLOW, Colors.CYAN]]}
                    .registerBodyWithFixture(type = BodyType.DYNAMIC, density = 10.0, friction = 0.99)
                    .also {  it.textColor =  Colors.AZURE}
            }

        //silme butonu
        uiButton(width=100.0, 60.0,text = "X", ) {
            position(0, 1050)
            onPress { println("TAPPED ON 1") }
        }.also {  it.colorMul =  Colors["#ff1552"]}
            .also {  it.textColor =  Colors.WHITE}


        //onaylama butonu
        uiButton(width=100.0, 60.0,text = "OK") {
            position(410, 1050)
            onPress { println("TAPPED ON 1") }
        }.also {  it.colorMul =  Colors["#4effeb"]}
            .also {  it.textColor =  Colors.WHITE}
        //direk
        solidRect(3, 450, Colors.BLACK).position(93, 650).registerBodyWithFixture(
            type = BodyType.STATIC,
            friction = 0.99
        ) //direk
        solidRect(3, 450, Colors.BLACK).position(503, 650).registerBodyWithFixture(
            type = BodyType.STATIC,
            friction = 0.99
        )
        onClick {
            for (i in 1..8){

                uiButton(width=50.0, 50.0,text = "A") {
                    position(500-i*50, 450).rotation(0.degrees)
                    onPress { println("TAPPED ON 1") }
                    buttonBackColor=Colors.WHITE
                }.also {  it.colorMul =   random1 [random1[Colors.RED, Colors.AQUA],random1[Colors.YELLOW, Colors.BLUE]]}
                    .registerBodyWithFixture(type = BodyType.DYNAMIC, density = 10.0, friction = 0.99)

            }

        }

    }


}
fun randomAngle(): Angle = Random.nextInt(0, 90).degrees // Rastgele bir açı değeri döndüren fonksiyon. Açı derecesi, 0 ila 90 arasında rastgele seçilir.