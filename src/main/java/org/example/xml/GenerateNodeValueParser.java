package org.example.xml;

import org.example.model.GenParsedResult;

import java.util.HashMap;
import java.util.Map;

import static org.example.xml.TypeToGen.STRING;

class GenerateNodeValueParser {

    // Инициализация значений по умолчанию
    private final int min = 5; // По умолчанию минимальная длина 5
    private final int max = 10; // По умолчанию максимальная длина 10
    private final TypeToGen type = STRING; // По умолчанию тип 'string'

    public GenParsedResult parseNodeValue(String input) {
        GenParsedResult result = new GenParsedResult(min, max, type);

        input = input.replace("{", "").replace("}", "");

        // Разбиваем строку по точке
        String[] parts = input.split("\\.");
        if (parts.length == 1) {
            return result;
        }

        Map<String, String> parameters = saveParametersToMap(parts);

        // Обрабатываем параметры
        if (parameters.containsKey("len")) {
            String lenPart = parameters.get("len");
            String[] lengthParts = lenPart.split("-");

            if (lengthParts.length == 1) {
                result.setMax(Integer.parseInt(lengthParts[0])); // Минимальная длина
            }

            if (lengthParts.length == 2) {
                result.setMin(Integer.parseInt(lengthParts[0])); // Минимальная длина
                result.setMax(Integer.parseInt(lengthParts[1])); // Максимальная длина
            }
        }

        if (parameters.containsKey("type")) {
            result.setType(TypeToGen.valueOf(parameters.get("type"))); // Если указан тип, используем его
        }

        return result;
    }

    private Map<String, String> saveParametersToMap(String[] parts) {
        Map<String, String> parameters = new HashMap<>();

        // Сохраняем параметры в мапу для произвольного порядка
        for (String part : parts) {
            if (part.startsWith("len[")) {
                String lenPart = part
                        .replace("len[", "")
                        .replace("]", ""); // Убираем 'len[' и ']'
                parameters.put("len", lenPart);
            } else if (isPartMatchesType(part)) {
                parameters.put("type", part.toUpperCase());
            }
        }
        return parameters;
    }

    private boolean isPartMatchesType(String part) {
        part = part.toLowerCase();
        if ("string".equals(part)) {
            return true;
        }
        if ("int".equals(part)) {
            return true;
        }
        return false;
    }
}
