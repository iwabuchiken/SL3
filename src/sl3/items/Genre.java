package sl3.items;


public class Genre {

	//
	int		db_Id;
	String	created_at;
	String	modified_at;

	String	genre_name;
	String	posted_at;
	
	public Genre(Builder builder) {

		db_Id = builder.db_Id;
		this.created_at	= builder.created_at;
		this.modified_at	= builder.modified_at;

		this.genre_name	= builder.genre_name;
		
		this.posted_at	= builder.posted_at;
		
	}//public BM(Builder builder)

	
	public int getDb_Id() {
		return db_Id;
	}


	public String getGenre_name() {
		return genre_name;
	}


	public void setDb_Id(int db_Id) {
		this.db_Id = db_Id;
	}


	public void setGenre_name(String genre_name) {
		this.genre_name = genre_name;
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

	public int getId() {
		return db_Id;
	}

	public void setId(int id) {
		this.db_Id = id;
	}

	public static class Builder {
		
		private int db_Id;
		private String	created_at;
		private String	modified_at;
		
		String	genre_name;
		String	posted_at;
		
		public Genre build() {
			
			return new Genre(this);
			
		}

		public Builder setDb_Id(int db_Id) {
			this.db_Id = db_Id; return this;
		}

		public Builder setCreated_at(String created_at) {
			this.created_at = created_at; return this;
		}

		public Builder setModified_at(String modified_at) {
			this.modified_at = modified_at; return this;
		}

		public Builder setGenre_name(String genre_name) {
			this.genre_name = genre_name; return this;
		}

		public Builder setPosted_at(String posted_at) {
			this.posted_at = posted_at; return this;
		}
		
		
	}//public static class Builder
}
