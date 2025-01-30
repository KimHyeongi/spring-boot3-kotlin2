package com.tistory.eclipse4j.persistence.board.entity

import com.tistory.eclipse4j.persistence.base.entity.AuditingEntity
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLRestriction

@Entity
@Table(
    name = "cs_board_comment",
    indexes = [
        Index(name = "idx_cs_board_comment_board_id", columnList = "cs_board_id", unique = false)
    ]
)
@Comment(value = "문의 댓글")
class CsBoardCommentEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    comments: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cs_board_id")
    @SQLRestriction("deleted = false")
    val csBoard: CsBoardEntity
) : AuditingEntity() {

    @Comment(value = "댓글")
    @Column(name = "comments", nullable = false, columnDefinition = "TEXT")
    var comments: String = comments
        protected set

    fun update(sourceEntity: CsBoardCommentEntity) {
        this.comments = sourceEntity.comments
    }
}
