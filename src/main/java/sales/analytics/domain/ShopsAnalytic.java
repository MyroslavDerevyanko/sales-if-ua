package sales.analytics.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Myroslav on 29.07.2015.
 */

@Entity
@Table(name = "shopAnalytics")
@ApiModel(value = "ShopAnalyticsDomain", description = "Simple ShopsAnalytic domain")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)

public class ShopsAnalytic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private int id;

    /**
     * Amount of users who have registered for a day
     */
    @Column(name = "shopsAmount")
    @JsonProperty("shopsAmount")
    private int shopsAmount;

    @Column(name = "date")
    @JsonProperty("date")
    private Date date;

    public ShopsAnalytic() {
    }

    public ShopsAnalytic(int shopsAmount, Date date) {
        this.shopsAmount = shopsAmount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShopsAmount() {
        return shopsAmount;
    }

    public void setShopsAmount(int shopsAmount) {
        this.shopsAmount = shopsAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
