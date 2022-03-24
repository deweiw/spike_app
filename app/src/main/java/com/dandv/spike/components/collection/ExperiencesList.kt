package com.dandv.spike.components.collection

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.dandv.spike.R
import com.dandv.spike.ui.collection.model.ExperienceItemUiModel

@Composable
fun ExperiencesList(experiences: List<ExperienceItemUiModel>) {

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.large_margin))
    ) {
        items(items = experiences) { experience ->
            ExperienceViewHolder(experience)
        }
    }
}

@Composable
fun ExperienceViewHolder(experience: ExperienceItemUiModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = dimensionResource(id = R.dimen.small_margin))
    ) {
        Text(
            text = experience.company,
            modifier = Modifier.padding(dimensionResource(R.dimen.small_margin)),
            style = MaterialTheme.typography.subtitle2
        )
        Text(
            text = experience.duration,
            modifier = Modifier.padding(dimensionResource(R.dimen.small_margin)),
            style = MaterialTheme.typography.body1,
        )
        Text(
            text = experience.summary,
            modifier = Modifier.padding(dimensionResource(R.dimen.small_margin)),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.primary
        )
    }
}
