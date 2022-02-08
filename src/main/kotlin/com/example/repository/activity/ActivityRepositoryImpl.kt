package com.example.repository.activity

import com.example.domain.data.dto.crud.CrudResult.*
import com.example.domain.model.Activity
import org.litote.kmongo.and
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class ActivityRepositoryImpl(
    database: CoroutineDatabase
): ActivityRepository {

    private val activities = database.getCollection<Activity>()

    override suspend fun add(activity: Activity) = InsertResult(
        succeeded = activities.insertOne(activity).wasAcknowledged(),
        obj = activity
    )

    override suspend fun delete(
        id: String,
        authorUsername: String
    ) = DeleteResult<Activity>(
        deleteCount = activities.deleteOne(
            and(
                Activity::id eq id,
                Activity::authorId eq authorUsername
            )
        ).deletedCount
    )

    override suspend fun getAll(
        pageNumber: Int,
        pageSize: Int,
        targetedUserId: String,
    ) = FindManyResult(
        items = activities.find(Activity::targetUserId eq targetedUserId)
            .skip(pageNumber * pageSize)
            .limit(pageSize)
            .descendingSort(Activity::timestamp)
            .toList()
    )
}