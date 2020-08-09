public class MatchingTags {

    // Tests if delimiters in the given expression are properly matched.
    public static boolean isMatched(String expression) {
        final String opening = "({[";  // opening delimiters
        final String closing = ")}]";  // respective closing delimiters

        Stack<Character> buffer = new LinkedStack<>();
        for (char c : expression.toCharArray()) {
            if (opening.indexOf(c) != -1)  // this is a left delimiter
                buffer.push(c);
            else if (closing.indexOf(c) != -1)  // this is a right delimiter
                if (buffer.isEmpty())  // nothing to match with
                    return false;
            if (closing.indexOf(c) != opening.indexOf(buffer.pop()))
                return false;  // mismatched delimiter
        }
        return buffer.isEmpty();  // were all opening delimiters matched?
    }

    // Tests if every opening tag has a matching closing tag in HTML string.
    public static boolean isHTMLMatched(String html) {
        Stack<String> buffer = new LinkedStack<>();
        int j = html.indexOf('<');  // find first '<' character (if any)
        while (j != -1) {
            int k = html.indexOf('>', j + 1);  // find next '>' character
            if (k == -1)
                return false;  // invalid tag
            String tag = html.substring(j + 1, k);  // strip away < >
            if (!tag.startsWith("/"))  // this is an opening tag
                buffer.push(tag);
            else {
                if (buffer.isEmpty())  // this is a closing tag
                    return false;  // no tag to match
                if (!tag.substring(1).equals(buffer.pop()))
                    return false;  // mismatched tag
            }
            j = html.indexOf('<', k + 1);  // find next '<' character (if any)
        }
        return buffer.isEmpty();  // were all opening tags matched?
    }
}
