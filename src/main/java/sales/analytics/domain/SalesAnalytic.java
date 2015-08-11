package sales.analytics.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModel;
import sales.users.domain.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Myroslav on 10.08.2015.
 */
@Entity
@Table(name = "salesAnalytics")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
public class SalesAnalytic {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @JsonProperty("id")
    private int id;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "shop", referencedColumnName = "id")
    @JsonProperty("shop")
    private User shop;

    @Column(name = "soldProducts")
    @JsonProperty("soldProducts")
    private int soldProducts;

    @Column(name = "profit")
    @JsonProperty("profit")
    private double profit;

    @Column(name = "date")
    @JsonProperty("date")
    private Date date;

    public SalesAnalytic() {
    }

    public SalesAnalytic(User shop, int soldProducts, double profit, Date date) {
        this.shop = shop;
        this.soldProducts = soldProducts;
        this.profit = profit;
        this.date=date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getShop() {
        return shop;
    }

    public void setShop(User shop) {
        this.shop = shop;
    }

    public int getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(int soldProducts) {
        this.soldProducts = soldProducts;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
