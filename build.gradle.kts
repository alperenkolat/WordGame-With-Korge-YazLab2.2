import com.soywiz.korge.gradle.*
import org.jetbrains.kotlin.gradle.targets.js.npm.*
import kotlin.script.experimental.jvm.*

plugins {
	alias(libs.plugins.korge)
}


korge {
	id = "com.example.example"
    supportBox2d()
    orientation = com.soywiz.korge.gradle.Orientation.LANDSCAPE
	targetJvm()
    targetAndroidDirect()

}
