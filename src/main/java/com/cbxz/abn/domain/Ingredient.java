package com.cbxz.abn.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "updated")
    @UpdateTimestamp
    private LocalDateTime updated;
    @ManyToMany(mappedBy = "ingredient", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Recipe> recipes;
}
