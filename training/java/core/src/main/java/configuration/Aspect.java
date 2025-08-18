//package configuration;
//
//
//	import java.lang.reflect.Parameter;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//@Aspect
//@Component
// 
//public class Aspect {
//
//	    @Before("execution(* controller.*Controller.*(..))")
//	    public void validateAnnotatedParams(JoinPoint joinPoint) throws Throwable {
//	        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//	        Object[] args = joinPoint.getArgs();
//	        Parameter[] parameters = signature.getMethod().getParameters();
//
//	        for (int i = 0; i < parameters.length; i++) {
//	            Object arg = args[i];
//	            if (arg == null) continue;
//
//	            if (parameters[i].isAnnotationPresent(ValidateMember.class) && arg instanceof model.Member) {
//	                validateMember((model.Member) arg);
//	            }
//
//	            if (parameters[i].isAnnotationPresent(ValidateBook.class) && arg instanceof model.Book) {
//	                validateBook((model.Book) arg);
//	            }
//
//	            if (parameters[i].isAnnotationPresent(ValidateIssueRecord.class) && arg instanceof model.Issuerecords) {
//	                validateIssueRecords((model.Issuerecords) arg);
//	            }
//	        }
//	    }
//
//	    private void validateMember(model.Member member) {
//	        if (member.getName() == null || member.getName().isEmpty()) {
//	            throw new IllegalArgumentException("Member name cannot be empty");
//	        }
//	        if (member.getEmail() == null || !member.getEmail().contains("@")) {
//	            throw new IllegalArgumentException("Invalid email for member");
//	        }
//	    }
//
//	    private void validateBook(model.Book book) {
//	        if (book.getTitle() == null || book.getTitle().isEmpty()) {
//	            throw new IllegalArgumentException("Book title cannot be empty");
//	        }
//	        if (book.getAuthor() == null || book.getAuthor().isEmpty()) {
//	            throw new IllegalArgumentException("Book author cannot be empty");
//	        }
//	    }
//
//	    private void validateIssueRecords(model.Issuerecords record) {
//	        if (record.getBookId() == null) {
//	            throw new IllegalArgumentException("Book ID cannot be null in issue record");
//	        }
//	        if (record.getMemberId() == null) {
//	            throw new IllegalArgumentException("Member ID cannot be null in issue record");
//	        }
//	    }
//	}
//

