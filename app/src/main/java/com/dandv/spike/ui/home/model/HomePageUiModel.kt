package com.dandv.spike.ui.home.model

data class HomePageUiModel(
    val summary: String,
    val phone: String,
    val email: String,
    val imageUrl: String,
    val name: String,
    val from: String,
    val to: String,
    val anyProjects: Boolean,
    val anySkill: Boolean,
    val anyExperience: Boolean
)