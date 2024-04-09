package PackageDeleteAPIs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@Builder
public class Userwer {

	
	
	private Integer id;
	private String name;
    private String email;
    private String gender;
    private String status;
    
    
    
	public Userwer(String name, String email, String gender, String status) {
	
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.status = status;
	}
    
    
    
    
}

