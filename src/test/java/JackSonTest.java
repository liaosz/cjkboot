import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @auther: liaosz
 * @date: 2021/08/05/9:29
 * @description:
 */
public class JackSonTest {
    public static void main(String[] args) throws JsonProcessingException {
        String jsonStr = "{\n" +
                "    \"name\": \"中国\",\n" +
                "    \"province\": [{\n" +
                "        \"name\": \"黑龙江\",\n" +
                "        \"cities\": {\n" +
                "            \"city\": [\"哈尔滨\", \"大庆\"]\n" +
                "        }\n" +
                "    }, {\n" +
                "        \"name\": \"广东\",\n" +
                "        \"cities\": {\n" +
                "            \"city\": [\"广州\", \"深圳\", \"珠海\"]\n" +
                "        }\n" +
                "    }, {\n" +
                "        \"name\": \"台湾\",\n" +
                "        \"cities\": {\n" +
                "            \"city\": [\"台北\", \"高雄\"]\n" +
                "        }\n" +
                "    }, {\n" +
                "        \"name\": \"新疆\",\n" +
                "        \"cities\": {\n" +
                "            \"city\": [[1,2,3]]\n" +
                "        }\n" +
                "    }]\n" +
                "}";
        ObjectMapper mapper = new ObjectMapper();
       /* final JsonNode root = mapper.readTree(jsonStr);
        JsonNode jsonNode = getByPath(root, "/province[3]/cities/city[0][1]");

        System.out.println(jsonNode.asText());
        JsonNode jsonNode1 = getByPath(root, "/province[3]/cities");

        System.out.println(jsonNode1);

        System.out.println(root);*/


        final ObjectNode objectNode = mapper.createObjectNode();
        System.out.println(createNode(objectNode, "/test/a[0][0]/b"));
        System.out.println(objectNode);

       /*  final HashMap hashMap = mapper.readValue(jsonStr, HashMap.class);
        System.out.println(hashMap);

        String pattem = "/name";

        final ObjectNode objectNode = mapper.createObjectNode();

        objectNode.put("version", "v1.0");
        final ArrayNode arrayNode = mapper.createArrayNode();
        objectNode.set("people", arrayNode);
        final ObjectNode objectNode1 = mapper.createObjectNode();
        objectNode1.put("firstName", "Brett");
        objectNode1.put("lastName", "McLaughlin");
        final ObjectNode objectNode2 = mapper.createObjectNode();
        objectNode2.put("firstName", "Jason");
        objectNode2.put("lastName", "Hunter");


        arrayNode.add(objectNode1);
        arrayNode.add(objectNode2);
        arrayNode.add(1);

        System.out.println(arrayNode.get(0));
        System.out.println(arrayNode.get(1));
        System.out.println(objectNode);


        Pattern p = Pattern.compile("cat");
        Matcher m = p.matcher("one cat two cats in the yard");
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
//            String match = m.group(1);
            m.appendReplacement(sb, "dog");
        }
        m.appendTail(sb);
        System.out.println(sb.toString());




        StringBuffer sb1 = new StringBuffer();
        String pattern = "\\.*\\[([^\\[\\]]+?)\\]";
        Pattern NAMES_PATTERN = Pattern.compile(pattern);//"\\.([^/]+?)}"
        Matcher matcher = NAMES_PATTERN.matcher("str[ 1 ] a ]");
        while (matcher.find()) {
            String match2 = matcher.group(1);

            System.out.println(match2);

            matcher.appendReplacement(sb1, "bb");

        }

        matcher.appendTail(sb1);

        System.out.println(sb1);
*/




    }

    public static JsonNode getByPath(JsonNode jsonNode, String paths) {
        final String[] path = paths.split("/", 2);
        String fieldName = path[0];

        if(path.length == 1){
            return parseNode(jsonNode, fieldName);
        }

        if (fieldName.trim().length() == 0) {
            return getByPath(jsonNode, path[1]);
        }
        if (jsonNode.isObject()) {
            JsonNode arrayNode = parseNode(jsonNode,fieldName);
            return getByPath(arrayNode, path[1]);
        }
        return null;
    }
    public static JsonNode parseNode(JsonNode jsonNode,String path){

        String name = path.split("\\[")[0];
        JsonNode arrayNode = jsonNode.get(name);
        String pattern = "\\.*\\[([^\\[\\]]+?)\\]";
        Pattern NAMES_PATTERN = Pattern.compile(pattern);//"\\.([^/]+?)}"
        Matcher matcher = NAMES_PATTERN.matcher(path);
        while (matcher.find()) {
            String match = matcher.group(1);
            Integer index = Integer.parseInt(match);
            arrayNode = arrayNode.get(index);
        }
        return arrayNode;

    }

    public static JsonNode createJsonData(JsonNode jsonNode,String paths,String value){
        final String[] path = paths.split("/", 2);
        String fieldName = path[0];
        if(path.length == 1){
            return createNode(jsonNode,fieldName);
        }
        if (fieldName.trim().length() == 0) {
            createJsonData(jsonNode,path[1],value);
        }
        return null;

    }

    public static JsonNode createNode(JsonNode jsonNode,String path){
        String name = path.split("\\[")[0];
        ObjectNode objectNode = (ObjectNode) jsonNode;
        if(!jsonNode.has(name)){
            String pattern = "\\.*\\[([^\\[\\]]+?)\\]";
            Pattern NAMES_PATTERN = Pattern.compile(pattern);//"\\.([^/]+?)}"
            Matcher matcher = NAMES_PATTERN.matcher(path);
            List<Integer> indexList = new ArrayList<>();

            while (matcher.find()) {
                String match = matcher.group(1);
                Integer index = Integer.parseInt(match);
                indexList.add(index);
            }
            if(indexList.size() > 0){
                ArrayNode arrayNode = objectNode.putArray(name);
                for (int i = 0; i < indexList.size(); i++) {
                    if(!arrayNode.has(i)){
                        if(i == indexList.size()-1){
                          return arrayNode.addObject();
                        }else{
                            arrayNode.insertArray(i);
                        }
                    }

                }

            }else {
               return objectNode.putObject(name);
            }

        }


        return objectNode.get(name);
    }
}
