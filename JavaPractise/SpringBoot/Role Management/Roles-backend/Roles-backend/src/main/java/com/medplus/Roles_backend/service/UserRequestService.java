package com.medplus.Roles_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.medplus.Roles_backend.dao.UserDaoInterface;
import com.medplus.Roles_backend.dao.UserrequestDaoInterface;
import com.medplus.Roles_backend.domain.ActiveMembers;
import com.medplus.Roles_backend.domain.User;
import com.medplus.Roles_backend.domain.UserRequest;
import com.medplus.Roles_backend.enums.ActiveStatus;
import com.medplus.Roles_backend.enums.ApprovalStatus;
import com.medplus.Roles_backend.exception.UserValidationException;
import com.medplus.Roles_backend.util.UserValidator;

import java.util.List;

@Service
public class UserRequestService implements UserRequestServiceInterface{

	@Autowired
	private UserrequestDaoInterface userRequestDao;
	@Autowired
	private UserDaoInterface userDao;

	@Autowired
	private TwilioService twilioService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public void sendRequest(UserRequest request) {
		if (request == null) {
			throw new UserValidationException("Request object cannot be null");
		}

		UserValidator.validateFirstName(request.getFirstName());
		UserValidator.validateLastName(request.getLastName());
		UserValidator.validateUsername(request.getUsername());
		UserValidator.validateAge(request.getAge());
		UserValidator.validateEmail(request.getEmail());
		UserValidator.validateMobile(request.getMobile());
		UserValidator.validateState(request.getState());
		UserValidator.validateCity(request.getCity());
		UserValidator.validateGender(request.getGender());
		request.setCreatedBy("Admin");
		userRequestDao.save(request);
	}

	public List<UserRequest> getAllRequests() {
		return userRequestDao.getAllRequests();
	}

	public List<ActiveMembers> getAllActiveUsers() {
		return userRequestDao.findAllActiveUsers();
	}

	public void acceptRequest(Long reqId) {
		UserValidator.validateReqId(reqId);

		UserRequest req = userRequestDao.findById(reqId);
		if (req == null) {
			throw new UserValidationException("Request not found for id: " + reqId);
		}

		userRequestDao.insertIntoMainTable(req, passwordEncoder.encode(req.getReqId().toString()));
		userRequestDao.updateApprovalStatus(reqId, ApprovalStatus.APPROVED);
		User user=userDao.findByUsername(req.getUsername());

		String mobile = req.getMobile();
		if (!mobile.startsWith("+"))
			mobile = "+91" + mobile;
		String message = "Hi " + req.getFirstName() + ", your request has been accepted successfully!"+"userId : +"+user.getId()+"password:"+reqId;
		twilioService.sendSms(mobile, message);
	}

	public void rejectRequest(Long reqId) {
		UserValidator.validateReqId(reqId);
		userRequestDao.updateApprovalStatus(reqId, ApprovalStatus.REJECTED);
	}

	public void activeRequest(Long reqId) {
		UserValidator.validateReqId(reqId);
		userRequestDao.updateActiveStatus(reqId, ActiveStatus.ACTIVE);
	}

	public void inactiveRequest(Long reqId) {
		UserValidator.validateReqId(reqId);
		userRequestDao.updateActiveStatus(reqId, ActiveStatus.INACTIVE);
	}
}
