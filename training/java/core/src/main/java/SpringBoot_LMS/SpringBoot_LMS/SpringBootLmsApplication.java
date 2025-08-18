package SpringBoot_LMS.SpringBoot_LMS;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import model.Gender;
import model.Member;
import repository.MemberRepository;

@SpringBootApplication
@ComponentScan(basePackages = {"controller", "repository", "model"})
//@ComponentScan({"controller.MemberController", "repository.MemberRepository", "model.Member"})
public class SpringBootLmsApplication  implements CommandLineRunner{
//    private MemberRepository memberRepository;
	public static void main(String[] args) {
		SpringApplication.run(SpringBootLmsApplication.class, args);
	}

//	@Override
	public void run(String... args) throws Exception {
////		// TODO Auto-generated method stub
////		Member mem = new Member (2,"kavithaa","jhjhj","9987675658",Gender.FEMALE,"yttytg");
////		int 
//	}
	}}
