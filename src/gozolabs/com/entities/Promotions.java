package gozolabs.com.entities;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Promotions {
    
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;

    @Persistent
    private String title;
    
	@Persistent
    private String description;
    
    @Persistent
    private Category category;
    
    @Persistent
    private Merchants merchant;
    
    @Persistent
    private Date startDate;
    
    @Persistent
    private Date endDate;
    
    @Persistent
    private boolean isLive;
    
    @Persistent
    private AroundImages image;


    public Promotions(String newTitle) {
        this.title = newTitle;
    }

    public Long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String newName) {
        this.title = newName;
    }

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Merchants getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchants merchant) {
		this.merchant = merchant;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}

	public AroundImages getImage() {
		return image;
	}

	public void setImage(AroundImages image) {
		this.image = image;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
