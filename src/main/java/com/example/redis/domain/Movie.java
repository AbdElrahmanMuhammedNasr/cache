package com.example.redis.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.Instant;
import java.util.Random;
import java.util.UUID;

@Setter
@Getter
@Data
@Entity
@Table(name = "movie")
public class Movie implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id  = new Random().nextLong(100000L,10000000000000L);
    @Column(name = "name")
    private String name;
    @Column(name = "dept")
    private String dept;
    @Column(name = "age")
    private int age;

    @Column(name = "created_by",nullable = false, updatable = false)
    private String createdBy = "tamer";

    @Column(name = "created_at",nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    @Column(name = "modified_by",nullable = false)
    private String modifiedBy ="tamer";

    @Column(name = "modified_at",nullable = false)
    private Instant modifiedAt =  Instant.now() ;

    public Movie() {
    }

    public Movie(String name, String dept, int age) {
        this.name = name;
        this.dept = dept;
        this.age = age;
    }
}
