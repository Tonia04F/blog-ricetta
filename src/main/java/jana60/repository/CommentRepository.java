package jana60.repository;

import org.springframework.data.repository.CrudRepository;

import jana60.model.Comment;


public interface CommentRepository extends CrudRepository<Comment, Integer>  {
	
}
