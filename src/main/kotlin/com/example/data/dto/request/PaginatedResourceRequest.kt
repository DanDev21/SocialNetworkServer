package com.example.data.dto.request

import com.example.Length

data class PaginatedResourceRequest(
    val pageNumber: Int = 0,
    val pageSize: Int = Length.PAGINATED_RESOURCE_PAGE,
)
