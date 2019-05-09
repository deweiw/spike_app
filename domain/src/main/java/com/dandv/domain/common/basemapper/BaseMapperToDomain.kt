package com.dandv.consultant.domain.common.basemapper

interface BaseMapperToDomain<SOURCE, TARGET> {

    fun mapToDomain(toBeTransformed: SOURCE): TARGET
}