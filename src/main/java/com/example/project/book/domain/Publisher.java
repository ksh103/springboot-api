package com.example.project.book.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "publisher")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id", nullable = false)
    private Long publisherId;

    @Column(name = "publisher_name", nullable = false)
    private String publisherName;

    @Column(name = "publisher_address")
    private String publisherAddress;
}
