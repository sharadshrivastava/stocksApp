package com.test.app.domain.models.ui

data class ViewState(
    val loading: Boolean = false,
    val empty: Boolean = false,
    val success: Boolean = false,
    val error: Boolean = false,
    val errorMsg: String? = null
)