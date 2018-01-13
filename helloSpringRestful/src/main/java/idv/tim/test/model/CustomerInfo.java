package idv.tim.test.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CustomerInfo implements Serializable
{
	@Id
    @Column(name="CUSTID")
    protected long custid;
    @Column(name="FIRSTNAME")
    protected String firstname;
    @Column(name="LASTNAME")
    protected String lastname;
    
	public CustomerInfo()
    {
        custid = -1L;
        firstname = "Unknown";
        lastname = "Unknown";
    }

	
	public long getCustid() {
		return custid;
	}
	public void setCustid(long custid) {
		this.custid = custid;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

}
