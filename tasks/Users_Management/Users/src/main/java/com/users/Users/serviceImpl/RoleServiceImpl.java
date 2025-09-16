package com.users.Users.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.users.Users.model.Role;
import com.users.Users.model.UserRole;
import com.users.Users.repository.interfaces.RoleRepository;
import com.users.Users.service.interfaces.RoleService;
import com.users.Users.validate.Validate;

@Service
public class RoleServiceImpl implements RoleService{
	
	private RoleRepository roleRepository;
	
	private Validate validate = new Validate();

	
	@Autowired
	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository=roleRepository;
	}
	
	public  List<Role> getRoles() {
		return  roleRepository.getRoles();
	}

	public void addUserRole(UserRole userRole) throws Exception {
		
		validate.validateUserRole(userRole);
		
		try {
			roleRepository.addUserRole(userRole);
		} catch (Exception e) {
			throw new Exception("The User is assigned with the selected roles in selected locations");
		} 
	}

	public List<UserRole> getUserAssignedRoles(String usercode) throws Exception {
		
		if(usercode == "" || usercode.isBlank()) {
			throw new Exception("usercode cannot not be empty");
		}
		
		List<UserRole> userRoles =  roleRepository.getUserAssignedRoles(usercode);
		
		if(userRoles!=null) {
			return userRoles;
		}else {
			throw new Exception("No roles assigned to the user: "+usercode);
		}
		
	}

	@Override
	public List<String>  getRoleName(String usercode) throws Exception {
		
		if(usercode == "" || usercode.isBlank()) {
			throw new Exception("usercode cannot not be empty");
		}
		
		return roleRepository.getRoleName(usercode);
	}
	
	@Override
    public boolean changeRoleStatus(String roleCode) throws Exception {
		
		if(roleCode == "" || roleCode.isBlank()) {
			throw new Exception("roleCode cannot not be empty");
		}
		
       boolean res = roleRepository.changeRoleStatus(roleCode);
       if(res) {
    	   return res;
       }else {
			throw new Exception("Failed to update role status for role : "+roleCode);
       }
    }

	  @Override
	  public boolean changeUserRoleStatus(UserRole userRole) throws Exception {
		 
		 validate.validateUserRole(userRole);
		  
	  	 boolean res = roleRepository.changeUserRoleStatus(userRole);

	       if(res) {
	    	   return res;
	       }else {
				throw new Exception("Failed to update user role status for user : "+userRole.getUsercode());
	       }
	  }
	

}
