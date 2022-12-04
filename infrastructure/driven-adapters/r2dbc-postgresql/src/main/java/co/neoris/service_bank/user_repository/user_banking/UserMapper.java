package co.neoris.service_bank.user_repository.user_banking;

import co.neoris.service_bank.model.person.Person;
import co.neoris.service_bank.model.user.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public UserBankingDAO toDAO(User user, Person person) {
        return new UserBankingDAO(user.getUserId(), user.getPassword(), user.getState(), person.getPersonId());
    }

    public User toModel(UserBankingDAO userBankingDAO) {
        User newUser = new User();
        newUser.setUserId(userBankingDAO.getId());
        newUser.setPassword(userBankingDAO.getUserPassword());
        newUser.setState(userBankingDAO.getState());

        return newUser;
    }
}
