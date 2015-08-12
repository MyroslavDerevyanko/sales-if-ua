package sales.analytics.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Myroslav on 11.08.2015.
 */
@Entity
@Table(name = "clientsAnalytics")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
public class ClientsAnalytic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private int id;

    @Column(name = "usersAmount")
    @JsonProperty("userAmount")
    private int usersAmount;

    @Column(name = "date")
    @JsonProperty("date")
    private Date date;

    public ClientsAnalytic() {
    }

    public ClientsAnalytic(int usersAmount, Date date) {
        this.usersAmount = usersAmount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsersAmount() {
        return usersAmount;
    }

    public void setUsersAmount(int usersAmount) {
        this.usersAmount = usersAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
