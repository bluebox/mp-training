package com.library.serviceInterface;

import java.sql.SQLException;
import java.util.List;

import com.library.domain.Member;

public interface MemberServiceInterface {

	 public abstract List<Member> fetchmembers() throws SQLException;

	    public abstract void addMember(Member member) throws Exception;

	    public abstract boolean updateMember(Member member) throws Exception;
}
