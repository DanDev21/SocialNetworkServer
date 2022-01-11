package com.example.domain.util

object Constants {

    object Time {
        const val ONE_YEAR = 1000L * 60L * 60L * 24L * 365L
    }

    object Length {

        const val POST_PAGE = 20

        object Min {
            const val USERNAME = 3
            const val PASSWORD = 6
        }

        object Max {
            const val COMMENT = 200
        }
    }

    object Database {
        const val NAME = "social_network"
    }

    object Authentication {
        const val ID = "user_id"
    }

    object RequestParam {
        const val PAGE_NUMBER = "page_number"
        const val PAGE_SIZE = "page_size"
        const val POST_ID = "post_id"
    }
}