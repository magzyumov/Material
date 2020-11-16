package ru.magzyumov.material.common.helpers

import android.app.Activity
import android.view.View
import com.google.android.material.snackbar.Snackbar


class SnackBarHelper {
    private var messageSnackBar: Snackbar? = null

    fun show(
        activity: Activity,
        message: String,
        action: View.OnClickListener
    ) {

        messageSnackBar = Snackbar.make(
            activity.findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_INDEFINITE
        )

        messageSnackBar?.let {
            it.view.setBackgroundColor(-0x40cdcdce)
            it.setAction("YES", action)
            it.show()
        }
    }

    fun show(
            view: View,
            message: String
    ) {

        messageSnackBar = Snackbar.make(
                view,
                message,
                Snackbar.LENGTH_LONG
        )

        messageSnackBar?.let {
            it.view.setBackgroundColor(-0x40cdcdce)
            it.show()
        }
    }
}