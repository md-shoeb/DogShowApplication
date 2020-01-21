package ca.sheridancollege;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.dao.DAO;
import ca.sheridancollege.dao.DAO2;
import ca.sheridancollege.dao.DAO3;
import ca.sheridancollege.dog.Dog;
import ca.sheridancollege.dog.Dog2;
import ca.sheridancollege.dog.Dog.Breed;
import ca.sheridancollege.dog.Dog.Dayxx;
import ca.sheridancollege.dog.Dog.DogRank;
import ca.sheridancollege.dog.Dog.Gender;

@Controller
public class HomeController {
	
	@Autowired
	EmailServiceImpl emailService;

	@GetMapping("/")
	public String myIndex() {
		return "index.html";
	}
	
	@GetMapping("/admin")
	public String myDog() {
		return "/users/home.html";
	}
	
	@GetMapping("/member")
	public String myDog2() {
		return "/users/home.html";
	}
	
	@GetMapping("/rock")
	public String myDogRock() {
		return "/users/home.html";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login.html";	
	}

	@GetMapping("/access-denied")
	public String error() {
		return "/error/access-denied.html";	
	}
	
	@GetMapping("/delete/{id}")
	public String deleteDog(Model model, @PathVariable int id) {
		DAO3.deleteDog(id);
		model.addAttribute("dogList", DAO.getDog());
		return "view.html";			
	}
	
	@GetMapping("/edit/{id}")
	public String editDog(@PathVariable int id, 
			Model model
			) {
		
		model.addAttribute("dogx", DAO3.getDogById(id));
		return "modify.html";			
	}
	
	
	@GetMapping("/register")
	public String goReg() {
		return "/users/register.html";	
	}
	
	public static String encryptPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
	
	@PostMapping("/registerx")
	public String doReg(@RequestParam String username, 
			@RequestParam String password, @RequestParam String rolex) {
		DAO2.addUser(username, encryptPassword(password));
		int userID = DAO2.findUserAccount(username).getId();
		//DAO.addUserRoles(userID, 1);
		int w=0;
		if(rolex.equals("ROLE_ADMIN"))
			w=1;
		else if(rolex.equals("ROLE_MEMBER"))
			w=2;
		
		DAO2.addUserRoles(userID, w);
		
		return "/users/register.html";	
	}

	@GetMapping("/find")
	public String findDog() {
		return "search.html";
	}

	@GetMapping("/add")
	public String addDog(@RequestParam String dog, @RequestParam String owner, @RequestParam String breed,
			@RequestParam String dogGroup, @RequestParam String gender, 
			@RequestParam String dogRank, @RequestParam String dayx, @RequestParam String email) {
		Dog d = new Dog(dog, owner, breed, dogGroup, gender, dogRank, dayx, email);
		
		String text = "Hi "+owner+",\n\n Thank you for registration to the dog show."
				+ " Below is the registration information for your reference - \n\n"+d.toString()+"\n\nThank you.";
		
		emailService.sendSimpleMessage(email, "Registration Successful", text);
		
		DAO.addDog(d);
		return "/users/home.html";
	}
	
	@GetMapping("/modify")
	public String updateDog(@RequestParam int id, @RequestParam String dog, @RequestParam String owner, @RequestParam String breed,
			@RequestParam String dogGroup, @RequestParam String gender, 
			@RequestParam String dogRank, Model model, @RequestParam String dayx, @RequestParam String email) {
		Dog d = new Dog(id, dog, owner, breed, dogGroup, gender, dogRank, dayx, email);
		DAO3.updateDog(d);
		model.addAttribute("dogList", DAO.getDog());
		return "view.html";	
	}

	@GetMapping("/view")
	public String viewDog(Model model) {
		model.addAttribute("dogList", DAO.getDog());
		return "view.html";
	}

