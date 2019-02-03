package greendatatest.dao;

import greendatatest.entity.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ClientDao extends CrudRepository<Client, Integer> {

    @Query("SELECT c " +
            "FROM  Client c " +
            "WHERE c.name LIKE CONCAT('%', :name, '%') " +
            "AND   c.shortName LIKE CONCAT('%', :shortName, '%') " +
            "AND   c.address LIKE CONCAT('%', :address, '%') " +
            "AND   (c.legalForm.id = :legalFormId OR :legalFormId = null)")
    Iterable<Client> findAllWithFilters(
            @Param("name") String name,
            @Param("shortName") String shortName,
            @Param("address") String address,
            @Param("legalFormId") Integer legalFormId
    );

}
