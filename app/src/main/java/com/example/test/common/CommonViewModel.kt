package com.example.test.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.atomic.AtomicInteger

open class CommonViewModel : ViewModel() {

    // Any model for providing error
    protected val _error: SingleLiveEvent<Any> =
        SingleLiveEvent()

    protected val _noInternet: SingleLiveEvent<Int> =
        SingleLiveEvent()

    protected val _progress: SingleLiveEvent<Boolean> =
        SingleLiveEvent()

    private val requestsCount: AtomicInteger by lazy {
        AtomicInteger(0)
    }

    val error: LiveData<Any>
        get() = _error

    val io: LiveData<Int>
        get() = _noInternet

    val progressEvent: LiveData<Boolean>
        get() = _progress


    protected suspend fun <V, S> S.tryHttp(invoke: suspend S.() -> V): V? {
        _progress.postValue(true)
        requestsCount.incrementAndGet()
        var value: V? = null
        try {
            value = invoke()
        } catch (e: SocketTimeoutException) {
//            _noInternet.postValue(R.string.not_answer_from_server)
        } catch (e: IOException) {
//            _noInternet.postValue(R.string.no_internet)
        } catch (e: HttpException) {
            e.printStackTrace()

        } catch (e: Throwable) {
//            _error.postValue()
        } finally {
            val count = requestsCount.decrementAndGet()
            _progress.postValue(count != 0)
        }
        return value
    }


}