package sales.goods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sales.goods.domain.Good;
import sales.goods.service.GoodsService;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


@RestController
@RequestMapping("/goods")
public class GoodsController {

    protected static Logger logger = Logger.getLogger(GoodsController.class.getName());

    @Autowired
    @Qualifier("goodsService")
    private GoodsService service;

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public Good getGoods(@RequestParam(value = "id") long id) {
        logger.info("Good: get by id using hibernate");
        return service.get(id);
    }

    @RequestMapping(value = "/",
            method = RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public List<Good> getAllGoods() {
        logger.info("Good: get all by id using hibernate");
        return service.getAll();
    }

    @RequestMapping(value = "/",
            method = RequestMethod.POST,
            produces = "application/json; charset=UTF-8",
            consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Good putGoods(@RequestBody Good goods) throws IOException {
        logger.info("Good: save or update using hibernate");
        return service.save(goods);
    }

    @RequestMapping(value = "",
            method = RequestMethod.DELETE,
            produces = "application/json; charset=UTF-8")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeGoods(@RequestParam(value = "id") int id) throws IOException {
        logger.info("Good: delete using hibernate");
        service.delete(id);
    }

    @RequestMapping(value = "/page",
            method = RequestMethod.POST,
            produces = "application/json; charset=UTF-8",
            consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Good> goodsPagination(
            @RequestParam(required = false, value = "number", defaultValue = "0") int page,
            @RequestParam(required = false, value = "amount", defaultValue = "5") int amount,
            @RequestBody Map<String, String> sort) throws IOException {
        logger.info("Good: pagination using hibernate");
        return service.page(page, amount, sort);
    }

    @RequestMapping(value = "/search",
            method = RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public List<Good> goodsSearch(@RequestParam(value = "name") String name) throws IOException {
        logger.info("Good: search by name using hibernate");
        return service.searchByName(name);
    }

    @RequestMapping(value = "/newest",
            method = RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public List<Good> getNewestGoods(@RequestParam(value = "size") int size) throws IOException {
        logger.info("Good: get newest using hibernate");
        return service.getNewest(size);
    }

}
