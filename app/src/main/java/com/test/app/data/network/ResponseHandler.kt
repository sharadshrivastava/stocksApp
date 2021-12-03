package com.test.app.data.network


import com.google.gson.stream.MalformedJsonException
import com.test.app.R
import com.test.app.StocksApp
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

class ResponseHandler {

    companion object {
        fun <T> handleSuccess(data: T) = Resource.success(data)

        fun <T : Any?> handleException(e: Exception): Resource<T> {
            return when (e) {
                is HttpException -> Resource.error(getErrorMessage(e.code()), null)
                is SocketTimeoutException -> Resource.error(getString(R.string.timeout), null)
                is ConnectException -> Resource.error(getString(R.string.connection_error), null)
                is MalformedJsonException -> Resource.error(getString(R.string.malformed_data), null)
                else -> Resource.error(getErrorMessage(Int.MAX_VALUE), null)
            }
        }

        private fun getErrorMessage(code: Int): String {
            return when (code) {
                ErrorCodes.UN_AUTH.code -> getString(R.string.unauthorized)
                ErrorCodes.NOT_FOUND.code -> getString(R.string.not_found)
                ErrorCodes.INTERNAL_ERR.code -> getString(R.string.internal_err)
                ErrorCodes.SVC_UN_AVAIL.code -> getString(R.string.svc_un_avail)
                else -> getString(R.string.response_error)
            }
        }

        private fun getString(id: Int): String = StocksApp.appContext?.getString(id) ?: ""
    }

    enum class ErrorCodes(val code: Int) {
        UN_AUTH(401),
        NOT_FOUND(404),
        INTERNAL_ERR(500),
        SVC_UN_AVAIL(503)
    }
}

class Resource<T> private constructor(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    companion object {
        fun <T> success(data: T) = Resource(Status.SUCCESS, data, null)

        fun <T> error(msg: String?, data: T?) = Resource(Status.ERROR, data, msg)

        fun <T> loading(data: T? = null) = Resource(Status.LOADING, data, null)
    }
}