	@GetMapping("/gen")
	public String generateDog(Model model) {

		for (int i = 0; i < 100; i++) {
			String dog = "Dog " + i;
			String owner = "Owner " + i;
			String email = "Email "+i;
			Breed breed = Breed.getRandomBreed();
			String b = "" + breed;
			String dogGroup;
			switch (breed) {

			case American_Bulldog:
				dogGroup = 1 + "";
				break;
			case Alaskan_Malamute:
				dogGroup = 1 + "";
				break;
			case Akbash:
				dogGroup = 1 + "";
				break;
			case American_Foxhound:
				dogGroup = 2 + "";
				break;
			case Australin_Shepherd:
				dogGroup = 2 + "";
				break;
			case Basenji:
				dogGroup = 2 + "";
				break;
			case Boxer:
				dogGroup = 3 + "";
				break;
			case Dalmatian:
				dogGroup = 3 + "";
				break;
			case Canadian_Eskimo_Dog:
				dogGroup = 3 + "";
				break;
			case English_Bulldog:
				dogGroup = 4 + "";
				break;
			case English_Foxhound:
				dogGroup = 4 + "";
				break;
			case French_Spaniel:
				dogGroup = 4 + "";
				break;
			case German_Shepherd:
				dogGroup = 5 + "";
				break;
			case Icelandic_Sheepdog:
				dogGroup = 5 + "";
				break;
			case Himalayan_Sheepdog:
				dogGroup = 5 + "";
				break;
			case Manchester_Terrier:
				dogGroup = 6 + "";
				break;
			case Pembrok_Welsh_Corgi:
				dogGroup = 6 + "";
				break;
			case Rafeiro_do_Alentejo:
				dogGroup = 6 + "";
				break;
			case Portuguese_Podengo:
				dogGroup = 7 + "";
				break;
			case Saint_Bernard:
				dogGroup = 7 + "";
				break;
			case Xiasi_Dog:
				dogGroup = 7 + "";
				break;
			default:
				dogGroup = 7 + "";

			}

			Gender gen = Gender.getGender();
			DogRank dogRank = DogRank.getDogRank();
			Dayxx dayxx = Dayxx.getDayxx();
			String gen2 = "" + gen;
			String dR = "" + dogRank;
			String dayx = "" + dayxx;

			Dog d = new Dog(dog, owner, b, dogGroup, gen2, dR, dayx, email);
			DAO.addDog(d);
		}
		return "/users/home.html";
	}

	@GetMapping("/search")
	public String searchDog(@RequestParam String breed, @RequestParam String s, Model model) {
		model.addAttribute("dogList", DAO.getDogByID(breed, s));
		return "view2.html";

	}

