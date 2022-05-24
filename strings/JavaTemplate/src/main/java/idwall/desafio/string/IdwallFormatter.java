package idwall.desafio.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public class IdwallFormatter extends StringFormatter {

	public IdwallFormatter() {
		super();
	}

	public IdwallFormatter(Integer limit, boolean justify) {
		super(limit, justify);
	}

	/**
	 * Should format as described in the challenge
	 *
	 * @param text
	 * @return
	 */
	@Override
	public String format(String text) {
		if (this.justify) {
			return justifyFormat(text);
		}

		return simpleFormat(text);
	}

	private void createLineFeed(char[] value, int pos) {
		int step = pos + this.limit;

		if (value == null || value.length <= step) {
			// no more chars to test
			return;
		}

		// try to find the last space
		while (step > pos && value[step] != ' ' && value[step] != '\n') {
			step--;
		}

		if (step == pos) {
			// there are no spaces in line (big word?)
			// try to find next space at right of the word
			while (step < value.length && value[step] != ' ') {
				step++;
			}
		}

		value[step] = '\n';
		createLineFeed(value, step);
	}

	private String simpleFormat(String text) {
		char[] chars = text.toCharArray();

		createLineFeed(chars, 0);

		return String.valueOf(chars);
	}

	private String justifyFormat(String text) {
		text = simpleFormat(text);

		final List<String> values = Arrays.asList(text.split("\n"));
		final List<String> result = new ArrayList<>();

		long maxLen = 0;

		// calculate the max length of all lines
		for (String v : values) {
			maxLen = Math.max(maxLen, v.length());
		}

		for (String v : values) {
			final long increseTo = maxLen - v.length();

			int spaceIndex = -1;
			String line = v;

			// justify the line to 'increseTo' length
			for (int i = 0; i < increseTo; i++) {
				spaceIndex = line.indexOf(' ', spaceIndex + 1);

				// test if there are more spaces to expand
				if (spaceIndex == -1) {
					// we got the first space again
					spaceIndex = line.indexOf(' ', spaceIndex + 1);

					if (spaceIndex == -1) {
						// ohh... this line have no spaces
						break;
					}
				}

				line = new StringBuilder(line).insert(spaceIndex, ' ').toString();
				spaceIndex++;
			}

			result.add(line);
		}

		return String.join("\n", result);
	}
}
