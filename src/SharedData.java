import java.util.ArrayList;

/**
 * @author Daniel and Ben	
 * Class SharedData This class shared the data with the threads
 * 
 */
public class SharedData {
	private ArrayList<Integer> array;
	private boolean[] winArray;
	private boolean flag;
	private final int b;

	/**
	 * @param array 
	 * given parameter 
	 * @param b
	 * constructor that get array and integer b
	 */
	public SharedData(ArrayList<Integer> array, int b) {
		this.array = array;
		this.b = b;
	}

	/**
	 * @return
	 * return the array that win
	 */
	public boolean[] getWinArray() {
		return winArray;
	}

	/**
	 * @param winArray
	 * set the array that win
	 */
	public void setWinArray(boolean[] winArray) {
		this.winArray = winArray;
	}

	/**
	 * @return
	 * return the array
	 */
	public ArrayList<Integer> getArray() {
		return array;
	}

	/**
	 * @return
	 * return b
	 */
	public int getB() {
		return b;
	}

	/**
	 * @return
	 * get the flag
	 */
	public boolean getFlag() {
		return flag;
	}

	/**
	 * @param flag
	 * set the flag
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
