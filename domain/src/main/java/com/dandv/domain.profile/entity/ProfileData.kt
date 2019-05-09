package com.dandv.domain.profile.entity

data class ProfileData(
    val summary: String,
    val phone: String,
    val email: String,
    val imageUrl: String,
    val name: String,
    val from: String,
    val to: String,
    val projects: List<ProjectSummary>,
    val skills: List<SkillSummary>,
    val experience: List<ExperienceSummary>
)