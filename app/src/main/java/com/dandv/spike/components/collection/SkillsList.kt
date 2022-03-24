package com.dandv.spike.components.collection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.dandv.spike.R
import com.dandv.spike.ui.collection.model.SkillItemUiModel

@Composable
fun SkillsList(skills: List<SkillItemUiModel>) {

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.small_margin))
    ) {
        items(items = skills) { skill ->
            SkillViewHolder(skill)
        }
    }
}
@Composable
fun SkillViewHolder(skill: SkillItemUiModel) {
    Text(
        text = skill.name,
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.large_margin)),
        style = MaterialTheme.typography.h6
    )
}
