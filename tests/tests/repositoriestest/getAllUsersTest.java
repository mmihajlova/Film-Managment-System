package tests.repositoriestest;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import BusinessLayer.User;
import DataAccessLayer.UserRepository;

class getAllUsersTest {

	@Test
	void getAllUsersTests() {
		UserRepository test= new UserRepository();
		List<User>tests=test.getAllUsers();
		assertNotNull(tests);
		
	}

	
}