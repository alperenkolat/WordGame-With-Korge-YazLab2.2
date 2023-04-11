import com.soywiz.klock.*
import com.soywiz.korge.*
import com.soywiz.korge.scene.*
import com.soywiz.korge.tween.*
import com.soywiz.korge.view.*
import com.soywiz.korim.color.*
import com.soywiz.korim.format.*
import com.soywiz.korge.box2d.*
import com.soywiz.korge.input.*
import com.soywiz.korge.ui.*
import com.soywiz.korma.geom.*
import com.soywiz.korma.random.*
import com.soywiz.korio.file.std.*
import com.soywiz.korma.interpolation.*
import org.jbox2d.dynamics.*
import kotlin.random.*

suspend fun main() = Korge(width = 512, height = 512, bgcolor = Colors["#2b2b2b"], title = "WordGame") {
	val sceneContainer = sceneContainer()

	sceneContainer.changeTo({ MyScene() })
}

class MyScene : Scene() {
	override suspend fun SContainer.sceneMain() {
		val minDegrees = (-36).degrees
		val maxDegrees = (+36).degrees
        val random = Random(0L)

		val image = image(resourcesVfs["basak.jpg"].readBitmap()) {
			rotation = maxDegrees
			anchor(.5, .5)
			scale(0.5)
			position(256, 256)
		}

            solidRect(50, 50, Colors.RED)
                .position(256, 0)
                .rotation(0.degrees)
                .also { it.colorMul = random[Colors.RED, Colors.PURPLE] }
                .registerBodyWithFixture(type = BodyType.DYNAMIC)
        solidRect(40, 40, Colors.BLACK)
            .position(346, 0)
            .rotation(0.degrees)
            .also { it.colorMul = random[Colors.RED, Colors.PURPLE] }
            .registerBodyWithFixture(type = BodyType.DYNAMIC)


        solidRect(50, 50, Colors.GOLD)
            .position(306, 0)
            .rotation(0.degrees)
            .also { it.colorMul = random[Colors.RED, Colors.PURPLE]  }
            .registerBodyWithFixture(type = BodyType.DYNAMIC)
            .setText("s").also { Colors.AQUA  }
		while (true) {
			image.tween(image::rotation[minDegrees], time =1.seconds, easing = Easing.EASE_IN_OUT)
			image.tween(image::rotation[maxDegrees], time = 1.seconds, easing = Easing.EASE_IN_OUT)
            solidRect(50, 50, Colors.BLACK)
                .position(306, 0)
                .rotation(0.degrees)
                .also { it.colorMul = random[Colors.RED, Colors.PURPLE] }
                .also { it. name("zx") }
                .registerBodyWithFixture(type = BodyType.DYNAMIC)
		}
	}
}
