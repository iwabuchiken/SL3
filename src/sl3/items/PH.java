package sl3.items;

public class PH {

//	"store_name", "pur_date",			// 3,4
//	"items",							// 5
//	"amount",							// 6
//	"memo",								// 7
//	"posted_at"							// 8

	private long dbId;
	String created_at;
	String modified_at;
	
	String store_name;
	String pur_date;
	String items;
	
	int amount;
	
	String memo;
	
	String posted_at;
	
	public PH(Builder b) {
		// TODO Auto-generated constructor stub

		this.dbId		= b.dbId;
		this.created_at	= b.created_at;
		this.modified_at	= b.modified_at;
		
		this.store_name	= b.store_name;
		this.pur_date	= b.pur_date;
		this.items	= b.items;
		
		this.amount	= b.amount;
		
		this.memo	= b.memo;
		
		this.posted_at	= b.posted_at;
		
	}

	
	
	public String getStore_name() {
		return store_name;
	}



	public String getPur_date() {
		return pur_date;
	}



	public String getPosted_at() {
		return posted_at;
	}



	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}



	public void setPur_date(String pur_date) {
		this.pur_date = pur_date;
	}



	public void setPosted_at(String posted_at) {
		this.posted_at = posted_at;
	}



	public long getDbId() {
		return dbId;
	}

	public String getCreated_at() {
		return created_at;
	}

	public String getModified_at() {
		return modified_at;
	}

	public String getStoreName() {
		return store_name;
	}

	public String getMemo() {
		return memo;
	}

	public String getItems() {
		return items;
	}

	public void setDbId(long dbId) {
		this.dbId = dbId;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public void setModified_at(String modified_at) {
		this.modified_at = modified_at;
	}

	public void setStoreName(String storeName) {
		this.store_name = storeName;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public static class Builder {
	
		private long dbId;
		String created_at;
		String modified_at;
		
		String store_name;
		String pur_date;
		String items;
		
		int amount;
		
		String memo;
		
		String posted_at;

		public PH build() {
			
			return new PH(this);
					
		}
		
		
		
		public Builder setStore_name(String store_name) {
			this.store_name = store_name; return this;
		}



		public Builder setPur_date(String pur_date) {
			this.pur_date = pur_date; return this;
		}



		public Builder setPosted_at(String posted_at) {
			this.posted_at = posted_at; return this;
		}



		public Builder setDbId(long dbId) {
			this.dbId = dbId; return this;
		}

		public Builder setCreated_at(String created_at) {
			this.created_at = created_at; return this;
		}

		public Builder setModified_at(String modified_at) {
			this.modified_at = modified_at; return this;
		}

		public Builder setAmount(int amount) {
			this.amount = amount; return this;
		}

		public Builder setStoreName(String storeName) {
			this.store_name = storeName; return this;
		}

		public Builder setMemo(String memo) {
			this.memo = memo; return this;
		}

		public Builder setItems(String items) {
			this.items = items; return this;
		}

		
	}
	
}//public class PS

