package com.tistory.eclipse4j.persistence.board.repository

import com.tistory.eclipse4j.persistence.board.condition.CsBoardApiCondition
import com.tistory.eclipse4j.persistence.board.condition.CsBoardWebCondition
import com.tistory.eclipse4j.persistence.board.entity.CsBoardEntity
import com.tistory.eclipse4j.persistence.board.entity.QCsBoardEntity
import com.tistory.eclipse4j.persistence.support.JpaQueryDslRepositorySupport
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl

class CsBoardRepositoryImpl :
    JpaQueryDslRepositorySupport(CsBoardEntity::class.java),
    CsBoardRepositoryCustom {

    private val q = QCsBoardEntity.csBoardEntity

    override fun findAllByCondition(condition: CsBoardWebCondition): Page<CsBoardEntity> {
        val contents = from(q)
            .where(condition.predicate())
            .offset(condition.pageable.offset)
            .limit(condition.pageable.pageSize.toLong())
            .orderBy(q.id.desc())
            .fetch()
        val count = from(q).where(condition.predicate()).fetchCount()
        return PageImpl(contents, condition.pageable, count)
    }

    override fun findAllByCondition(condition: CsBoardApiCondition): List<CsBoardEntity> {
        return from(q)
            .where(condition.predicate())
            .offset(condition.page.offset())
            .limit(condition.page.limit())
            .orderBy(q.applyPinType.desc(), q.sortNum.asc(), q.id.desc())
            .fetch()
    }
}
