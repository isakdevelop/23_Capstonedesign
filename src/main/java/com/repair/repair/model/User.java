package com.repair.repair.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

import com.repair.repair.model.value.Domain;

import org.springframework.data.domain.Persistable;

@Entity
@Getter
@Table(name = "user")
public class User extends BaseEntity implements Persistable<String> {

    @Column(name = "email", updatable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "type")
    private int type = 0;

    @OneToMany(mappedBy = "user")
    private List<Board> boardList = new ArrayList<>();

    protected User()    {
        super(Domain.USER);
    }

    @Builder
    protected User(String email, String password, String name, String phone) {
        this();
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    public void updateUser(String name) {
        this.name = name;
        this.phone = phone;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isNew() {
        return getCreateAt() == null;
    }
}
