package gozolabs.com.entities;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Category {
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;

    @Persistent
    private String name;


    public Category(String newName) {
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

}
