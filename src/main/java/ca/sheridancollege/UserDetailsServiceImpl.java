package ca.sheridancollege;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ca.sheridancollege.dao.DAO;
import ca.sheridancollege.dao.DAO2;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		ca.sheridancollege.dog.User user = DAO2.findUserAccount(username);
		if(user==null) {
			System.out.println("User Not Found...!!!" + username);
			throw new UsernameNotFoundException("User " + username + " was not found in database.");
		}
		
		System.out.println("User Found: " + user);
		ArrayList<String> roles = DAO2.getRoleNames(user.getId());
		System.out.println(roles);
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if(roles != null) {
			for(String role : roles) {
				GrantedAuthority authority = new SimpleGrantedAuthority(role);
				grantList.add(authority);
			}
		}
		
		UserDetails userDetails = (UserDetails) new User(user.getUser(), user.getPassword(), grantList);
		
		return userDetails;
	}

}
