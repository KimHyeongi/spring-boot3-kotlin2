package com.tistory.eclipse4j.persistence.board.entity

import com.tistory.eclipse4j.persistence.base.entity.AuditingEntity
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import java.time.LocalDateTime

@Entity
@Table(
    name = "cs_board",
    indexes = [
        Index(name = "idx_cs_board_type_category_type", columnList = "board_type, board_category_type", unique = false)
    ]
)
@SQLDelete(sql = "UPDATE cs_board SET deleted = true WHERE id=?")
@Comment(value = "문의 남기기")
class CsBoardEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    title: String,
    receiveEmail: String? = null,
    content: String,
    imageUrl: String? = null,
    applyPinType: CsBoardPinType = CsBoardPinType.NONE,
    boardType: CsBoardType,
    categoryType: CsBoardCategoryType? = null,
    displayType: CsBoardDisplayType = CsBoardDisplayType.NORMAL,
    dpStartedAt: LocalDateTime? = LocalDateTime.now(),
    dpEndedAt: LocalDateTime? = LocalDateTime.now(),
    sortNum: Int = 0
) : AuditingEntity() {
    @Comment(value = "제목")
    @Column(name = "title", nullable = false, length = 1000)
    var title: String = title
        protected set

    @Comment(value = "본문")
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    var content: String = content
        protected set

    @Comment(value = "이미지")
    @Column(name = "image_url", nullable = true, columnDefinition = "varchar(255)")
    var imageUrl: String? = imageUrl
        protected set

    @Comment(value = "수신받을 이메일")
    @Column(name = "receive_email", nullable = true, columnDefinition = "varchar(255)")
    var receiveEmail: String? = receiveEmail
        protected set

    @Comment("고정 여부")
    @Enumerated(EnumType.STRING)
    @Column(name = "apply_pin_type", nullable = false, columnDefinition = "varchar(10)")
    var applyPinType: CsBoardPinType = applyPinType
        protected set

    @Comment(value = "형태")
    @Enumerated(EnumType.STRING)
    @Column(name = "board_type", nullable = false, columnDefinition = "varchar(20)")
    var boardType: CsBoardType = boardType
        protected set

    @Comment(value = "노출형태")
    @Enumerated(EnumType.STRING)
    @Column(name = "display_type", nullable = true, columnDefinition = "varchar(20)")
    var displayType: CsBoardDisplayType = displayType
        protected set

    @Comment(value = "분류")
    @Enumerated(EnumType.STRING)
    @Column(name = "board_category_type", nullable = true, columnDefinition = "varchar(25)")
    var categoryType: CsBoardCategoryType? = categoryType
        protected set

    @Comment("공지 기간")
    @Column(name = "started_at", nullable = true)
    var dpStartedAt: LocalDateTime? = dpStartedAt
        protected set

    @Comment("공지 기간")
    @Column(name = "ended_at", nullable = true)
    var dpEndedAt: LocalDateTime? = dpEndedAt
        protected set

    @Comment("순서")
    @Column(name = "sort_num", nullable = false)
    var sortNum: Int = sortNum
        protected set

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "csBoard")
    @SQLRestriction("deleted = false")
    var comments: MutableList<CsBoardCommentEntity> = mutableListOf()

    fun update(source: CsBoardEntity) {
        this.title = source.title
        this.receiveEmail = source.receiveEmail
        this.content = source.content
        this.imageUrl = source.imageUrl
        this.applyPinType = source.applyPinType
        this.boardType = source.boardType
        this.categoryType = source.categoryType
        this.displayType = source.displayType
        this.dpStartedAt = source.dpStartedAt
        this.dpEndedAt = source.dpEndedAt
    }

    fun deleted() {
        this.deleted = true
    }
}

enum class CsBoardType(val description: String) {
    FAQ("FAQ"),
    NOTICE("공지사항"),
    INQUIRY("1:1 문의")
}

enum class CsBoardPinType(val description: String) {
    NONE("고정없음"),
    TOP("상단고정"),
}

enum class CsBoardDisplayType(val description: String) {
    NORMAL("일반게시"),
    POPUP("팝업게시"),
}

enum class CsBoardCategoryType(val description: String, val descriptionEng: String, val category: CsBoardType) {
    INQUIRY_MEMBER_JOIN("회원가입", "Sign up", CsBoardType.INQUIRY),
    INQUIRY_MEMBER_LEAVE("회원탈퇴", "Withdrawing a member", CsBoardType.INQUIRY),
    INQUIRY_SERVICE("서비스", "Service", CsBoardType.INQUIRY),
    FAQ_MEMBER_JOIN("회원가입", "Sign up", CsBoardType.FAQ),
    FAQ_MEMBER_LEAVE("회원탈퇴", "Withdrawing a member", CsBoardType.FAQ),
    FAQ_ETC("기타", "Etc", CsBoardType.FAQ),
    NOTICE("공지사항", "Notice", CsBoardType.NOTICE),
}
