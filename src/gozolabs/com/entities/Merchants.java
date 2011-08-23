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
    private List<Promotions> promotions;
    
    @Persistent
    private List<Addresses> addresses;
    
    @Persistent
    private LoyaltyPromotions loyaltyPromo;
    
    @Persistent
    private List<AroundImages> brandImages;
    
    @Persistent
    private List<AroundImages> images;


    public Merchants(String newName, String contactno, String description, List <Addresses> addresses, List <AroundImages> brandimages, List <AroundImages> images, boolean hasloyalty, LoyaltyPromotions loyaltypromotion, List<Promotions> promotions) {
        this.name = newName;
        this.contactNo = contactno;
        this.description = description;
        this.addresses = addresses;
        this.brandImages = brandimages;
        this.images = images;
        this.hasLoyalty = hasloyalty;
        this.loyaltyPromo = loyaltypromotion;
        this.promotions = promotions;
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

	public List<Promotions> getPromotions() {
		return promotions;
	}

	public void setPromotionList(List<Promotions> promoList) {
		this.promotions = promoList;
	}
	
	public void addPromotion(Promotions promotion) {
		this.promotions.add(promotion);
	}

	public List <Addresses> getAddresses() {
		return addresses;
	}

	public void setAddresses(List <Addresses> addresses) {
		this.addresses = addresses;
	}
	
	public void addAddress(Addresses addresses) {
		this.addresses.add(addresses);
	}

	public LoyaltyPromotions getLoyaltyPromo() {
		return loyaltyPromo;
	}

	public void setLoyaltyPromo(LoyaltyPromotions loyaltyPromo) {
		this.loyaltyPromo = loyaltyPromo;
	}

	public List<AroundImages> getBrandImages() {
		return brandImages;
	}

	public void addBrandImage(AroundImages brandImage) {
		this.brandImages.add(brandImage);
	}
	
	public void setBrandImage(List<AroundImages> brandImages) {
		this.brandImages = brandImages;
	}

	public List<AroundImages> getImages() {
		return images;
	}

	public void addImage(AroundImages image) {
		this.images.add(image);
	}
	
	public void setImages(List<AroundImages> images) {
		this.images = images;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
