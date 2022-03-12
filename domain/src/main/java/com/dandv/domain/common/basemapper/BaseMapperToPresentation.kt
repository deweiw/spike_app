package com.dandv.domain.common.basemapper

/**
 * In clean architecture, each layer is decoupled to each other.
 * Data between each layer should also be decoupled . When passing the data between layers, need to use mappers to convert
 * the data from one layer to the layer consuming the data
 *
 * For example: when presentation layer (activity) requests a data from the domain layer (use case), the use case returns
 * data as an data entity, and couldn't be used directly by the UI. Therefore, we need a mapper to map
 * an Entity data into the UiModel Data
 */
interface BaseMapperToPresentation<SOURCE, TARGET> {

    fun mapToPresentation(toBeTransformed: SOURCE): TARGET
}
