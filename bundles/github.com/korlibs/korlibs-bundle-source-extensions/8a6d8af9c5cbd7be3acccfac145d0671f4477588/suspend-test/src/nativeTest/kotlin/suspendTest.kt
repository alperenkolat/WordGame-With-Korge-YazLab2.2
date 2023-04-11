import kotlinx.cinterop.*
import platform.posix.*
import kotlin.coroutines.*

actual fun suspendTest(callback: suspend () -> Unit): Unit {
	var completed = false
	var fexception: Throwable? = null
	callback.startCoroutine(object : Continuation<Unit> {
		override val context: CoroutineContext = EmptyCoroutineContext
		override fun resumeWith(result: Result<Unit>) {
			fexception = result.exceptionOrNull()
			completed = true
		}
	})
	while (!completed) {
		usleep(1000.convert())
	}
	if (fexception != null) {
		throw fexception!!
	}
}
