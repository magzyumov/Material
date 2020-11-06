package ru.magzyumov.material.di.services

import ru.magzyumov.material.common.helpers.PreferenceHelper
import ru.magzyumov.material.common.helpers.SnackBarHelper
import ru.magzyumov.material.common.helpers.ThemeHelper
import javax.inject.Inject

class BaseServices @Inject constructor(
    val preferenceHelper : PreferenceHelper,
    val snackBarHelper: SnackBarHelper,
    val themeHelper: ThemeHelper
) {

}