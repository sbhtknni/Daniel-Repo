import java.util.ArrayList;

/**
 * @author Daniel Maman and Ben The program shows how, with the help of two
 *         threads, it is possible to check whether there are elements in the
 *         array whose sum is equal to a given sum
 */
public class ThreadCheckArray implements Runnable {
	private boolean flag;
	private boolean[] winArray;
	SharedData sd;
	ArrayList<Integer> array;
	int b;

	/**
	 * @param sd - shared data The function receives shared information and
	 *           initializes it within a synchronized block
	 */
	public ThreadCheckArray(SharedData sd) {
		this.sd = sd;
		synchronized (sd) {
			array = sd.getArray();
			b = sd.getB();
		}
		winArray = new boolean[array.size()];
	}

	/**
	 * @param n
	 * @param b A function whose function is to search for the sum of numbers in an
	 *          array and check whether their number is equal to a given sum
	 */
	void rec(int n, int b) {
		synchronized (sd) {
			if (sd.getFlag())
				return;
		}
		if (n == 1) {
			if (b == 0 || b == array.get(n - 1)) {
				flag = true;
				synchronized (sd) {
					sd.setFlag(true);
				}
			}
			if (b == array.get(n - 1))
				winArray[n - 1] = true;
			return;
		}

		rec(n - 1, b - array.get(n - 1));
		if (flag)
			winArray[n - 1] = true;
		synchronized (sd) {
			if (sd.getFlag())
				return;
		}
		rec(n - 1, b);
	}

	/**
	 * RUN function which activates the thread and waits for the previous thread to
	 * see if it has finished and if there is any point in continuing to search
	 * again.
	 * 
	 */
	public void run() {
		if (array.size() != 1)
			if (Thread.currentThread().getName().equals("thread1"))
				rec(array.size() - 1, b - array.get(array.size() - 1));
			else
				rec(array.size() - 1, b);
		if (array.size() == 1)
			if (b == array.get(0) && !flag) {
				winArray[0] = true;
				flag = true;
				synchronized (sd) {
					sd.setFlag(true);
				}
			}
		if (flag) {
			if (Thread.currentThread().getName().equals("thread1"))
				winArray[array.size() - 1] = true;
			synchronized (sd) {
				sd.setWinArray(winArray);
			}
		}
	}
}
