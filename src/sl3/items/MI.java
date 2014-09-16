package sl3.items;

public class MI {

	int id_Item;
	int id_Title;
	int id_Icon;
	
	int order;
	
	
	public MI(Builder builder) {
		// TODO Auto-generated constructor stub
		
		this.id_Item	= builder.id_Item;
		this.id_Title	= builder.id_Title;
		this.id_Icon	= builder.id_Icon;
		this.order		= builder.order;
		
	}
	
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public int getId_Item() {
		return id_Item;
	}
	public int getId_Title() {
		return id_Title;
	}
	public int getId_Icon() {
		return id_Icon;
	}
	public void setId_Item(int id_Item) {
		this.id_Item = id_Item;
	}
	public void setId_Title(int id_Title) {
		this.id_Title = id_Title;
	}
	public void setId_Icon(int id_Icon) {
		this.id_Icon = id_Icon;
	}
	
	public static class Builder {
		
		int id_Item;
		int id_Title;
		int id_Icon;

		int order;
		
		public MI build() {
					
			return new MI(this);
					
		}
		
		public Builder setOrder(int order) {
			this.order = order; return this;
		}

		public Builder setId_Item(int id_Item) {
			this.id_Item = id_Item; return this;
		}
		public Builder setId_Title(int id_Title) {
			this.id_Title = id_Title; return this;
		}
		public Builder setId_Icon(int id_Icon) {
			this.id_Icon = id_Icon; return this;
		}
		
		
	}	
	
}
