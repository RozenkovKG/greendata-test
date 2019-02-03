package greendatatest.dao;

import greendatatest.entity.Deposit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DepositDao extends CrudRepository<Deposit, Integer> {

    @Query("SELECT d " +
            "FROM  Deposit d " +
            "WHERE (d.client.id = :clientId OR :clientId = null) " +
            "AND   (d.bank.id = :bankId OR :bankId = null)")
    Iterable<Deposit> findAllWithFilters(
            @Param("clientId") Integer clientId,
            @Param("bankId") Integer bankId
    );

}
