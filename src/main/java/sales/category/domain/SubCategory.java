package sales.category.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * Created by taras on 11.08.15.
 */
@Entity
@Table(name="subCategories")
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(50)")
    @JsonProperty
    private String name;

    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name = "category", referencedColumnName = "id")
    @JsonProperty
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
