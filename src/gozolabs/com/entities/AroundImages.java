package gozolabs.com.entities;



import com.google.appengine.api.datastore.Blob;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * JDO-annotated model class for storing merchant images properties; promotional
 * image is stored as a Blob (large byte array) in the image field.
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class AroundImages {

	    @PrimaryKey
	    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	    private Long id;

	    @Persistent
	    private String filename;

	    @Persistent
	    private Blob image;
	    
	    @Persistent
	    private String imageURL;

		//JPA getters and setters & constructors
	    public AroundImages() { }
	    public AroundImages(String name, Blob imageBlob) {
			this.filename = name;
			this.image = imageBlob;
		}

		public Long getId() {
	        return id;
	    }

	    public String getFileName() {
	        return filename;
	    }

	    public Blob getImage() {
	        if (image == null) {
	            return null;
	        }

	        return image;
	    }

	    public void setFileName(String myName) {
	        this.filename = myName;
	    }

	    public void setImage(Blob newimage) {
	        this.image = newimage;
	    }

	    public String getImageURL() {
			return imageURL;
		}
	    
		public void setImageURL(String imageURL) {
			this.imageURL = imageURL;
		}
}
