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
import androidx.constraintlayout.compose.ConstraintLayout
import com.dandv.spike.R
import com.dandv.spike.ui.collection.model.ProjectItemUiModel

@Composable
fun ProjectList(projects: List<ProjectItemUiModel>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.large_margin))
    ) {
        items(items = projects) { project ->
            ProjectViewHolder(project)
        }
    }
}

@Composable
fun ProjectViewHolder(project: ProjectItemUiModel) {
    val summaryMargin = dimensionResource(id = R.dimen.small_margin)
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.large_margin))
    ) {
        val (title, duration, summary) = createRefs()
        Text(
            text = project.title,
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            },
            style = MaterialTheme.typography.h5
        )
        Text(
            text = project.time,
            modifier = Modifier.constrainAs(duration) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            },
            style = MaterialTheme.typography.h5
        )
        Text(
            text = project.summary,
            modifier = Modifier.constrainAs(summary) {
                top.linkTo(title.bottom, margin = summaryMargin)
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            },
            style = MaterialTheme.typography.h6
        )
    }
}
