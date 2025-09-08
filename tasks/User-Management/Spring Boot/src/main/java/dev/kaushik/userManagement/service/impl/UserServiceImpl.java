package dev.kaushik.userManagement.service.impl;

import dev.kaushik.userManagement.dao.MainUserDao;
import dev.kaushik.userManagement.dao.UserRequestDao;
import dev.kaushik.userManagement.exception.UserException;
import dev.kaushik.userManagement.model.MainUser;
import dev.kaushik.userManagement.model.UserRequest;
import dev.kaushik.userManagement.model.enums.Approval;
import dev.kaushik.userManagement.model.enums.Status;
import dev.kaushik.userManagement.service.UserService;
import dev.kaushik.userManagement.validator.UserValidator;
import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	private final UserRequestDao userRequestDao;
	private final MainUserDao mainUserDao;
	private final PasswordEncoder passwordEncoder;

	@Value("${twilio.account-sid}")
	private String accountSid;

	@Value("${twilio.auth-token}")
	private String authToken;

	@Value("${twilio.phone-number}")
	private String twilioPhoneNumber;

	@Value("${twilio.to-number}")
	private String toPhoneNumber;

	@Autowired
	public UserServiceImpl(UserRequestDao userRequestDao, MainUserDao mainUserDao, PasswordEncoder passwordEncoder) {
		this.userRequestDao = userRequestDao;
		this.mainUserDao = mainUserDao;
		this.passwordEncoder = passwordEncoder;
	}

	@PostConstruct
	private void initTwilio() {
		Twilio.init(accountSid, authToken);
	}

	@Override
	public int createUser(UserRequest user) {
		UserValidator.validate(user);
		return userRequestDao.createUser(user);
	}

	@Override
	public boolean approveUser(int requestId, String userName, String password) {
		UserRequest req = getUserRequests().stream().filter(r -> r.getRequestId() == requestId).findFirst()
				.orElseThrow(() -> new UserException("User request not found with requestId: " + requestId));

		MainUser mainUser = new MainUser();
		mainUser.setUserName(userName);
		mainUser.setPassword(password);
		mainUser.setFirstName(req.getFirstName());
		mainUser.setLastName(req.getLastName());
		mainUser.setEmail(req.getEmail());
		mainUser.setPhoneNumber(req.getPhoneNumber());
		mainUser.setGender(req.getGender());
		mainUser.setCountry(req.getCountry());
		mainUser.setState(req.getState());
		mainUser.setCity(req.getCity());
		mainUser.setPinCode(req.getPinCode());

		boolean userAdded = mainUserDao.addMainuser(mainUser);

		if (userAdded) {
			sendSmsNotification(mainUser);
			req.setApproval(Approval.APPROVED);
			return userRequestDao.updateRequest(req);
		}

		return false;
	}

	private void sendSmsNotification(MainUser mainUser) {
		String messageBody = String.format("Welcome %s %s! username is: %s & password is: %s", mainUser.getFirstName(),
				mainUser.getLastName(), mainUser.getUserName(), mainUser.getPassword());

		Message message = Message
				.creator(new PhoneNumber(toPhoneNumber), new PhoneNumber(twilioPhoneNumber), messageBody).create();

		System.out.println("SMS sent with SID: " + message.getSid());
	}

	private void sendSmsNotification(String messageString) {

		Message message = Message
				.creator(new PhoneNumber(toPhoneNumber), new PhoneNumber(twilioPhoneNumber), messageString).create();

		System.out.println("SMS sent with SID: " + message.getSid());
	}

	@Override
	public List<MainUser> getMainUsers() {
		return mainUserDao.getAllMainUsers().stream().filter(u -> !u.getUserName().equals("admin")).toList();
	}

	@Override
	public List<UserRequest> getUserRequests() {
		return userRequestDao.getAllRequests();
	}

	@Override
	public boolean rejectUser(int requestId) {
		if (requestId <= 0) {
			throw new UserException("Request Id must be positive");
		}
		UserRequest req = getUserRequests().stream().filter(r -> r.getRequestId() == requestId).findFirst()
				.orElseThrow(() -> new UserException("User request not found with requestId: " + requestId));

		req.setApproval(Approval.REJECTED);
		return userRequestDao.updateRequest(req);
	}

	@Override
	public boolean changeStatus(String userName) {
		MainUser user = getMainUsers().stream().filter(u -> u.getUserName().equals(userName)).findFirst()
				.orElseThrow(() -> new UserException("user not found with username: " + userName));
		Status newStatus = user.getStatus() == Status.ACTIVE ? Status.INACTIVE : Status.ACTIVE;
		user.setStatus(newStatus);
		return mainUserDao.updateMainUser(user);
	}

	@Override
	public MainUser verifyMainUser(String userName, String password) {
		List<MainUser> mainUsers = getMainUsers();
		MainUser foundUser = mainUsers.stream().filter(u -> u.getUserName().equals(userName)).findFirst()
				.orElseThrow(() -> new UserException("user not found with username: " + userName));
		if (passwordEncoder.matches(password, foundUser.getPassword())) {
			return foundUser;
		} else {
			throw new UserException("Incorrect password");
		}
	}

	@Override
	public boolean updateUser(MainUser mainUser) {
		UserValidator.validate(mainUser);
		MainUser oldUser = getMainUsers().stream().filter(u -> u.getUserName().equals(mainUser.getUserName()))
				.findFirst()
				.orElseThrow(() -> new UserException("user not found with username: " + mainUser.getUserName()));
		if (mainUser.getPassword() != null) {
			if (passwordEncoder.matches(mainUser.getPassword(), oldUser.getPassword())) {
				throw new UserException("New password is same as existing password");
			}
			return mainUserDao.updateMainUserWithPassword(mainUser); 
		}
		if (oldUser.equals(mainUser)) {
			throw new UserException("No changes found");
		}
		return mainUserDao.updateMainUser(mainUser);
	}
}