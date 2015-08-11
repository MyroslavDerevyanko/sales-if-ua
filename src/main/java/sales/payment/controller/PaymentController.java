package sales.payment.controller;

import com.paypal.core.rest.PayPalRESTException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sales.payment.dto.data.AnonymMultyPaymentDTO;
import sales.payment.paymentHandler.AnonymPaymentHandler;

/**
 * Created by volodya on 29.07.15.
 */
@RestController
@RequestMapping("/payment")
public class PaymentController  {

    @Autowired
    private AnonymPaymentHandler handler;

    protected static Logger logger = LoggerFactory.getLogger(PaymentController.class.getName());

    @RequestMapping(
            method = RequestMethod.POST,
            produces = "application/json; charset=UTF-8",
            consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void anonymSinglePayment(@RequestBody AnonymMultyPaymentDTO anonym) throws PayPalRESTException {
        logger.info("Payment: anonym single payment");
        handler.anonymPayment(anonym);
    }
}
