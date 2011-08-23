package gozolabs.com.entities;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class LoyaltyPromotions {
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;

	@Persistent
    private String reward;
    
    @Persistent
    private String tnc;
    
    @Persistent
    private String block;
    
    @Persistent
    private String streetName;
    
    @Persistent
    private String postalCode;


    public LoyaltyPromotions(String _reward, String _tnc, String _block, String _streetName, String _postalCode) {
        this.reward = _reward;
        this.tnc = _tnc;
        this.block = _block;
        this.streetName = _streetName;
        this.postalCode = _postalCode;
    }

    public Long getId() {
        return id;
    }
    
    public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	public String getTnc() {
		return tnc;
	}

	public void setTnc(String tnc) {
		this.tnc = tnc;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setId(Long id) {
		this.id = id;
	}


}
