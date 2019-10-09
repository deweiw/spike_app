package com.dandv.spike.tools

import javax.inject.Inject

class ImageProcessingTargetFactory @Inject constructor() {

    fun getImageProcessingTarget(callback: ImageProcessingTarget.Callback) = ImageProcessingTarget(callback)
}
