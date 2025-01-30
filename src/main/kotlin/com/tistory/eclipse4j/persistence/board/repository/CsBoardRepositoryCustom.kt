package com.tistory.eclipse4j.persistence.board.repository

import com.tistory.eclipse4j.persistence.board.condition.CsBoardApiCondition
import com.tistory.eclipse4j.persistence.board.condition.CsBoardWebCondition
import com.tistory.eclipse4j.persistence.board.entity.CsBoardEntity
import org.springframework.data.domain.Page

interface CsBoardRepositoryCustom {
    fun findAllByCondition(condition: CsBoardWebCondition): Page<CsBoardEntity>

    fun findAllByCondition(condition: CsBoardApiCondition): List<CsBoardEntity>
}
