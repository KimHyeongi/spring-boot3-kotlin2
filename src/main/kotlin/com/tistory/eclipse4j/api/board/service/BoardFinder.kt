package com.tistory.eclipse4j.api.board.service

import com.tistory.eclipse4j.api.board.response.BoardResponse
import com.tistory.eclipse4j.persistence.board.condition.CsBoardApiCondition
import com.tistory.eclipse4j.persistence.board.repository.CsBoardRepository
import org.springframework.stereotype.Service

@Service
class BoardFinder(
    private val csBoardRepository: CsBoardRepository
) {
    fun findAllByCondition(condition: CsBoardApiCondition): List<BoardResponse> {
        return csBoardRepository.findAllByCondition(condition).map {
            BoardResponse.of(it)
        }
    }
}