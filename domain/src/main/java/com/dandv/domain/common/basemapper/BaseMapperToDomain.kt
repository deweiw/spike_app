package com.dandv.domain.common.basemapper

interface BaseMapperToDomain<SOURCE, TARGET> {

    fun mapToDomain(toBeTransformed: SOURCE): TARGET
}
