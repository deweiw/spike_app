package com.dandv.spike.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dandv.domain.profile.entity.collection.CollectionType
import com.dandv.spike.R
import com.dandv.spike.ui.home.HomePageViewModel

@Composable
fun ExtraInfoLayout(homePageViewModel: HomePageViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        CollectionType.SKILLS.let { ExtraInfoItem(collectionType = it) {
            homePageViewModel.requestCollection(it)
        }}
        CollectionType.PROJECTS.let { ExtraInfoItem(collectionType = it) {
            homePageViewModel.requestCollection(it)
        }}
        CollectionType.EXPERIENCES.let { ExtraInfoItem(collectionType = it) {
            homePageViewModel.requestCollection(it)
        }}
    }
}

@Composable
fun ExtraInfoItem(collectionType: CollectionType, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(id = getExtraInfoString(collectionType)),
            modifier = Modifier
                .padding(dimensionResource(R.dimen.small_margin))
                .weight(1F),
            style = MaterialTheme.typography.subtitle2
        )
        Text(
            text = stringResource(R.string.more_label),
            modifier = Modifier.padding(dimensionResource(R.dimen.small_margin)),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.primary
        )
    }
}

private fun getExtraInfoString(collectionType: CollectionType): Int {
    return when (collectionType) {
        CollectionType.SKILLS -> R.string.skill_label
        CollectionType.PROJECTS -> R.string.project_label
        CollectionType.EXPERIENCES -> R.string.experience_label
    }
}

