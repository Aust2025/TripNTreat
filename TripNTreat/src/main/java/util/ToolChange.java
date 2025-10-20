package util;

import java.util.ArrayList;
import java.util.List;

public class ToolChange {
	public static String breakdown(int change) {
		int[] denominations = { 1000, 500, 100, 50, 10, 5, 1 };
		int[] counts = new int[denominations.length];
		int remaining = change;

		for (int i = 0; i < denominations.length; i++) {
			counts[i] = remaining / denominations[i];
			remaining -= counts[i] * denominations[i];
		}

		List<String> lines = new ArrayList<>();
		lines.add("找零：$" + change);
		for (int i = 0; i < denominations.length; i++) {
			if (counts[i] > 0) {
				lines.add("$" + denominations[i] + " × " + counts[i] + " 張/枚");
			}
		}
		return String.join("\n", lines);
	}
}