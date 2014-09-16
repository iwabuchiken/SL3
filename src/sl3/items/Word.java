package sl3.items;

public class Word {

	long id;
	String name;
	
	/***************************************
	 * The value returned from Yahoo Furigana<br>
	 * The katakana remains to be a katakana
	 ***************************************/
	String combo;
	
	/***************************************
	 * The all-hiragana string converted from katakana-including<br>
	 * 		string, through a method in Methods.java
	 ***************************************/
//	String hiragana;
	String yomi;
	
	private Word() {
		
	}
	
	public Word(long itemId, String name) {

		this.id = itemId;
		this.name = name;
		
	}

	public Word(long itemId, String name, String combo) {
		
		this.id = itemId;
		this.name = name;
		this.combo = combo;
		
	}

	public static Word getInstance() {
		
		return new Word();
		
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCombo() {
		return combo;
	}

	public String getYomi() {
		return yomi;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCombo(String combo) {
		this.combo = combo;
	}

	public void setYomi(String yomi) {
		this.yomi = yomi;
	}
	
	
}
