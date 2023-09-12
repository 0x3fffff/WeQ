package client;


import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util{

    static Pattern pattern = Pattern.compile("\\[//(\\d+)\\*/\\]");//创建匹配数字字符的模式
    public static String checkUpdNum(String text){
        if (text.charAt(0)!='[') return text;
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()){
            return matcher.group(1);
        }return text;
    }


    public static String htmlEscape1(String str) {

        if(isEmpty(str))
            return str;

        StringBuilder w = new StringBuilder();
        for (int i = 0, len = str.length(); i < len; i++) {
            char cur = str.charAt(i);
            switch (cur) {
                case '<' -> w.append("&lt;");
                case '>' -> w.append("&gt;");
                case '"' -> w.append("&quot;");
                case '\'' -> w.append("&#39;");
                case '&' -> w.append("&amp;");
                default -> w.append(cur);
            }
        }
        return w.toString();
    }

    private static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     *
     * StringBuilder sb = new StringBuilder();
     * HashMap<String, String> oldToNew = new HashMap<>();
     * oldToNew.put("$sn",sn);
     * oldToNew.put("$email",mail);
     * StringUtils.replaceOneMore(sb,registertemplate,"[$]sn|[$]email",oldToNew);
     *
     * @param output
     * @param input
     * @param oldRegex
     * @param oldToNew
     */
    public static void replaceOneMore(StringBuilder output, String input,
                                      String oldRegex, Map<String,String> oldToNew){
        Pattern p = Pattern.compile(oldRegex);
        Matcher m = p.matcher(input);

        while (m.find()) {
            String one = m.group();
            if(oldToNew.containsKey(one))
                m.appendReplacement(output, oldToNew.get(one));
        }
        m.appendTail(output);
    }

    public static String htmlEscape2(String source){
        if(isEmpty(source))
            return source;

        StringBuilder builder = new StringBuilder();
        HashMap<String, String> oldToNew = new HashMap<>();
        oldToNew.put("<","&lt;");
        oldToNew.put(">","&gt;");
        oldToNew.put("\"","&quot;");
        oldToNew.put("'","&#39;");
        oldToNew.put("&","&amp;");
        long start2 = System.currentTimeMillis();
        replaceOneMore(builder,source,"[<]|[>]|[\"]|[']|[&]",oldToNew);
        long end2 = System.currentTimeMillis();
        print("replaceOneMore",end2-start2);
        return builder.toString();
    }

    public static String htmlUnescape(String str){
        if(isEmpty(str))
            return str;

        if(str.indexOf("&") == -1)
            return str;

        StringBuilder sb = new StringBuilder();
        HashMap<String, String> oldToNew = new HashMap<>();
        oldToNew.put("&lt;","<");
        oldToNew.put("&gt;",">");
        oldToNew.put("&quot;","\"");
        oldToNew.put("&#39;","\'");
        oldToNew.put("&amp;","&");
        replaceOneMore(sb,str,"[&]lt;|[&]gt;|[&]quot;|[&]#39;|[&]amp;",oldToNew);
        return sb.toString();
    }

    public static<T> void print(String key,T t){
        System.out.println(key +" : "+t);
    }


}
