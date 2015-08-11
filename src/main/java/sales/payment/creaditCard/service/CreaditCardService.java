package sales.payment.creaditCard.service;



import sales.payment.creaditCard.domain.CreditCard;

import java.util.List;

/**
 * Created by volodya on 29.07.15.
 */

public interface CreaditCardService {

    public CreditCard get(long id);

    public List<CreditCard> getAll();

    public CreditCard save(CreditCard card);

    public void delete(long id);

}
