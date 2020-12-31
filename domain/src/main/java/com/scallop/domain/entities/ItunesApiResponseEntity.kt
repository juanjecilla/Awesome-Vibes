package com.scallop.domain.entities


data class ItunesApiResponseEntity<T>(
    val resultCount: Long,
    val results: List<T>
)
