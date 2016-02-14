public class CookingItem implements CookingItemInterface {
	
	private String item;
	private int cookingTime;
	private int checkedTime;
	private int penalty;

	public CookingItem(String i, int t, int p) {
		this.item = i;
		this.cookingTime = t;
		this.penalty = p;
		this.checkedTime = 0;
	}

	public String getItem(){
		return this.item;
	}

	public String setItem(String i){
		return this.item = i;
	}

	public int getCookingTime() {
		return this.cookingTime; //time remaining
	}

	public int getPenalty() {
		return this.penalty;
	}

	public int setPenalty(int p) {
		return this.penalty = p;
	}

	/** 
	* Get the time remaining for cooking this dish.
	* @return the time in minutes
	*/
	public int timeRemaining() {
		return this.cookingTime; 
	}

	/** Implements a simulation of one min of time for this item
	* by decrementing cooking time by one minute
	*/
	public void tick(){
		this.cookingTime = this.cookingTime - 1; 
	}

	/** 
	* Calculate the penalty if this dish were removed now.
	* @return the penalty
	*/
	public int penalty() {
		return 0; //dummy variable
	}

}