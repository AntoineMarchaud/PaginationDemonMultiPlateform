package com.amarchaud.data.mappers

import com.amarchaud.domain.models.ErrorApiModel

internal fun com.amarchaud.data.models.ErrorApiDataModel.toDomain() = when (this) {
    is com.amarchaud.data.models.ErrorApiDataModel.ApiGenericServerError -> ErrorApiModel.ApiGenericServerError()
    is com.amarchaud.data.models.ErrorApiDataModel.ApiNullBody -> ErrorApiModel.ApiNullBody()
    is com.amarchaud.data.models.ErrorApiDataModel.ApiServerErrorWithCode -> ErrorApiModel.ApiServerErrorWithCode(
        responseCode = this.responseCode,
        responseMessage = this.responseMessage
    )

    is com.amarchaud.data.models.ErrorApiDataModel.NoInternetError -> ErrorApiModel.NoInternetError()
    is com.amarchaud.data.models.ErrorApiDataModel.SocketTimeOutError -> ErrorApiModel.SocketTimeOutError()
}