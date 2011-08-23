package gozolabs.com.entities;

import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Element;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Merchants {
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;

    @Persistent
    private String name;
    
	@Persistent
    private boolean hasLoyalty;
    
    @Persistent
    private String contactNo;
    
    @Persistent
    private String description;
    
    @Persistent(mappedBy = "merchant")
    @Element(dependent = "true")
    private List<Promotions> promotion;
    
    @Persistent
    private List<Addresses> address;
    
    @Persistent
    private LoyaltyPromotions loyaltyPromo;
    
    @Persistent
    private List<AroundImages> brandImage;
    
    @Persistent
    private List<AroundImages> image;


    public Merchants(String newName) {
        this.name = newName;
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public void setName(String newName) {
        this.name = newName;
    }
    
    public boolean isHasLoyalty() {
		return hasLoyalty;
	}

	public void setHasLoyalty(boolean hasLoyalty) {
		this.hasLoyalty = hasLoyalty;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Promotions> getPromotion() {
		return promotion;
	}

	public void setPromotionList(List<Promotions> promoList) {
		this.promotion = promoList;
	}
	
	public void addPromotion(Promotions promotion) {
		this.promotion.add(promotion);
	}

	public List <Addresses> getAddress() {
		return address;
	}

	public void setAddress(List <Addresses> address) {
		this.address = address;
	}
	
	public void addAddress(Addresses address) {
		this.address.add(address);
	}

	public LoyaltyPromotions getLoyaltyPromo() {
		return loyaltyPromo;
	}

	public void setLoyaltyPromo(LoyaltyPromotions loyaltyPromo) {
		this.loyaltyPromo = loyaltyPromo;
	}

	public List<AroundImages> getBrandImage() {
		return brandImage;
	}

	public void addBrandImage(AroundImages brandImage) {
		this.brandImage.add(brandImage);
	}
	
	public void setBrandImage(List<AroundImages> brandImage) {
		this.brandImage = brandImage;
	}

	public List<AroundImages> getImage() {
		return image;
	}

	public void addImage(AroundImages image) {
		this.image.add(image);
	}
	
	public void setImage(List<AroundImages> image) {
		this.image = image;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
