package com.tistory.eclipse4j.api.board.controller

import com.tistory.eclipse4j.api.board.response.BoardResponse
import com.tistory.eclipse4j.api.board.service.BoardFinder
import com.tistory.eclipse4j.persistence.base.condition.ApiPageCondition
import com.tistory.eclipse4j.persistence.board.condition.CsBoardApiCondition
import com.tistory.eclipse4j.persistence.board.entity.CsBoardType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BoardController(
    private val boardFinder: BoardFinder
) {

    @GetMapping("/boards")
    fun findAll(): ResponseEntity<List<BoardResponse>> {
        return ResponseEntity.ok(
            boardFinder.findAllByCondition(
                CsBoardApiCondition(
                    boardType = CsBoardType.NOTICE,
                    page = ApiPageCondition(1, 10)
                )
            )
        )
    }
}