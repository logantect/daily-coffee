package com.dailycoffee.support.interfaces

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonUnwrapped

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class ApiResponse<T>(
    val message: String? = null,
    val code: String? = null,
    @JsonUnwrapped val data: T? = null
) {

    companion object {
        fun error(message: String?): ApiResponse<Unit> = ApiResponse(message = message)

        fun error(message: String?, code: String?): ApiResponse<Unit> =
            ApiResponse(message = message, code = code)

        fun <T> success(data: T?): ApiResponse<T> = ApiResponse(data = data)
    }
}