	@GetMapping("/schedule")
	public String scheduleDog(Model model) {
		String myDay = "Friday";
		ArrayList<Dog2> dogFinalList1 = new ArrayList<Dog2>();
		ArrayList<Dog2> dogFinalList2 = new ArrayList<Dog2>();
		ArrayList<Dog2> dogFinalList3 = new ArrayList<Dog2>();
		ArrayList<Dog2> dogFinalList4 = new ArrayList<Dog2>();
		ArrayList<Dog2> dogFinalList5 = new ArrayList<Dog2>();
		ArrayList<Dog2> dogFinalList6 = new ArrayList<Dog2>();
		ArrayList<Dog2> dogFinalList7 = new ArrayList<Dog2>();

		int breedCount = DAO3.getCount(myDay);
		String my[][] = new String[50][10];
		
		//making Count & Breed Name Array
		String[][] my2 = DAO3.getDog(myDay);
		
		//making array of rest count features
		for (int j = 0; j < breedCount; j++) {
			my [j]= DAO3.getInfo(my2[j][1], myDay);
			int catchx = DAO3.breedCountForOne(my2[j][1], myDay);
		}
		
		int global_bgc=0;
		//Group 1
		int bgc=0;
		String c="1";
		for (int i = 0; i < breedCount; i++) {
			if(my[i][1].equals(c)) {
				bgc++;
			}		
		}
		
		for (int i = 0; i < bgc; i++) {
			Dog2 dx = new Dog2(my2[i][0],my2[i][1],my[i][2],my[i][3],my[i][4],my[i][5]);
			dogFinalList1.add(dx);
		}
		global_bgc+=bgc;
		
		//Group 2
				bgc=0;
				c="2";
				for (int i = 0; i < breedCount; i++) {
					if(my[i][1].equals(c)) {
						bgc++;
					}		
				}
	
				for (int i = 0; i < bgc; i++) {
					Dog2 dx = new Dog2(my2[i+global_bgc][0],my2[i+global_bgc][1],my[i+global_bgc][2],my[i+global_bgc][3],my[i+global_bgc][4],my[i+global_bgc][5]);
					dogFinalList2.add(dx);
				}
				global_bgc+=bgc;
				
				
				//Group 3
				bgc=0;
				c="3";
				for (int i = 0; i < breedCount; i++) {
					if(my[i][1].equals(c)) {
						bgc++;
					}		
				}
				
				for (int i = 0; i < bgc; i++) {
					Dog2 dx = new Dog2(my2[i+global_bgc][0],my2[i+global_bgc][1],my[i+global_bgc][2],my[i+global_bgc][3],my[i+global_bgc][4],my[i+global_bgc][5]);
					dogFinalList3.add(dx);
				}
				global_bgc+=bgc;
				
				//Group 4
				bgc=0;
				c="4";
				for (int i = 0; i < breedCount; i++) {
					if(my[i][1].equals(c)) {
						bgc++;
					}		
				}
				
				for (int i = 0; i < bgc; i++) {
					Dog2 dx = new Dog2(my2[i+global_bgc][0],my2[i+global_bgc][1],my[i+global_bgc][2],my[i+global_bgc][3],my[i+global_bgc][4],my[i+global_bgc][5]);
					dogFinalList4.add(dx);
				}
				global_bgc+=bgc;
				
				//Group 5
				bgc=0;
				c="5";
				for (int i = 0; i < breedCount; i++) {
					if(my[i][1].equals(c)) {
						bgc++;
					}		
				}
				
				for (int i = 0; i < bgc; i++) {
					Dog2 dx = new Dog2(my2[i+global_bgc][0],my2[i+global_bgc][1],my[i+global_bgc][2],my[i+global_bgc][3],my[i+global_bgc][4],my[i+global_bgc][5]);
					dogFinalList5.add(dx);
				}
				global_bgc+=bgc;
				
				//Group 6
				bgc=0;
				c="6";
				for (int i = 0; i < breedCount; i++) {
					if(my[i][1].equals(c)) {
						bgc++;
					}		
				}
				
				for (int i = 0; i < bgc; i++) {
					Dog2 dx = new Dog2(my2[i+global_bgc][0],my2[i+global_bgc][1],my[i+global_bgc][2],my[i+global_bgc][3],my[i+global_bgc][4],my[i+global_bgc][5]);
					dogFinalList6.add(dx);
				}
				global_bgc+=bgc;
				
				//Group 7
				bgc=0;
				c="7";
				for (int i = 0; i < breedCount; i++) {
					if(my[i][1].equals(c)) {
						bgc++;
					}		
				}
				
				for (int i = 0; i < bgc; i++) {
					Dog2 dx = new Dog2(my2[i+global_bgc][0],my2[i+global_bgc][1],my[i+global_bgc][2],my[i+global_bgc][3],my[i+global_bgc][4],my[i+global_bgc][5]);
					dogFinalList7.add(dx);
				}
				global_bgc+=bgc;		
		
		 model.addAttribute("dogList1", dogFinalList1); 
		 model.addAttribute("dogList2", dogFinalList2);
		 model.addAttribute("dogList3", dogFinalList3);
		 model.addAttribute("dogList4", dogFinalList4); 
		 model.addAttribute("dogList5", dogFinalList5); 
		 model.addAttribute("dogList6", dogFinalList6);
		 model.addAttribute("dogList7", dogFinalList7);
		 model.addAttribute("day1", "Friday");
		 
		 //Saturday 
		 
		  	myDay = "Saturday";
			ArrayList<Dog2> dogFinalList1a = new ArrayList<Dog2>();
			ArrayList<Dog2> dogFinalList2a = new ArrayList<Dog2>();
			ArrayList<Dog2> dogFinalList3a = new ArrayList<Dog2>();
			ArrayList<Dog2> dogFinalList4a = new ArrayList<Dog2>();
			ArrayList<Dog2> dogFinalList5a = new ArrayList<Dog2>();
			ArrayList<Dog2> dogFinalList6a = new ArrayList<Dog2>();
			ArrayList<Dog2> dogFinalList7a = new ArrayList<Dog2>();

			breedCount = DAO3.getCount(myDay);
			
			//making Count & Breed Name Array
			my2 = DAO3.getDog(myDay);
			
			//making array of rest count features
			for (int j = 0; j < breedCount; j++) {
				my [j]= DAO3.getInfo(my2[j][1], myDay);
				int catchx = DAO3.breedCountForOne(my2[j][1], myDay);
			}
			
			global_bgc=0;
			//Group 1
			bgc=0;
			c="1";
			for (int i = 0; i < breedCount; i++) {
				if(my[i][1].equals(c)) {
					bgc++;
				}		
			}
			
			for (int i = 0; i < bgc; i++) {
				Dog2 dx = new Dog2(my2[i][0],my2[i][1],my[i][2],my[i][3],my[i][4],my[i][5]);
				dogFinalList1a.add(dx);
			}
			global_bgc+=bgc;
			
			//Group 2
					bgc=0;
					c="2";
					for (int i = 0; i < breedCount; i++) {
						if(my[i][1].equals(c)) {
							bgc++;
						}		
					}
		
					for (int i = 0; i < bgc; i++) {
						Dog2 dx = new Dog2(my2[i+global_bgc][0],my2[i+global_bgc][1],my[i+global_bgc][2],my[i+global_bgc][3],my[i+global_bgc][4],my[i+global_bgc][5]);
						dogFinalList2a.add(dx);
					}
					global_bgc+=bgc;
					
					
					//Group 3
					bgc=0;
					c="3";
					for (int i = 0; i < breedCount; i++) {
						if(my[i][1].equals(c)) {
							bgc++;
						}		
					}
					
					for (int i = 0; i < bgc; i++) {
						Dog2 dx = new Dog2(my2[i+global_bgc][0],my2[i+global_bgc][1],my[i+global_bgc][2],my[i+global_bgc][3],my[i+global_bgc][4],my[i+global_bgc][5]);
						dogFinalList3a.add(dx);
					}
					global_bgc+=bgc;
					
					//Group 4
					bgc=0;
					c="4";
					for (int i = 0; i < breedCount; i++) {
						if(my[i][1].equals(c)) {
							bgc++;
						}		
					}
					
					for (int i = 0; i < bgc; i++) {
						Dog2 dx = new Dog2(my2[i+global_bgc][0],my2[i+global_bgc][1],my[i+global_bgc][2],my[i+global_bgc][3],my[i+global_bgc][4],my[i+global_bgc][5]);
						dogFinalList4a.add(dx);
					}
					global_bgc+=bgc;
					
					//Group 5
					bgc=0;
					c="5";
					for (int i = 0; i < breedCount; i++) {
						if(my[i][1].equals(c)) {
							bgc++;
						}		
					}
					
					for (int i = 0; i < bgc; i++) {
						Dog2 dx = new Dog2(my2[i+global_bgc][0],my2[i+global_bgc][1],my[i+global_bgc][2],my[i+global_bgc][3],my[i+global_bgc][4],my[i+global_bgc][5]);
						dogFinalList5a.add(dx);
					}
					global_bgc+=bgc;
					
					//Group 6
					bgc=0;
					c="6";
					for (int i = 0; i < breedCount; i++) {
						if(my[i][1].equals(c)) {
							bgc++;
						}		
					}
					
					for (int i = 0; i < bgc; i++) {
						Dog2 dx = new Dog2(my2[i+global_bgc][0],my2[i+global_bgc][1],my[i+global_bgc][2],my[i+global_bgc][3],my[i+global_bgc][4],my[i+global_bgc][5]);
						dogFinalList6a.add(dx);
					}
					global_bgc+=bgc;
					
					//Group 7
					bgc=0;
					c="7";
					for (int i = 0; i < breedCount; i++) {
						if(my[i][1].equals(c)) {
							bgc++;
						}		
					}
					
					for (int i = 0; i < bgc; i++) {
						Dog2 dx = new Dog2(my2[i+global_bgc][0],my2[i+global_bgc][1],my[i+global_bgc][2],my[i+global_bgc][3],my[i+global_bgc][4],my[i+global_bgc][5]);
						dogFinalList7a.add(dx);
					}
					global_bgc+=bgc;		
			
			 model.addAttribute("dogList1a", dogFinalList1a); 
			 model.addAttribute("dogList2a", dogFinalList2a);
			 model.addAttribute("dogList3a", dogFinalList3a);
			 model.addAttribute("dogList4a", dogFinalList4a); 
			 model.addAttribute("dogList5a", dogFinalList5a); 
			 model.addAttribute("dogList6a", dogFinalList6a);
			 model.addAttribute("dogList7a", dogFinalList7a);
			 model.addAttribute("day2", "Saturday");
			 
			 //Sunday
			 
			  	myDay = "Sunday";
				ArrayList<Dog2> dogFinalList1b = new ArrayList<Dog2>();
				ArrayList<Dog2> dogFinalList2b = new ArrayList<Dog2>();
				ArrayList<Dog2> dogFinalList3b = new ArrayList<Dog2>();
				ArrayList<Dog2> dogFinalList4b = new ArrayList<Dog2>();
				ArrayList<Dog2> dogFinalList5b = new ArrayList<Dog2>();
				ArrayList<Dog2> dogFinalList6b = new ArrayList<Dog2>();
				ArrayList<Dog2> dogFinalList7b = new ArrayList<Dog2>();

				breedCount = DAO3.getCount(myDay);
				
				//making Count & Breed Name Array
				my2 = DAO3.getDog(myDay);
				
				//making array of rest count features
				for (int j = 0; j < breedCount; j++) {
					my [j]= DAO3.getInfo(my2[j][1], myDay);
					int catchx = DAO3.breedCountForOne(my2[j][1], myDay);
				}
				
				global_bgc=0;
				//Group 1
				bgc=0;
				c="1";
				for (int i = 0; i < breedCount; i++) {
					if(my[i][1].equals(c)) {
						bgc++;
					}		
				}
				
				for (int i = 0; i < bgc; i++) {
					Dog2 dx = new Dog2(my2[i][0],my2[i][1],my[i][2],my[i][3],my[i][4],my[i][5]);
					dogFinalList1b.add(dx);
				}
				global_bgc+=bgc;
				
				//Group 2
						bgc=0;
						c="2";
						for (int i = 0; i < breedCount; i++) {
							if(my[i][1].equals(c)) {
								bgc++;
							}		
						}
			
						for (int i = 0; i < bgc; i++) {
							Dog2 dx = new Dog2(my2[i+global_bgc][0],my2[i+global_bgc][1],my[i+global_bgc][2],my[i+global_bgc][3],my[i+global_bgc][4],my[i+global_bgc][5]);
							dogFinalList2b.add(dx);
						}
						global_bgc+=bgc;
						
						
						//Group 3
						bgc=0;
						c="3";
						for (int i = 0; i < breedCount; i++) {
							if(my[i][1].equals(c)) {
								bgc++;
							}		
						}
						
						for (int i = 0; i < bgc; i++) {
							Dog2 dx = new Dog2(my2[i+global_bgc][0],my2[i+global_bgc][1],my[i+global_bgc][2],my[i+global_bgc][3],my[i+global_bgc][4],my[i+global_bgc][5]);
							dogFinalList3b.add(dx);
						}
						global_bgc+=bgc;
						
						//Group 4
						bgc=0;
						c="4";
						for (int i = 0; i < breedCount; i++) {
							if(my[i][1].equals(c)) {
								bgc++;
							}		
						}
						
						for (int i = 0; i < bgc; i++) {
							Dog2 dx = new Dog2(my2[i+global_bgc][0],my2[i+global_bgc][1],my[i+global_bgc][2],my[i+global_bgc][3],my[i+global_bgc][4],my[i+global_bgc][5]);
							dogFinalList4b.add(dx);
						}
						global_bgc+=bgc;
						
						//Group 5
						bgc=0;
						c="5";
						for (int i = 0; i < breedCount; i++) {
							if(my[i][1].equals(c)) {
								bgc++;
							}		
						}
						
						for (int i = 0; i < bgc; i++) {
							Dog2 dx = new Dog2(my2[i+global_bgc][0],my2[i+global_bgc][1],my[i+global_bgc][2],my[i+global_bgc][3],my[i+global_bgc][4],my[i+global_bgc][5]);
							dogFinalList5b.add(dx);
						}
						global_bgc+=bgc;
						
						//Group 6
						bgc=0;
						c="6";
						for (int i = 0; i < breedCount; i++) {
							if(my[i][1].equals(c)) {
								bgc++;
							}		
						}
						
						for (int i = 0; i < bgc; i++) {
							Dog2 dx = new Dog2(my2[i+global_bgc][0],my2[i+global_bgc][1],my[i+global_bgc][2],my[i+global_bgc][3],my[i+global_bgc][4],my[i+global_bgc][5]);
							dogFinalList6b.add(dx);
						}
						global_bgc+=bgc;
						
						//Group 7
						bgc=0;
						c="7";
						for (int i = 0; i < breedCount; i++) {
							if(my[i][1].equals(c)) {
								bgc++;
							}		
						}
						
						for (int i = 0; i < bgc; i++) {
							Dog2 dx = new Dog2(my2[i+global_bgc][0],my2[i+global_bgc][1],my[i+global_bgc][2],my[i+global_bgc][3],my[i+global_bgc][4],my[i+global_bgc][5]);
							dogFinalList7b.add(dx);
						}
						global_bgc+=bgc;		
				
				 model.addAttribute("dogList1b", dogFinalList1b); 
				 model.addAttribute("dogList2b", dogFinalList2b);
				 model.addAttribute("dogList3b", dogFinalList3b);
				 model.addAttribute("dogList4b", dogFinalList4b); 
				 model.addAttribute("dogList5b", dogFinalList5b); 
				 model.addAttribute("dogList6b", dogFinalList6b);
				 model.addAttribute("dogList7b", dogFinalList7b);
				 model.addAttribute("day3", "Sunday");
		 
		 return "schedule.html";
	}
}
