package com.tistory.eclipse4j.persistence.board.condition

import com.querydsl.core.BooleanBuilder
import com.tistory.eclipse4j.persistence.base.condition.ApiPageCondition
import com.tistory.eclipse4j.persistence.board.entity.CsBoardCategoryType
import com.tistory.eclipse4j.persistence.board.entity.CsBoardType
import com.tistory.eclipse4j.persistence.board.entity.QCsBoardEntity
import java.time.LocalDateTime

data class CsBoardApiCondition(
    val term: String? = null,
    val boardType: CsBoardType,
    val categoryType: CsBoardCategoryType? = null,
    val uuid: String? = null,
    val page: ApiPageCondition
) {

    private val q = QCsBoardEntity.csBoardEntity

    fun predicate(): BooleanBuilder {
        val queryBuilder = BooleanBuilder()
        queryBuilder.and(q.boardType.eq(boardType))
        term?.let {
            queryBuilder.and(q.title.contains(term))
        }
        uuid?.let {
            queryBuilder.and(q.createdBy.eq(uuid))
        }
        categoryType?.let {
            queryBuilder.and(q.categoryType.eq(categoryType))
        }
        if (boardType == CsBoardType.NOTICE) {
            queryBuilder.and(q.dpStartedAt.before(LocalDateTime.now()).and(q.dpEndedAt.after(LocalDateTime.now())))
        }
        queryBuilder.and(q.deleted.eq(false))
        return queryBuilder
    }
}
