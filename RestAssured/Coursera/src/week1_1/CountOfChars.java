package week1_1;

public class CountOfChars {
	public void CharCounter(String str) {
		int[] counters = new int[str.length()];
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			int index = str.indexOf(Character.toLowerCase(ch));
			if (index != -1) {
				counters[index] += 1;
			}
		}
		for (int k = 0; k < counters.length; k++) {
			System.out.println(str.charAt(k) + "\t" + counters);
		}
	}

	public static void main(String[] args) {
		CountOfChars ch1 = new CountOfChars();
		ch1.CharCounter("Prasanna");
	}

}
