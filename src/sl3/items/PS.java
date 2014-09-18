package sl3.items;

public class PS {

	private long dbId;
	String created_at;
	String modified_at;
	String dueDate;
	int amount;
	
	private String storeName, memo, items;

	public PS(Builder b) {
		// TODO Auto-generated constructor stub

		this.dbId	= b.dbId;
		this.created_at	= b.created_at;
		this.modified_at	= b.modified_at;
		this.dueDate	= b.dueDate;
		this.amount	= b.amount;
		
		this.storeName	= b.storeName;
		this.memo	= b.memo;
		this.items	= b.items;
		
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

	public String getDueDate() {
		return dueDate;
	}

	public String getStoreName() {
		return storeName;
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

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
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
		
		String dueDate;
		
		int amount;
		
		private String storeName, memo, items;

		public PS build() {
			
			return new PS(this);
					
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

		public Builder setDueDate(String dueDate) {
			this.dueDate = dueDate; return this;
		}

		public Builder setAmount(int amount) {
			this.amount = amount; return this;
		}

		public Builder setStoreName(String storeName) {
			this.storeName = storeName; return this;
		}

		public Builder setMemo(String memo) {
			this.memo = memo; return this;
		}

		public Builder setItems(String items) {
			this.items = items; return this;
		}

		
	}
	
}//public class PS

