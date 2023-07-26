package tech.bosstop.woolrace.utilities;

import java.util.Map;

/*
 * This class is a modified version of the PowerFormatter class from the PowerRanks plugin.
 * The original class can be found here:
 * By Svenar
*/

public class PowerFormatter {
    public static String format(String text, Map<String, String> values, char openChar, char closeChar) {
        StringBuilder result = new StringBuilder();
        int textIdx = 0;

        for (int startIdx; (startIdx = text.indexOf(openChar, textIdx)) != -1;) {
            int endIdx = text.indexOf(closeChar, startIdx + 1);

            if (endIdx == -1)
                break;

            if (startIdx < 2 || text.charAt(startIdx - 1) != '\\') {
                result.append(text.substring(textIdx, startIdx));
                textIdx = endIdx + 1;
                String value = values.get(text.substring(startIdx + 1, endIdx));

                if (value != null && !value.isEmpty()) {
                    result.append(value); // Replace placeholder with non-empty value

                } else if (result.length() != 0 && result.charAt(result.length() - 1) == ' ') {
                    result.setLength(result.length() - 1); // Remove space before placeholder

                } else if (textIdx < text.length() && text.charAt(textIdx) == ' ') {
                    textIdx++; // Skip space after placeholder
                } else {
                    result.append(openChar + text.substring(startIdx + 1, endIdx) + closeChar); // Add back the original
                    // placeholder when an
                    // replacement isn't
                    // found
                }
            } else {
                String unformatted = text.substring(textIdx, endIdx + 1).replaceFirst("\\\\", "");
                if (unformatted.length() > 1) {
                    String replaceText = text.substring(startIdx, endIdx + 1);
                    String baseText = text.substring(startIdx, startIdx + 1);
                    String endText = text.substring(endIdx + 1, endIdx + 1);
                    String formattedReplacement = baseText
                            + format(text.substring(startIdx + 1, endIdx + 1), values, openChar, closeChar)
                            + endText;

                    unformatted = unformatted.replace(replaceText, formattedReplacement);
                }
                result.append(unformatted);
                textIdx = endIdx + 1;
            }
        }
        result.append(text.substring(textIdx));
        return result.toString();
    }
}
