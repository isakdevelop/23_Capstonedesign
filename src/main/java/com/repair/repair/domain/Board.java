package com.repair.repair.domain;

import com.repair.repair.domain.value.Domain;
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
@Getter
@Table(name = "board")
public class Board extends BaseEntity implements Persistable<String> {
    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "type")
    private int type = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();

    protected Board() {
        super(Domain.BOARD);
    }
    @Builder
    private Board(String title, String content, int type, User user)  {
        this();
        this.title = title;
        this.content = content;
        this.user = user;
        this.type = type;
    }

    public void update(String title, String content)    {
        this.title = title;
        this.content = content;
    }


    @Override
    public boolean isNew() {
        return getCreateAt() == null;
    }
}
