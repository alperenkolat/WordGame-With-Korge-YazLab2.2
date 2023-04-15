import com.soywiz.korge.gradle.*

plugins {
	alias(libs.plugins.korge)
}

korge {
    id = "com.example.example"
// To enable all targets at once

    //targetAll()

// To enable targets based on properties/environment variables
    //targetDefault()

// To selectively enable targets

    targetJvm()
    //targetJs()
    //targetDesktop()
    //targetIos()
    //targetAndroidIndirect() // targetAndroidDirect()
    orientation = com.soywiz.korge.gradle.Orientation.PORTRAIT
    bundle("https://github.com/korlibs/korge-bundles.git::korge-box2d::7439e5c7de7442f2cd33a1944846d44aea31af0a##9fd9d54abd8abc4736fd3439f0904141d9b6a26e9e2f1e1f8e2ed10c51f490fd")
    bundle("https://github.com/korlibs/korlibs-bundle-source-extensions.git::korma-rectangle-experimental-ext::696a97640bb93a66f07ca008cca84b1ae4013e57##d2d9e3eb8f9f8eb5c137e847677eb8b3e9038c30d1f4457d1bd05cafc5c3f251")
    bundle("https://github.com/korlibs/korge-bundles.git::korge-services::4ac7fcee689e1b541849cedd1e017016128624b9##392d5ed87428c7137ae40aa7a44f013dd1d759630dca64e151bbc546eb25e28e")
}
