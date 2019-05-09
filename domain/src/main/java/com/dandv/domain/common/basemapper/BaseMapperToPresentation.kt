package com.dandv.consultant.domain.common.basemapper

interface BaseMapperToPresentation<SOURCE, TARGET> {

    fun mapToPresentation(toBeTransformed: SOURCE): TARGET
}