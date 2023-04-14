import com.soywiz.korge.scene.*
import com.soywiz.korge.view.*
import com.soywiz.korge.Korge
import com.soywiz.korge.box2d.*
import com.soywiz.korge.box2d.body
import com.soywiz.korge.debug.*
import com.soywiz.korge.input.onClick
import com.soywiz.korge.ui.*
import com.soywiz.korge.view.position
import com.soywiz.korge.view.rotation
import com.soywiz.korge.view.roundRect
import com.soywiz.korge.view.solidRect
import com.soywiz.korim.color.Colors
import com.soywiz.korim.font.*
import com.soywiz.korio.file.std.*
import com.soywiz.korma.geom.degrees
import com.soywiz.korma.geom.vector.*
import com.soywiz.korma.random.*
import kotlinx.coroutines.*
import org.jbox2d.dynamics.*

import kotlin.concurrent.*
import kotlin.random.Random

suspend fun main() = Korge(width = 512, height =1280, bgcolor = Colors["#2b2b2b"]) {
    val sceneContainer = sceneContainer()
    sceneContainer.changeTo({ MyScene() })
}

class MyScene : Scene() {
    val destiny=10.0
    val boxSize=49.0
    lateinit var scoreText: Text // skor kutusunu tutacak değişken
    lateinit var InputText:Text
    var contText="beyhan ;)"
    var score1 = 0 // skoru tutacak değişken
    val contentInput=ArrayList<String>()
    override suspend fun SContainer.sceneInit() {
        val font = resourcesVfs["BebasNeue-Regular.ttf"].readTtfFont()
        graphics {
            it.position(0.0,350.0)
            fill(Colors["#474748"]) {
                roundRect(0.0, 0.0, 512.0, 200.0, 5.0)
            }
        }
        val bgScore= circle(80.0,  Colors["#75888c"]) {
            position(180.0, 370.0)
        }
        scoreText = text("$score1", textSize = 32.0,  ).centerOn(bgScore)

         val bgInput=solidRect(412, 50, Colors.DARKGOLDENROD).position(0, 1050)
             .registerBodyWithFixture(
             type = BodyType.STATIC,
             friction = 0.99
         )
        InputText =text("$contText", textSize = 30.0,Colors.WHITE,font)
            .centerOn(bgInput)


    }

