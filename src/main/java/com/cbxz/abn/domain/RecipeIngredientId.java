package com.cbxz.abn.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeIngredientId implements Serializable {
    private Long recipeId;
    private Long ingredientId;
}