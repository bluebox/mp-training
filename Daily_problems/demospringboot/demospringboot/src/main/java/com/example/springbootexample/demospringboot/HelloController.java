package com.example.springbootexample.demospringboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class HelloController {
	
	@Autowired
	private Product product;
	@Autowired
	private Person person;
	
	@Autowired
	private Business business;
    //@RequestMapping(value={"", "/", "home"},method=RequestMethod.GET)
	@GetMapping(value= {"","/","Home"})
	public String hello(@RequestParam(required=false,defaultValue="Rajini") String name,Model model) {
		
		product.setPrice(100.00);
		product.setProductname("Shirts");
//		business.setBusinessname("imports");
//		business.setBusinessshop("");
//		business.setBusinessstatergy("comprehensive");
//		business.setEmail("ofajfai");
//		business.setPhoneno(998);
//		business.setBusinessid(9);
//		System.out.println("bata business");
//		System.out.println("this is business.."+business);

		model.addAttribute("username", "Vijay Devarakonda");
		model.addAttribute("items", List.of("rakesh","ramesh","suresh","naresh","paramesh","sukesh"));
		model.addAttribute("isgod","true");
		model.addAttribute("productname",product.getProductname());
		model.addAttribute("price",product.getPrice());
		model.addAttribute("businessname",business.getBusinessname());
		model.addAttribute("phoneno",business.getPhoneno());
		model.addAttribute("email",business.getEmail());
		model.addAttribute("name",name);
		log.info("hi,hello this is from log");
		log.debug("debug info value={}",123);
//		model.addAttribute("businessname",business.getBusinessname());
//		model.addAttribute("phoneno",business.getPhoneno());
//		model.addAttribute("email",business.getEmail());
		
		
		
		
		return "Home";
		
	}
	@GetMapping(value={"/index"})
	public String registerproduct(Model model) {
		
		

		model.addAttribute("product", new Product());
		model.addAttribute("issale",false);
		return "index";
		
	}
	@GetMapping(value={"/business"})
	public String registerbusiness(Model model) {
		
		

		model.addAttribute("business",new Business());
		return "business";
		
	}
	@GetMapping(value={"/index/{sale}"})
	public String registerproduct(Model model,@PathVariable String sale) {
		
		
		model.addAttribute("product", new Product());
		System.out.println("the sales id is "+sale);
		model.addAttribute("sale",sale);
		model.addAttribute("issale",true);
		return "index";
		
	}
	@PostMapping("/formproduct")
	public String registerproductpost(@ModelAttribute("product") Product product,Model model) {
		System.out.println(product.toString());
		return "index";
	}
	@PostMapping("/formbusiness")
	public String registerbusinesspost(@Valid @ModelAttribute("business") Business business) {
		System.out.println(business.toString());
		return "business";
	}
	
	
	@GetMapping(value={"/Home/person"})
	public String getperson(HttpServletRequest request,@RequestParam(required=false) String name,Model model) {
		
		if(name!=null && !name.equals(""))
			person.setName(name);
		Person p1=person;
		HttpSession session=request.getSession(false);
		if(session!=null) {
			session.invalidate();
			}
		model.addAttribute("name",person.getName());
		Person p2=person;
		System.out.println(p1.hashCode()+" "+p2.hashCode());
		if(p1.hashCode()!=p2.hashCode()) {
			System.out.println("person p1 not equal to p2");
		}
		return "person";
		
	}
	
	
}
