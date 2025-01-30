package com.tistory.eclipse4j.persistence.board.repository

import com.tistory.eclipse4j.persistence.board.entity.CsBoardEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CsBoardRepository : JpaRepository<CsBoardEntity, Long>, CsBoardRepositoryCustom {
    fun findByIdAndCreatedBy(id: Long, memberUuid: String): CsBoardEntity?
}