    override suspend fun SContainer.sceneMain() {
        val random1 = Random(0L)
        val font = resourcesVfs["BebasNeue-Regular.ttf"].readTtfFont()
        val cellSize = views.virtualWidth / 10.0
        val fieldSize = 50 + 8 * cellSize
        val leftIndent = (views.virtualWidth - fieldSize) / 2
        val topIndent = 150.0
        val charr = "ABCDEFGHIJKLMNOPRSTUVYZÇĞİÖŞÜ"
        val charrList = charr.split("").filter { it.isNotEmpty() }.toList() as ArrayList<String>
        var falseCount= 0


        charrList.shuffle()
        solidRect(512, 50, Colors.DARKGOLDENROD).position(0, 1050).registerBodyWithFixture(
            type = BodyType.STATIC,
            friction = 0.99
        ).visible=false

        graphics {
            it.position(0.0,550.0)
            fill(Colors["#cec0b2"]) {
                roundRect(0.0, 0.0, 512.0, 500.0, 5.0)
            }
        }

 /*       val bgScore= circle(80.0,  Colors["#75888c"]) {
            position(180.0, 370.0)
        }
        text("", cellSize * 0.5, Colors.WHITE, ).centerOn(bgScore)
          */
        //süs
      solidRect(240.0,7.0,  Colors.GOLD) {
            position(140.0, 550.0)
        }


        //direk
        for (i in 0..8)
            solidRect(3, 470, Colors.BLACK).position(2+i*63, 600).registerBodyWithFixture(
                type = BodyType.STATIC,
                friction = 0.99
            ).visible=false
        //silme butonu
        val  removeBttn=  uiButton(width = 105.0, 65.0, text = "ğ") {
            position(-1, 1045)
            onPress {
                contentInput.clear()
                UpdateContent("")
                println(contentInput.joinToString(separator = ""))  }
        }.also { it.colorMul = Colors["#ff1552"] }
            .also { it.textColor = Colors.WHITE }
            .also { it.textSize =cellSize/1.5 }
        text("X", cellSize * 0.5, Colors.WHITE, font).centerOn(removeBttn)

        //onaylama butonu
        uiButton(width = 105.0, 65.0, text = "OK") {
            position(410, 1045)
            onPress {
            if(contentInput.joinToString(separator = "")!="DD"){
                falseCount+=1
                if(falseCount==3){
                    falseCount=0
                    for (i in 0..7) {
                        val  charRand1 = Random.nextInt(0,charrList.size)
                        uiButton(width = boxSize, boxSize, text = charrList[charRand1]) {
                            position(13+ i * 63, 450).rotation(0.degrees)

                            onPress {
                                println(charrList[charRand1])
                                UpdateContent(charrList[charRand1])

                            }
                        }.also {it.colorMul = random1[random1[Colors.CYAN, Colors.WHITE], random1[Colors.YELLOW, Colors.CYAN]]
                        }.registerBodyWithFixture(type = BodyType.DYNAMIC, density = destiny, friction = 100.0,angularDamping = 50.0, gravityScale = 2.0)
                            .also { it.textColor = Colors.AZURE }
                            .also { it.textSize =cellSize/2 }

                    }
                }
            }
            }
        }.also { it.colorMul = Colors["#4effeb"] }
            .also { it.textColor = Colors.WHITE }
            .also { it.textSize =cellSize/1.5 }

   for (j in 0..2){
            for (i in 0..7) {
                val  charRand = Random.nextInt(0,charrList.size)
              val alp=  uiButton(width = boxSize, boxSize, text = charrList[charRand]) {
                    position(13+ i * 63, 450).rotation(0.degrees)

                    onPress {
                        println(charrList[charRand])
                        UpdateContent(charrList[charRand])
                    }
                }.also {it.colorMul = random1[random1[Colors.CYAN, Colors.WHITE], random1[Colors.YELLOW, Colors.CYAN]]
                }.registerBodyWithFixture(type = BodyType.DYNAMIC, density = destiny, friction = 100.0,angularDamping = 50.0, gravityScale = 2.0)
                    .also { it.textColor = Colors.AZURE }
                    .also { it.textSize =cellSize/2 }
                  .also { it.textFont =font }

            }}
        onClick {
//            for (i in 0..7) {
//           uiButton(width = boxSize, boxSize, text = "A") {
//                    position(13+ i * 63, 450).rotation(0.degrees)
//
//                    onPress {println(it.startGlobal)
//                    }
//                }.also { it.colorMul = random1[random1[Colors.RED, Colors.AQUA], random1[Colors.YELLOW, Colors.BLUE]] }
//                    .registerBodyWithFixture(type = BodyType.DYNAMIC, density = destiny, friction =2.99)
//                    .also { it.textSize =cellSize/2  }
//            }
        }

        GlobalScope.launch {
            while(true) {
                delay(3600)
                val i = Random.nextInt(0, 8)
                uiButton(width = boxSize, boxSize, text = "A") {
                    position(13+ i * 63, 450).rotation(0.degrees)
                    onPress {
                        also { position(500 - i * 55, 4500) }
                        println(charrList[i])
                        updateScore(10)
                    }
                    buttonBackColor = Colors.WHITE
                }.also { it.colorMul = random1[random1[Colors.RED, Colors.AQUA], random1[Colors.YELLOW, Colors.BLUE]] }
                    .registerBodyWithFixture(type = BodyType.DYNAMIC, density = destiny, friction = 100.99, angularDamping = 50.0, gravityScale = 2.0)
                    .also { it.textSize =cellSize/2 }
                    .also { it.textColor = Colors.WHITE }
            }
        }
    }


    fun UpdateContent(newChar: String) {
        contentInput.add(newChar)
        InputText.text = "${contentInput.joinToString(separator = "")}" // skor kutusunu güncelle
    }
    fun updateScore(newScore: Int) {
        score1 += newScore // skoru güncelle
        scoreText.text = "$score1" // skor kutusunu güncelle
    }

}

