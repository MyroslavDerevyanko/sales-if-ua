package sales.payment.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import sales.payment.creaditCard.domain.CreditCard;

import java.util.List;

/**
 * Created by volodya on 10.08.15.
 */
public class RegisteredMultiPaymentDTO {

    @JsonProperty
    private List<Long> goodsId;

    @JsonProperty
    private long userId;

    @JsonProperty
    private long cardId;

    @JsonProperty
    private CreditCard card;

    public List<Long> getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(List<Long> goodsId) {
        this.goodsId = goodsId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public CreditCard getCard() {
        return card;
    }

    public void setCard(CreditCard card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return "RegisteredMultiPaymentDTO{" +
                "goodsId=" + goodsId +
                ", userId=" + userId +
                ", cardId=" + cardId +
                ", card=" + card +
                '}';
    }
}
