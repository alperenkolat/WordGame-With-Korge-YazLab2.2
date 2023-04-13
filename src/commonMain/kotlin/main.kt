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
import com.soywiz.korge.box2d.registerBodyWithFixture
import com.soywiz.korge.input.onClick
import com.soywiz.korge.view.position
import com.soywiz.korge.view.rotation
import com.soywiz.korge.view.solidRect
import com.soywiz.korgw.GameWindow
import com.soywiz.korim.color.Colors
import com.soywiz.korma.geom.Angle
import com.soywiz.korma.geom.degrees
import org.jbox2d.dynamics.BodyType
import kotlin.random.Random


suspend fun main() = Korge(width = 512, height = 1280, bgcolor = Colors["#2b2b2b"]) {
    val sceneContainer = sceneContainer()

    sceneContainer.changeTo({ MyScene() })
}

class MyScene : Scene() {
    override suspend fun SContainer.sceneMain() {


        // Kırmızı bir dikdörtgen oluşturulur, boyutu 50x50 piksel, konumu (400, 50), dönüş açısı 30 derece.
        // Kutu, dinamik bir cisim olarak kaydedilir, yoğunluğu 2, sürtünmesi 0.01 olarak ayarlanır.
        solidRect(50, 50, Colors.RED).position(400, 50).rotation(30.degrees)
            .registerBodyWithFixture(type = BodyType.DYNAMIC, density = 2, friction = 0.01)

        // Kırmızı bir dikdörtgen oluşturulur, boyutu 50x50 piksel, konumu (300, 100).
        // Kutu, dinamik bir cisim olarak kaydedilir.
        solidRect(50, 50, Colors.RED).position(300, 100).registerBodyWithFixture(type = BodyType.DYNAMIC)

        // Kırmızı bir dikdörtgen oluşturulur, boyutu 50x50 piksel, konumu (450, 100), dönüş açısı 15 derece.
        // Kutu, dinamik bir cisim olarak kaydedilir.
        solidRect(50, 50, Colors.RED).position(450, 100).rotation(15.degrees)
            .registerBodyWithFixture(type = BodyType.DYNAMIC)

        // Beyaz bir dikdörtgen oluşturulur, boyutu 600x100 piksel, konumu (100, 600).
        // Kutu, statik bir cisim olarak kaydedilir, sürtünmesi 0.2 olarak ayarlanır.
        solidRect(600, 100, Colors.WHITE).position(100, 600).registerBodyWithFixture(
            type = BodyType.STATIC,
            friction = 0.2
        )
        onClick {
            val pos = it.currentPosLocal // Tıklama olayından gelen pozisyonu alır
            solidRect(50, 50, Colors.RED).position(pos.x, pos.y)
                .rotation(randomAngle()) // 50x50 boyutunda, kırmızı bir dikdörtgen oluşturur ve tıklama pozisyonuna yerleştirir. Ayrıca rastgele bir açı ile döndürür.
                .registerBodyWithFixture(type = BodyType.DYNAMIC) // Oluşturulan kutunun Box2D dünyasına dinamik bir cisim olarak kaydedilmesi sağlanır.
        }

// Tıklama olayı eklenir. Tıklanan yere, boyutu 50x50 piksel olan kırmızı bir dikdörtgen yerleştirilir.
// Kutu, dinamik bir cisim olarak kaydedilir, dönüş
    }

}
fun randomAngle(): Angle = Random.nextInt(0, 90).degrees // Rastgele bir açı değeri döndüren fonksiyon. Açı derecesi, 0 ila 90 arasında rastgele seçilir.
