package jana60.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import jana60.model.Categories;

@Repository
public interface CategoriesRepository extends CrudRepository<Categories, Integer>{

}
