package sl3.items;


public class SI {

	//
	int		db_Id;
	String	created_at;
	String	modified_at;

	String	store, name, genre, yomi;
	int		price;
	
	int		num;
	
	String	posted_at;
	
	public SI(Builder builder) {

		db_Id = builder.db_Id;
		this.created_at	= builder.created_at;
		this.modified_at	= builder.modified_at;
		
		store = builder.store;
		name = builder.name;
		genre = builder.genre;
		yomi = builder.yomi;
		price = builder.price;
		
		this.num	= builder.num;
		
		this.posted_at	= builder.posted_at;
		
	}//public BM(Builder builder)

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public SI(String store, String name, int price, String genre) {
		
		this.store = store;
		this.name = name;
		this.price = price;
		this.genre = genre;
		
	}//public ShoppingItem(String store, String name, int price, String genre)

	public SI(String store, String name, int price, String genre, int id) {
		
		this.store = store;
		this.name = name;
		this.price = price;
		this.genre = genre;
		this.db_Id = id;
		
	}//public ShoppingItem(String store, String name, int price, String genre)
	
	public SI
		(String store, String name,
		int price, String genre, int id,
		String	created_at,
		String	updated_at,
		String	posted_at)
	{
		
		this.store = store;
		this.name = name;
		this.price = price;
		this.genre = genre;
		this.db_Id = id;
		
		this.created_at = created_at;
		this.modified_at = updated_at;
		this.posted_at = posted_at;
		
	}//public ShoppingItem(String store, String name, int price, String genre)

	public
	SI
	(int id, String name, String yomi, String genre, String store, int price) {
		
		this.db_Id = id;
		this.name = name;
		this.yomi = yomi;
		this.genre = genre;
		this.store = store;
		this.price = price;
		
		
		
	}//public ShoppingItem(String store, String name, int price, String genre)

	

	public
	SI
	(int id, String store, String name, int price, String genre, String yomi) {

		this.db_Id = id;
		this.name = name;
		this.yomi = yomi;

		this.genre = genre;
		this.store = store;
		this.price = price;

	}

	public SI() {
		// TODO Auto-generated constructor stub
	}

	
	
	
	public String getCreated_at() {
		return created_at;
	}

	public String getModified_at() {
		return modified_at;
	}

	public void setModified_at(String updated_at) {
		this.modified_at = updated_at;
	}

	public String getPosted_at() {
		return posted_at;
	}

	public void setPosted_at(String posted_at) {
		this.posted_at = posted_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getStore() {
		return store;
	}

	public String getName() {
		return name;
	}

	public String getGenre() {
		return genre;
	}

	public int getPrice() {
		return price;
	}

	public int getId() {
		return db_Id;
	}

	public String getYomi() {
		return yomi;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setYomi(String yomi) {
		this.yomi = yomi;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setId(int id) {
		this.db_Id = id;
	}

	public static class Builder {
		
		private int db_Id;
		private String	created_at;
		private String	modified_at;
		
		private String store, name, genre, yomi;
		private int price;

		private int num;
		
		private String	posted_at;
		
		public SI build() {
			
			return new SI(this);
			
		}
		
		public Builder setNum(int num) {
			this.num = num; return this;
		}

		public Builder setModified_at(String modified_at) {
			this.modified_at = modified_at; return this;
		}

		public Builder setStore(String store) {
			this.store = store;	return this;
		}
		public Builder setCreated_at(String created_at) {
			this.created_at = created_at;	return this;
		}

		public Builder setDb_id(int db_Id) {
			this.db_Id = db_Id;	return this;
		}

		public Builder setPosted_at(String posted_at) {
			this.posted_at = posted_at;	return this;
		}

		public Builder setName(String name) {
			this.name = name;	return this;
		}
		public Builder setGenre(String genre) {
			this.genre = genre;	return this;
		}
		public Builder setYomi(String yomi) {
			this.yomi = yomi;	return this;
		}
		public Builder setPrice(int price) {
			this.price = price;	return this;
		}


		
		
	}//public static class Builder
}
