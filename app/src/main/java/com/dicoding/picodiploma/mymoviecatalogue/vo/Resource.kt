package com.dicoding.picodiploma.mymoviecatalogue.vo

data class Resource<T>(val status: Status, val body: T?, val message: String?) {
    companion object {
        fun <T> success(body: T?): Resource<T> = Resource(Status.SUCCESS, body, null)

        fun <T> loading(body: T?): Resource<T> = Resource(Status.LOADING, body, null)

        fun <T> error(message: String?, body: T?): Resource<T> =
            Resource(Status.ERROR, body, message)
    }
}