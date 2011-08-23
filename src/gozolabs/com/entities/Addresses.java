package gozolabs.com.entities;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Addresses {
	    
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;

	@Persistent
    private String buildingName;
    
    @Persistent
    private String unitNo;
    
    @Persistent
    private String block;	
    
    @Persistent
    private String streetName;
    
    @Persistent
    private String postalCode;


    public Addresses(String bName, String _unitNo, String _block, String _streetName, String _postalCode) {
        this.buildingName = bName;
        this.unitNo = _unitNo;
        this.block = _block;
        this.streetName = _streetName;
        this.postalCode = _postalCode;
    }

    public Long getId() {
        return id;
    }
 
    public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getUnitNo() {
		return unitNo;
	}

	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
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
