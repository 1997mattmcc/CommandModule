package commandmodule.utils;

import org.apache.commons.lang3.StringUtils;
import java.util.List;

public class ListToString {

    public static final String generateMessage(String title, List<String> lines, int pageLength, int page, int countFrom) {
        String message = new String();
        message = message.concat("```md\n");
        message = message.concat(title).concat("\n");
        message = message.concat(StringUtils.repeat("=", title.length())).concat("\n");

        for (int i = (page - 1) * pageLength; i < Math.min(page * pageLength, lines.size()) && i >= 0; i++) {
            message = message.concat(String.format("\n[%s]: ", Integer.toString(i + countFrom)));
            message = message.concat(StringUtils.repeat(" ", Integer.toString(lines.size() + countFrom).length() - Integer.toString(i + countFrom).length()));
            message = message.concat(lines.get(i));
        }
        message = message.concat("\n\n# Page ");
        message = message.concat(Integer.toString(page));
        message = message.concat(" of ");
        message = message.concat(Integer.toString((int) Math.ceil(lines.size() / pageLength) + 1));
        message = message.concat("\n```");
        return message;
    }
}
