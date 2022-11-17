package br.com.lighttasks.presentation.data.remote.util

import br.com.lighttasks.commom.model.Message
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import br.com.lighttasks.commom.model.Result


internal suspend fun <T : Any> safeApiCall(call: suspend () -> Result<T>): Result<T> {
    return withContext(Dispatchers.IO) {
        try {
            call()
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> Result.Error(Result.Error.Type.NETWORK)
                is HttpException -> {
                    val code = throwable.code()
                    val message = convertErrorBody(throwable)
                    Result.Error(Result.Error.Type.HTTP, code, message)
                }
                else -> {
                    Result.Error(Result.Error.Type.GENERIC)
                }
            }
        }
    }
}

internal suspend fun <T : Any> unsafeApiCall(call: suspend () -> T): T {
    return coroutineScope {
        try {
            call()
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> {
                    Result.Error(Result.Error.Type.NETWORK)
                    throw Throwable("Ocorreu um problema de conexão")
                }
                is HttpException -> {
                    val code = throwable.code()
                    val message = convertErrorBody(throwable)
                    Result.Error(Result.Error.Type.HTTP, code, message)
                    throw Throwable(message?.error?.message)
                }
                else -> {
                    Result.Error(Result.Error.Type.GENERIC)
                    throw throwable
                }
            }
        }
    }
}


private fun convertErrorBody(throwable: HttpException): Message? {
    return try {
        val json: String = throwable.response()?.errorBody()?.string() ?: ""
        val jsonObject = JSONObject(json)
        Gson().fromJson(jsonObject.toString(), Message::class.java)
    } catch (throwable: Throwable) {
        return null
    }
}