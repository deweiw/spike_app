package com.dandv.domain.profile.entity.collection

sealed class CollectionEntity {
    object Error : CollectionEntity()
    object Empty : CollectionEntity()
    data class SkillCollection(val skills: List<SkillData>) : CollectionEntity()
    data class ProjectCollection(val projects: List<ProjectData>) : CollectionEntity()
    data class ExperienceCollection(val experiences: List<ExperienceData>) : CollectionEntity()
}