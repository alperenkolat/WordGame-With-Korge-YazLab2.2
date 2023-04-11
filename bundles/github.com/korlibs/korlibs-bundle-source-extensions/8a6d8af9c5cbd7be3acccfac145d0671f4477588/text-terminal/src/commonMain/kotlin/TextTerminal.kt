import com.soywiz.kds.*
import com.soywiz.klogger.*
import com.soywiz.korge.scene.*
import com.soywiz.korge.view.*

fun Container.textTerminal(nlines: Int = 16, lineSize: Double = 16.0) = TextTerminal(nlines, lineSize).also { addChild(it) }

class TextTerminal(val nlines: Int = 16, val lineSize: Double = 16.0) : Container(), Logger.Output {
	private var printedLines = 0

	//fun logger() = Logger().also { it.output = this }
	fun logger(name: String, level: Logger.Level = Logger.Level.TRACE) = Logger(name).also {
		it.output = this
		it.level = level
	}

	val lines = (0 until nlines).map { nline ->
		text("", lineSize, font = debugBmpFont).xy(0.0, lineSize * nline).also {
			it.smoothing = false
		}
	}

	fun println(text: String) {
		if (printedLines > nlines - 1) {
			for (n in 0 until nlines) {
				lines.getCyclic(printedLines - nlines + n + 1).y = n * lineSize
			}
		}
		lines.getCyclic(printedLines++).text = text
	}

	override fun output(logger: Logger, level: Logger.Level, msg: Any?) {
		println("$level: $msg")
	}
}
