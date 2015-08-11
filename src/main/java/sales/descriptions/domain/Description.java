package sales.descriptions.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import sales.goods.domain.Good;
import sales.users.domain.User;

import javax.persistence.*;

/**
 * Created by volodya on 30.07.15.
 */

@Entity
@Table(name = "descriptions")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Description {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @JsonProperty("id")
    private int id;

    @Column(name = "name", columnDefinition = "VARCHAR(255) COLLATE utf8_general_ci")
    @JsonProperty("name")
    private String name;

    @Column(name = "value", columnDefinition = "VARCHAR(255) COLLATE utf8_general_ci")
    @JsonProperty("value")
    private String value;

    @ManyToOne(targetEntity = Good.class)
    @JoinColumn(name = "good_id", referencedColumnName = "id")
    @JsonIgnore
    private Good good;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Description{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", goodId=" + good +
                '}';
    }
}
