package PUTAPIs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

	
	private Integer id;
	private String name;
    private String email;
    private String gender;
    private String status;
    
    
	public User(String name, String email, String gender, String status) {
	
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.status = status;
	}
	
    
    
    
}
