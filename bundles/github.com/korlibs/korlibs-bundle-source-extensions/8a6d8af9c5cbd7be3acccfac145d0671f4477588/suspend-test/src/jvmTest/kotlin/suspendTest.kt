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
		Thread.sleep(1L)
	}
	if (fexception != null) {
		throw fexception!!
	}
}
