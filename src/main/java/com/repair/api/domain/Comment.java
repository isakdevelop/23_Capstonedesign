package com.repair.api.domain;

import com.repair.api.domain.value.Domain;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Persistable;

@Entity
@Table(name = "comments")
@Getter
public class Comment extends BaseEntity implements Persistable<String> {
    @Column(name = "comment")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany(mappedBy = "parentComment")
    private List<Comment> commentlist = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentComment_id")
    private Comment parentComment;


    protected Comment() {
        super(Domain.COMMENT);
    }

    @Builder
    public Comment(String comment, User user, Board board, Comment parentComment) {
        this();
        this.comment = comment;
        this.user = user;
        this.board = board;
        this.parentComment = parentComment;
    }

    public void modify(String comment) {
        this.comment = comment;
    }


    @Override
    public boolean isNew() {
        return getCreateAt() == null;
    }
}
