package com.tistory.eclipse4j.api.board.response

import com.tistory.eclipse4j.persistence.board.entity.CsBoardEntity

data class BoardResponse (
    val id: Long,
    val title: String,
) {
    companion object {
        fun of(e: CsBoardEntity): BoardResponse {
            return BoardResponse(
                id = e.id!!,
                title = e.title
            )
        }
    }
}