package ca.sheridancollege.dog;

import java.util.Random;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Dog {
	private int id;
	private String dog;
	private String owner;
	private String breed;
	private String dogGroup;
	private String gender;
	private String dogRank;
	private String dayx;
	private String email;
	
	public Dog(String dog, String owner, String breed, String dogGroup, String gender, String dogRank, String dayx, String email) {
		this.dog=dog;
		this.owner=owner;
		this.breed=breed;
		this.dogGroup=dogGroup;
		this.gender=gender;
		this.dogRank=dogRank;
		this.dayx=dayx;
		this.email = email;
	}
	
	public enum Breed {
        American_Bulldog, Alaskan_Malamute, Akbash, 
        American_Foxhound, Australin_Shepherd, Basenji, 
        Boxer, Canadian_Eskimo_Dog, Dalmatian,
        English_Bulldog, English_Foxhound, French_Spaniel, 
        German_Shepherd, Icelandic_Sheepdog, Himalayan_Sheepdog, 
        Manchester_Terrier, Pembrok_Welsh_Corgi, Rafeiro_do_Alentejo, 
        Portuguese_Podengo, Saint_Bernard, Xiasi_Dog;
		
        public static Breed getRandomBreed() {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
    }
	
	public enum Gender {
        Male,
        Female;
        public static Gender getGender() {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
    }
	
	public enum DogRank {
        Class,
        Specialty;
        public static DogRank getDogRank() {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
    }
	
	public enum Dayxx {
        Friday,
        Saturday,
        Sunday;
		
        public static Dayxx getDayxx() {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
    }
	
	@Override
	public String toString() {
		return "Dog= " + dog + "\nOwner= " + owner + "\nBreed= " + breed + "\nDog Group= " + dogGroup
				+ "\nGender= " + gender + "\nDog Rank= " + dogRank + "\nEmail= " + email;
	}
}
