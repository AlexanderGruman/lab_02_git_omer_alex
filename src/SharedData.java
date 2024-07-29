import java.util.ArrayList;

/**
 * This class represents shared data that is accessed by multiple threads.
 * It contains an array of integers, a target sum, and flags indicating
 * the state of the computation.
 */
public class SharedData {

	private ArrayList<Integer> array;
	private boolean[] winArray;
	private boolean flag;
	private final int b;

	/**
	 * Constructor for the SharedData class.
	 *
	 * @param array the array of integers to be checked by the threads.
	 * @param b the target sum to be checked.
	 */
	public SharedData(ArrayList<Integer> array, int b) {
		this.array = array;
		this.b = b;
	}

	/**
	 * Gets the array of booleans indicating which elements are part of the solution.
	 *
	 * @return the array of booleans.
	 */
	public boolean[] getWinArray() {
		return winArray;
	}

	/**
	 * Sets the array of booleans indicating which elements are part of the solution.
	 *
	 * @param winArray the array of booleans to set.
	 */
	public void setWinArray(boolean[] winArray) {
		this.winArray = winArray;
	}

	/**
	 * Gets the array of integers.
	 *
	 * @return the array of integers.
	 */
	public ArrayList<Integer> getArray() {
		return array;
	}

	/**
	 * Gets the target sum to be checked.
	 *
	 * @return the target sum.
	 */
	public int getB() {
		return b;
	}

	/**
	 * Gets the flag indicating whether a solution has been found.
	 *
	 * @return the flag.
	 */
	public boolean getFlag() {
		return flag;
	}

	/**
	 * Sets the flag indicating whether a solution has been found.
	 *
	 * @param flag the flag to set.
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}
