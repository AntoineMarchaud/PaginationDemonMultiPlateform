package com.amarchaud.data.models

sealed class ErrorApiDataModel : Throwable() {
    class ApiServerErrorWithCode(val responseCode: Int, val responseMessage: String) : com.amarchaud.data.models.ErrorApiDataModel()
    class ApiNullBody : com.amarchaud.data.models.ErrorApiDataModel()
    class ApiGenericServerError : com.amarchaud.data.models.ErrorApiDataModel()
    class SocketTimeOutError : com.amarchaud.data.models.ErrorApiDataModel()
    class NoInternetError : com.amarchaud.data.models.ErrorApiDataModel()
}
