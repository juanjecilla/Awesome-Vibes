package com.scallop.domain.entities

data class MovieItemEntity(
    var id: Long,
    var title: String? = null,
    var posterPath: String? = null
)

