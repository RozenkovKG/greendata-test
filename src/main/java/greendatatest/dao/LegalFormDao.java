package greendatatest.dao;

import greendatatest.entity.LegalForm;
import org.springframework.data.repository.CrudRepository;

public interface LegalFormDao extends CrudRepository<LegalForm, Integer> {
}
