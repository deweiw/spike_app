package com.dandv.consultant.domain.common.basemapper

interface BaseMapperToData<SOURCE, TARGET> {

    fun mapToData(toBeTransformed: SOURCE): TARGET
}