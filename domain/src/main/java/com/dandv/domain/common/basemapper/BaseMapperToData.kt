package com.dandv.domain.common.basemapper

interface BaseMapperToData<SOURCE, TARGET> {

    fun mapToData(toBeTransformed: SOURCE): TARGET
}
