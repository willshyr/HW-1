public class CookingItem implements CookingItemInterface {

	private String item; // item name
	private int cookingTime;
	private int checkedTime;
	private int underdonepenalty;
	private int overdonepenalty;
	private String information;

	public CookingItem(String i, int t, int u, int o) {
		this.item = i;
		this.cookingTime = t;
		this.underdonepenalty = u;
		this.overdonepenalty = o;
		this.checkedTime = 0;
	}

	public CookingItem(String info) {
		this.information = info;
	}

	public String getItem() {
		return this.item;
	}

	public String setItem(String i) {
		return this.item = i;
	}

	public int getCookingTime() {
		return this.cookingTime; //time remaining
	}

	public int getUnderdonePenalty() {
		return this.underdonepenalty;
	}

	public void setUnderdonePenalty(int u) {
		this.underdonepenalty = u;
	}

	public int getOverdonePenalty() {
		return this.overdonepenalty;
	}

	public void setOverdonePenalty(int o) {
		this.overdonepenalty = o;
	}

	/**
	* Get the time remaining for cooking this dish.
	* @return the time in minutes
	*/
	public int timeRemaining() {
		return this.cookingTime;
	}

	/**
	* Implements a simulation of one min of time for this item
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
		if (this.cookingTime < 0) {
			return this.cookingTime*(-1)*(this.overdonepenalty);
		} else {
			return this.cookingTime*this.underdonepenalty;
		}
	}

	/**
	* String representation of the item and the remaining cookingtime.
	* @Return
	* string of cooking item and its remaining cookingtime
	*/
	public String toString() {
		return "(" + this.item + " " + this.cookingTime + ")"; 
	}


}
