package com.dandv.spike.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import coil.annotation.ExperimentalCoilApi
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.dandv.spike.R
import com.dandv.spike.ui.home.model.HomePageUiModel

@ExperimentalCoilApi
@Composable
fun ProfileView(homePageUiModel: HomePageUiModel) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = dimensionResource(id = R.dimen.large_margin))
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(homePageUiModel.imageUrl)
                .build(),
            loading = { LoadingProgressBar(true) },
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(R.dimen.profile_image_height))
        )
        Text(
            text = homePageUiModel.name,
            modifier = Modifier.padding(dimensionResource(R.dimen.small_margin)),
            style = MaterialTheme.typography.subtitle2
        )
        Text(
            text = homePageUiModel.phone,
            modifier = Modifier.padding(dimensionResource(R.dimen.small_margin)),
            style = MaterialTheme.typography.body1,
        )
        Text(
            text = homePageUiModel.email,
            modifier = Modifier.padding(dimensionResource(R.dimen.small_margin)),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.primary
        )
        Text(
            text = homePageUiModel.summary,
            modifier = Modifier.padding(dimensionResource(R.dimen.small_margin)),
            style = MaterialTheme.typography.body1
        )
        ExtraInfoLayout()
    }
}
