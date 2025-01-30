package com.tistory.eclipse4j.persistence.board.repository

import com.tistory.eclipse4j.persistence.board.entity.CsBoardCommentEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CsBoardCommentRepository : JpaRepository<CsBoardCommentEntity, Long>
