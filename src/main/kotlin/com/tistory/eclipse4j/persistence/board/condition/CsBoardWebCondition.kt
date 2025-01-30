package com.tistory.eclipse4j.persistence.board.condition

import com.querydsl.core.BooleanBuilder
import com.tistory.eclipse4j.persistence.board.entity.CsBoardCategoryType
import com.tistory.eclipse4j.persistence.board.entity.CsBoardType
import com.tistory.eclipse4j.persistence.board.entity.QCsBoardEntity
import org.springframework.data.domain.Pageable

data class CsBoardWebCondition(
    val boardType: CsBoardType,
    val term: String? = null,
    val categoryType: CsBoardCategoryType? = null,
    val pageable: Pageable
) {
    private val q = QCsBoardEntity.csBoardEntity
    fun predicate(): BooleanBuilder {
        val queryBuilder = BooleanBuilder()
        queryBuilder.and(q.boardType.eq(boardType))
        categoryType?.let {
            queryBuilder.and(q.categoryType.eq(categoryType))
        }
        term?.let {
            queryBuilder.and(q.title.contains(term))
        }
        queryBuilder.and(q.deleted.eq(false))
        return queryBuilder
    }
}
