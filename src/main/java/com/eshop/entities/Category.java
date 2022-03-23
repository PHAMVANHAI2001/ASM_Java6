// Generated with g9.

package com.eshop.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(indexes = { @Index(name = "Category_Slug_IX", columnList = "Slug", unique = true) })
public class Category implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false)
	private Integer id;

	@Column(name = "Name", nullable = false, length = 50)
	private String name;

	@Column(name = "Slug", nullable = false, length = 50)
	private String slug;

	@OneToMany(mappedBy = "category")
	private Set<Product> products = new LinkedHashSet<>();

}
