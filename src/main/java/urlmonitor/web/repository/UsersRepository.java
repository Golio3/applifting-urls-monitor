package urlmonitor.web.repository;

import urlmonitor.web.model.UsersModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<UsersModel, Integer> {

    List<UsersModel> findAll();

    UsersModel findByUsername(String username);

    List<UsersModel> findByRoles(String roles);
}
