package com.scallop.domain.entities

data class MovieSourcesEntity(
    var page: Int? = 0,
    var total_results: Int? = 0,
    var total_pages: Int? = 0,
    var results: List<MovieItemEntity> = emptyList()
)