package ru.magzyumov.material.common

interface Constants {
    interface Preferences {
        companion object {
            const val SHARED_PREFERENCES_KEY = "shared_preferences_key"
            const val SETTINGS = "settings"
            const val THEME = "theme"
        }
    }

    interface RequestCode {
        companion object {
            const val REQUEST_IMAGE_CAPTURE = 1
            const val REQUEST_IMAGE_STORE = 2
            const val REQUEST_TAKE_PHOTO = 3
        }
    }

    interface StringPattern {
        companion object {
            const val TIMESTAMP_PATTERN = "yyyyMMdd_HHmmss"
        }
    }
}