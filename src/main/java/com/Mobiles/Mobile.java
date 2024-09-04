package com.Mobiles;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;

@Entity
@Table(name = "mobile")

public class Mobile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String mobileName;
	private int mobileQuantity;
	private int mobileCost;
	
	 @ManyToOne
	    @JoinColumn(name = "brand_id")
	    private Brand brand;

	public Mobile() {
		super();
	}

	public Mobile(int id, String mobileName, int mobileQuantity, int mobileCost) {
	
			this.id = id;
			this.mobileName = mobileName;
			this.mobileQuantity = mobileQuantity;
			this.mobileCost = mobileCost;
			
		
		}
	public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

	public int getId() {
		return id;
	}

	public String getMobileName() {
		return mobileName;
	}

//		public int getMobileQuantity() {
////			return mobileQuantity;
////		}

	public int getMobileCost() {
		return mobileCost;

	}

	public int getMobileQuantity() {
		return mobileQuantity;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMobileName(String mobileName) {
		this.mobileName = mobileName;
	}

	public void setMobileQuantity(int mobileQuatity) {
		this.mobileQuantity = mobileQuatity;
	}

	public void setMobileCost(int mobileCost) {
		this.mobileCost = mobileCost;
	}

	@Override
	public String toString() {
		return String.format("Mobile [%d, =%s, =%d, =%d]", id, mobileName, mobileQuantity, mobileCost);
	}

}
