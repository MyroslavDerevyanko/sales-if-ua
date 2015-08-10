package sales.orders.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sales.orders.domain.Order;
import sales.orders.repository.OrderRepository;
import sales.storage.domain.Storage;

import java.util.Date;
import java.util.List;

/**
 * Created by Volodya on 23.07.2015.
 */

@Service("ordersService")
@Transactional

public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrderRepository repository;


    @Override
    public Order get(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Order> getAll() {
        return repository.findAll();
    }

    @Override
    public Order save(Order order) {
        return repository.save(order);
    }

    @Override
    public void delete(long id) {
        repository.removeById(id);
    }

    @Override
    public List<Order> getByStorages(List<Storage> storages) {
        return repository.StorageIn(storages);
    }

    @Override
    public List<Order> getByDateAfter(Date date) {
        return  repository.findByDateAfter(date);
    }
}
