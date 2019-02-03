package greendatatest.dao;

import greendatatest.entity.Bank;
import org.springframework.data.repository.CrudRepository;

public interface BankDao extends CrudRepository<Bank, Integer> {

    Iterable<Bank> findByNameContaining(String name);

}
