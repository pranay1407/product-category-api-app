package com.pinnacle.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Category
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;

    @OneToMany(targetEntity=Product.class, cascade = CascadeType.REMOVE, mappedBy = "category", fetch = FetchType.EAGER, orphanRemoval = true)
    @ToString.Exclude
    private List<Product> product;

}
